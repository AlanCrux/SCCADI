package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.daoimpl.DAOActividadAsignadaImpl;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.daoimpl.DAOSalaImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.ActividadProgramada;
import logica.dominio.Asesor;
import logica.dominio.ExperienciaEducativa;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarActividadesAsignadasController implements Initializable {

  @FXML
  private ImageView botonRegresar;
  @FXML
  private Label labelNombreAsesor;
  @FXML
  private JFXComboBox<ExperienciaEducativa> comboExperienciaEdu;
  @FXML
  private JFXComboBox<ActividadProgramada> comboSemana;
  @FXML
  private TableView<ActividadAsignada> tablaActividades;
  @FXML
  private TableColumn<ActividadAsignada, String> columnaActividad;
  @FXML
  private TableColumn<ActividadAsignada, String> columnaSala;
  @FXML
  private TableColumn<ActividadAsignada, Time> columnaHora;
  @FXML
  private TableColumn<ActividadAsignada, Date> columnaFecha;
  @FXML
  private JFXButton botonRegistrarAsistencia;

  ObservableList<ActividadAsignada> listaActividades = null;
  DAOActividadAsignadaImpl actividad = new DAOActividadAsignadaImpl();
  DAOActividadProgramadaImpl actividadP = new DAOActividadProgramadaImpl();
  DAOExperienciaEducativaImpl experienciaEdu = new DAOExperienciaEducativaImpl();
  DAOSalaImpl salas = new DAOSalaImpl();
  Asesor asesorCreado = new Asesor();

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    labelNombreAsesor.setText(asesorCreado.getNombre());
    llenarTabla();
    llenarComboExp();
    llenarComboSemana();
    botonRegresar.setOnMouseClicked(event->{
      regresar();
    });
  }

  /**
   * Metoodo que llena una tabla con los datos de las actividades que ya han sido asignadas a un
   * asesor
   */
  public void llenarTabla() {
    try {
      ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadAsignadaAlAsesor(asesorCreado.getNoPersonal()));
      columnaActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
      columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
      tablaActividades.setItems(listaActividades2);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }
  }

  /**
   * Metodo que llena el combo de experiencias educativas con las EE registradas en la base de datos
   */
  public void llenarComboExp() {
    try {
      ObservableList<ExperienciaEducativa> listaExperiencias = FXCollections.observableList(experienciaEdu.obtenerExperienciasPorAsesor(asesorCreado.getNoPersonal()));
      comboExperienciaEdu.getItems().addAll(listaExperiencias);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }
  }

  /**
   * Metodo que filtra los registros asignados a un asesor por experiencia educativa
   */
  @FXML
  public void filtrarPorExperiencia() {
    int noPersonal = asesorCreado.getNoPersonal();
    int experiencia = comboExperienciaEdu.getValue().getIdExperiencia();
    System.out.println(experiencia);

    try {
      ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadesPorExperiencia(noPersonal, experiencia));
      columnaActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
      columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
      tablaActividades.setItems(listaActividades2);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }
  }

  /**
   * Metodo que llena el comboBox de las semanas con los rangos de fecha de las actividades
   * programadas
   */
  public void llenarComboSemana() {
    try {
      ObservableList<ActividadProgramada> listaSemanas = FXCollections.observableList(actividadP.obtenerActividadesProgramadas());
      ObservableList<ActividadProgramada> listaSemanas2 = FXCollections.observableArrayList();
      Date ahora = new Date();
      for (int i = 0; i < listaSemanas.size(); i++) {
        Date fechaInicio = listaSemanas.get(i).getFechaInicio();
        Date fechaFin = listaSemanas.get(i).getFechaFin();
        if (fechaInicio.after(ahora)) {
          listaSemanas2.add(listaSemanas.get(i));
        }
      }
      comboSemana.getItems().addAll(listaSemanas2);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
      Logger.getLogger(IUConsultarActividadesAsignadasController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo que filtra las actividades asignadas a un asesor por un intervalo de tiempo
   */
  @FXML
  public void filtrarPorSemana() {
    int noPersonal = asesorCreado.getNoPersonal();
    try {
      int experiencia = comboExperienciaEdu.getValue().getIdExperiencia();
      Date fechaMin = comboSemana.getValue().getFechaInicio();
      Date fechaMax = comboSemana.getValue().getFechaFin();

      ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadesPorFecha(noPersonal, experiencia, fechaMin, fechaMax));
      columnaActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
      columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
      tablaActividades.setItems(listaActividades2);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error", "Seleccione una experiencia educativa primero");
      System.out.println(ex);
    }
  }

  /**
   * Muestra la ventana.
   *
   * @param loader el loader con la ruta de la ventana que se quiere cargar.
   */
  public void mostrarVentana(FXMLLoader loader) {
    try {
      Stage stagePrincipal = new Stage();
      Parent root = (Parent) loader.load();
      Scene scene = new Scene(root);
      stagePrincipal.setScene(scene);
      stagePrincipal.show();
    } catch (IOException ex) {
      Logger.getLogger(IUMenuAsesorController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Asesor getAsesorCreado() {
    return asesorCreado;
  }

  public void setAsesorCreado(Asesor asesorCreado) {
    this.asesorCreado = asesorCreado;
  }

  public void regresar() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuAsesor.fxml"));
    IUMenuAsesorController controller = new IUMenuAsesorController();
    loader.setController(controller);
    controller.setAsesor(asesorCreado);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) botonRegresar.getScene().getWindow();
    mainStage.close();
  }

}

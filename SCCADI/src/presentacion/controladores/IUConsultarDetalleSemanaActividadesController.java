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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.daoimpl.DAOActividadAsignadaImpl;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.ActividadProgramada;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Recepcionista;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarDetalleSemanaActividadesController implements Initializable {

  @FXML
  private JFXComboBox<ExperienciaEducativa> comboExperiencia;
  @FXML
  private JFXComboBox<ActividadProgramada> comboSemana;
  @FXML
  private TableView<ActividadAsignada> tablaActividades;
  @FXML
  private TableColumn<ActividadAsignada, String> columnaNombre;
  @FXML
  private TableColumn<ActividadAsignada, String> columnaAsesor;
  @FXML
  private TableColumn<ActividadAsignada, Date> columnaFecha;
  @FXML
  private TableColumn<ActividadAsignada, Time> columnaHora;
  @FXML
  private TableColumn<ActividadAsignada, String> columnaSala;
  @FXML
  private TableColumn<ActividadAsignada, Integer> columnaCupo;
  @FXML
  private JFXButton botonReservar;
  private Recepcionista recepcionista;
  DAOActividadAsignadaImpl actividad = new DAOActividadAsignadaImpl();
  DAOActividadProgramadaImpl actividadP = new DAOActividadProgramadaImpl();
  DAOExperienciaEducativaImpl experienciaEdu = new DAOExperienciaEducativaImpl();

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    llenarTabla();
    llenarComboSemana();
    llenarComboExp();
  }

  /**
   * Metoodo que llena una tabla con los datos de las actividades que ya han sido asignadas a los
   * asesores
   */
  public void llenarTabla() {
    try {
      ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadesAsignadas());
      ObservableList<ActividadAsignada> listaActividadesNueva = FXCollections.observableArrayList();
      Date ahora = new Date();
      for (int i = 0; i < listaActividades2.size(); i++) {
        Date fecha = listaActividades2.get(i).getFecha();
        if (fecha.after(ahora)) {
          listaActividadesNueva.add(listaActividades2.get(i));
        }
      }
      columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      columnaAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
      columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
      columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
      columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
      tablaActividades.setItems(listaActividadesNueva);
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
   * Metodo que llena el combo de experiencias educativas con las EE registradas en la base de datos
   */
  public void llenarComboExp() {
    try {
      ObservableList<ExperienciaEducativa> listaExperiencias = FXCollections.observableList(experienciaEdu.obtenerExperiencias());
      comboExperiencia.getItems().addAll(listaExperiencias);

    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }
  }

  /**
   * Metodo que filtra los registros asignados a los asesores por experiencia educativa
   */
  @FXML
  public void filtrarPorExperiencia() {
    int experiencia = comboExperiencia.getValue().getIdExperiencia();
    System.out.println("experiencia en exp " + experiencia);

    try {
      ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadesPorExperiencia(experiencia));
      ObservableList<ActividadAsignada> listaActividadesNueva = FXCollections.observableArrayList();
      Date ahora = new Date();
      for (int i = 0; i < listaActividades2.size(); i++) {
        Date fecha = listaActividades2.get(i).getFecha();
        if (fecha.after(ahora)) {
          listaActividadesNueva.add(listaActividades2.get(i));
        }
      }
      columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      columnaAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
      columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
      columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
      columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
      tablaActividades.setItems(listaActividadesNueva);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }
  }

  /**
   * Metodo que filtra las actividades asignadas a los asesores por un intervalo de tiempo
   */
  @FXML
  public void filtrarPorSemana2() {
    try {
      int experiencia = comboExperiencia.getValue().getIdExperiencia();

      Date fechaMin = comboSemana.getValue().getFechaInicio();
      Date fechaMax = comboSemana.getValue().getFechaFin();
      System.out.println("experiencia en fecha " + experiencia);
      ObservableList<ActividadAsignada> listaActividades2
          = FXCollections.observableList(actividad.obtenerActividadesPorFecha(experiencia, fechaMin, fechaMax));
      columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      columnaAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
      columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
      columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
      columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
      columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
      tablaActividades.setItems(listaActividades2);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "Seleccione una experiencia educativa primero");
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
      Logger.getLogger(IUMenuRecepcionistaController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Recepcionista getRecepcionista() {
    return recepcionista;
  }

  public void setRecepcionista(Recepcionista recepcionista) {
    this.recepcionista = recepcionista;
  }

  @FXML
  public void regresar() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuRecepcionista.fxml"));
    IUMenuRecepcionistaController controller = new IUMenuRecepcionistaController();
    loader.setController(controller);
    controller.setRecepcionista(recepcionista);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) botonReservar.getScene().getWindow();
    mainStage.close();
  }
}

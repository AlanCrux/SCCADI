package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.daoimpl.DAOActividadAsignadaImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.daoimpl.DAOInscripcionImpl;
import logica.daoimpl.DAOReservacionImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Reservacion;
import logica.dominio.Seccion;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IUReservacionActividadesController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private TextField tfMatricula;
  @FXML
  private ComboBox<ExperienciaEducativa> comboExperiencia;
  @FXML
  private TextField tfNombre;
  @FXML
  private TextField tfAsesor;
  @FXML
  private TextField tfHora;
  @FXML
  private TextField tfSala;
  @FXML
  private JFXButton btnReservar;
  @FXML
  private TextField tfFecha;
  @FXML
  private JFXButton btnBuscar;
  @FXML
  private TableView<ActividadAsignada> tablaActividades;

  @FXML
  private TableColumn<ActividadAsignada, String> tbcNombre;

  @FXML
  private TableColumn<ActividadAsignada, String> tbcAsesor;

  @FXML
  private TableColumn<ActividadAsignada, String> tbcFecha;

  @FXML
  private TableColumn<ActividadAsignada, String> tbcCupo;

  private String matricula;

  /**
   * Inicializa el controlador de la ventana IUReservacionActividades
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tbcAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
    tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    tbcCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));

    comboExperiencia.getSelectionModel().selectedItemProperty().addListener(event -> {
      try {
        int idExperiencia = comboExperiencia.getSelectionModel().getSelectedItem().getIdExperiencia();
        llenarTablaActividades(idExperiencia, matricula);
      } catch (Exception e) {
      }
    });

    tablaActividades.getSelectionModel().selectedItemProperty().addListener(event -> {
      if (tablaActividades.getItems().isEmpty()) {
        limpiar();
      } else {
        try {
          extraerDatos(tablaActividades.getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
        }
      }
    });
  }

  /**
   * Extrae los atributos de una actividad y los carga en JTextField en la IU.
   * @param actividad la actividad de la cual se obtienen los datos. 
   */
  private void extraerDatos(ActividadAsignada actividad) {
    tfNombre.setText(actividad.getNombre());
    tfAsesor.setText(actividad.getNombreAsesor());
    tfHora.setText(actividad.getHora().toString());
    tfSala.setText(actividad.getNombreSala());
    tfFecha.setText(actividad.getFecha().toString());
  }

  /**
   * Limpia los cuadros de texto de la interfaz. 
   */
  private void limpiar() {
    tfNombre.clear();
    tfAsesor.clear();
    tfHora.clear();
    tfSala.clear();
    tfFecha.clear();
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz.
   *
   * @param event
   */
  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz.
   *
   * @param event
   */
  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
  }

  /**
   * Ejecuta la acción del botón salir. 
   * @param event evento que desencadena el botón. 
   */
  @FXML
  private void salir(MouseEvent event) {
    Stage stage = (Stage) botonAtras.getScene().getWindow();
    stage.close();
  }

  /**
   * Invoca métodos de capa lógica para insertar la reservación en la BD.
   * @param event evento que desencadena el botón. 
   */
  @FXML
  private void onActionReservar(ActionEvent event) {
    ActividadAsignada actividad = tablaActividades.getSelectionModel().getSelectedItem();
    int idActividadAsignada = actividad.getIdActividadAsignada();
    System.out.println("IDACTIVIDAD: " + idActividadAsignada);
    Reservacion reservacion = new Reservacion();
    reservacion.setIdActividadAsignada(idActividadAsignada);
    reservacion.setMatricula(matricula);
    DAOReservacionImpl dAOReservacionImpl = new DAOReservacionImpl();
    try {
      if (dAOReservacionImpl.insertarReservacion(reservacion)) {
        Mensajes.displayInformation("Éxito", "Actividad reservada");
        limpiar();
        tablaActividades.getItems().clear();
        comboExperiencia.getItems().clear();
        DAOActividadAsignadaImpl daoActividad = new DAOActividadAsignadaImpl();
        daoActividad.actualizarActividadAsignada(descontarCupo(actividad));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      Mensajes.displayWarningAlert("Error", "Servicio no disponible, intente más tarde");
    }
  }

  /**
   * Invoca métodos de capa lógica que permiten obtener las experiencias educativas 
   * (secciones) a las que esta asociado un alumno. 
   * @param event evento que desencadena el botón. 
   */
  @FXML
  private void onActionBuscar(ActionEvent event) {
    matricula = tfMatricula.getText();
    ObservableList<ExperienciaEducativa> experiencias = obtenerExperiencias(obtenerSecciones(matricula));
    if (experiencias.size() > 0) {
      comboExperiencia.setItems(experiencias);
      comboExperiencia.getSelectionModel().selectFirst();
      int idExperiencia = comboExperiencia.getSelectionModel().getSelectedItem().getIdExperiencia();
      llenarTablaActividades(idExperiencia, matricula);
    } else {
      Mensajes.displayInformation("Error", "Alumno no encontrado");
    }
  }

  /**
   * Llena la tabla con actividades disponibles para el alumno en la experiencia seleccionada.
   * @param idExperiencia
   * @param matricula 
   */
  private void llenarTablaActividades(int idExperiencia, String matricula) {
    List<ActividadAsignada> actividades = obtenerActividades(idExperiencia);
    List<Reservacion> reservaciones = obtenerReservaciones(matricula);
    ObservableList<ActividadAsignada> actividadesAsignadas = FXCollections.observableArrayList();

    int dimensiones = actividades.size();
    Date today = new Date();
    for (int i = 0; i < dimensiones; i++) {
      if (!existeReservacion(reservaciones, actividades.get(i)) && 
          actividades.get(i).getCupoMaximo() > 0 && actividades.get(i).getFecha().after(today)) {
        actividadesAsignadas.add(actividades.get(i));
      }
    }

    tablaActividades.setItems(actividadesAsignadas);
    tablaActividades.getSelectionModel().selectFirst();
  }

  /**
   * Obtiene las secciones asociadas a un alumno.
   * @param matricula identificador del alumno.
   * @return lista con las secciones a las cual esta asociado el alumno. 
   */
  public List<Seccion> obtenerSecciones(String matricula) {
    DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
    List<Seccion> listaSecciones = null;
    try {
      listaSecciones = daoInscripcion.obtenerSecciones(matricula);
    } catch (Exception ex) {
      Logger.getLogger(IUReservacionActividadesController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listaSecciones;
  }

  /**
   * Obtiene las experiencia educativas asociadas a una lista de secciones.
   * @param secciones secciones de las que se requiere obtener las experiencias educativas.
   * @return lista de las experiencias educativas correspondientes. 
   */
  public ObservableList<ExperienciaEducativa> obtenerExperiencias(List<Seccion> secciones) {
    DAOExperienciaEducativaImpl daoExp = new DAOExperienciaEducativaImpl();
    ObservableList<ExperienciaEducativa> experiencias = FXCollections.observableArrayList();
    int dimensiones = secciones.size();
    for (int i = 0; i < dimensiones; i++) {
      try {
        experiencias.add(daoExp.obtenerExperiencia(secciones.get(i).getIdExperiencia()));
      } catch (Exception ex) {
        Mensajes.displayWarningAlert("Error", "Servicio no disponible, intente más tarde");
      }
    }
    return experiencias;
  }

  /**
   * Invoca métodos de capa lógica para obtener las actividades asociadas a una experiencia educativa en especifico. 
   * @param idExperiencia identificador de la experiencia.
   * @return Lista con las actividades asociadas. 
   */
  public ObservableList<ActividadAsignada> obtenerActividades(int idExperiencia) {
    DAOActividadAsignadaImpl daoActividad = new DAOActividadAsignadaImpl();
    ObservableList<ActividadAsignada> actividades = FXCollections.observableArrayList();
    try {
      actividades = FXCollections.observableList(daoActividad.obtenerActividadesPorExperiencia(idExperiencia));
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error", "Servicio no disponible, intente más tarde");
    }
    return actividades;
  }

  /**
   * Realiza una disminución de 1 al cupo de una actividad asignada. 
   * @param actividad la actividad que se quiere reducir.
   * @return la misma actividad con el cupo reducido. 
   */
  private ActividadAsignada descontarCupo(ActividadAsignada actividad) {
    actividad.setCupoMaximo(actividad.getCupoMaximo() - 1);
    return actividad;
  }

  /**
   * Permite obtener las reservaciones de un alumno en particular. 
   * @param matricula identificador del alumno. 
   * @return lista de las reservaciones hechas por ese alumno. 
   */
  public List<Reservacion> obtenerReservaciones(String matricula) {
    DAOReservacionImpl daoReservacion = new DAOReservacionImpl();
    List<Reservacion> reservaciones = new ArrayList<>();
    try {
      reservaciones = daoReservacion.obtenerReservacionesPorAlumno(matricula);
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error", "Servicio no disponible, intente más tarde");
    }
    return reservaciones;
  }

  /**
   * Verifica si el alumno ya reservo una actividad de las disponibles
   * @param reservacion lista de reservaciones del alumno
   * @param actividad actividad que va a ser comprobada en la lista. 
   * @return true si existe una reservación con la misma actividad
   */
  private boolean existeReservacion(List<Reservacion> reservacion, ActividadAsignada actividad) {
    int dimensiones = reservacion.size();
    for (int i = 0; i < dimensiones; i++) {
      if (reservacion.get(i).getIdActividadAsignada() == actividad.getIdActividadAsignada()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Muestra la ventana. 
   * @param loader 
   */
  public void mostrarVentana(FXMLLoader loader) {
    try {
      Stage stagePrincipal = new Stage();
      Parent root = (Parent) loader.load();
      Scene scene = new Scene(root);
      stagePrincipal.setScene(scene);
      stagePrincipal.show();
    } catch (IOException ex) {
      Logger.getLogger(IUAdministrarAvisoController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}

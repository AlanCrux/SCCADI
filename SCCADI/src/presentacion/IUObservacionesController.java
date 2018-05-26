package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.daoimpl.DAOAlumnoImpl;
import logica.daoimpl.DAOInscripcionImpl;
import logica.daoimpl.DAOObservacionImpl;
import logica.dominio.Alumno;
import logica.dominio.Asesor;
import logica.dominio.Inscripcion;
import logica.dominio.Observacion;
import logica.dominio.Seccion;
import presentacion.controladores.IUMenuRecepcionistaController;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class IUObservacionesController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private TextField tfAlumno;
  @FXML
  private Label lbNombreAsesor;
  @FXML
  private TextArea taObservacion;
  @FXML
  private Button btnAgregar;
  @FXML
  private ListView<Alumno> lstAlumnos;
  @FXML
  private TextArea taObservaciones;
  @FXML
  private ComboBox<Seccion> comboExperiencias;

  private Asesor asesor;

  /**
   * Inicializa los componentes de la interfaz de usuario IUObservaciones.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lstAlumnos.getSelectionModel().selectedItemProperty().addListener(event -> {
      taObservaciones.clear();
      if (lstAlumnos.getItems().size() < 1) {
        comboExperiencias.setValue(null);
      }
      try {
        Alumno seleccionado = lstAlumnos.getSelectionModel().getSelectedItem();
        System.out.println(seleccionado.getMatricula());
        comboExperiencias.setItems(FXCollections.observableList(obtenerExperiencias(seleccionado.getMatricula())));
      } catch (Exception ex) {
      };
    });

    comboExperiencias.getSelectionModel().selectedItemProperty().addListener(event -> {
      try {
        Alumno seleccionado = lstAlumnos.getSelectionModel().getSelectedItem();
        Seccion seleccionada = comboExperiencias.getSelectionModel().getSelectedItem();
        taObservaciones.setText(obtenerObservaciones(seleccionado.getMatricula(), seleccionada.getNrc()));
      } catch (Exception ex) {
      };
    });

    taObservacion.setOnKeyReleased(event -> {
      Seccion seleccionada = comboExperiencias.getSelectionModel().getSelectedItem();
      if (!taObservacion.getText().trim().isEmpty() && seleccionada != null) {
        btnAgregar.setDisable(false);
      } else {
        btnAgregar.setDisable(true);
      }
    });
  }

  /**
   * Invoca métodos de capa lógica para recuperar las secciones a las que esta inscrito un alumno. 
   * @param matricula identificador del alumno. 
   * @return Lista de secciones a las que esta inscrito el alumno.
   */
  public List<Seccion> obtenerExperiencias(String matricula) {
    List<Seccion> secciones = new ArrayList<>();
    try {
      DAOInscripcionImpl daoIncripcion = new DAOInscripcionImpl();
      secciones = daoIncripcion.obtenerSecciones(matricula);
    } catch (Exception ex) {
      Logger.getLogger(IUObservacionesController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return secciones;
  }

  /**
   * Registra una observación en la base de datos, asociada a un alumno para una sección en particular.
   * @param noPersonal número de personal del asesor que hace la observación.
   * @param nrc identificador de la sección.
   * @param matricula identificador del alumno.
   * @param descripcion el texto de la observación. 
   */
  private void registrarObservacion(int noPersonal, int nrc, String matricula, String descripcion) {
    DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
    DAOObservacionImpl daoObservacion = new DAOObservacionImpl();
    Observacion observacion = new Observacion();
    try {
      Inscripcion inscripcion = daoInscripcion.obtenerInscripcion(matricula, nrc);
      observacion.setFolioInscripcion(inscripcion.getFolioInscripcion());
      observacion.setNoPersonal(noPersonal);
      observacion.setObservacion(descripcion + " - " + asesor.getNombre());
      daoObservacion.insertarObservacion(observacion);
    } catch (Exception ex) {
      ex.printStackTrace();
      Mensajes.displayInformation("Error", "Servicio no disponible");
    }
    taObservacion.clear();
    taObservaciones.setText(obtenerObservaciones(matricula, nrc));
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz.
   * @param event
   */
  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz.
   * @param event
   */
  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
  }

  @FXML
  private void salir(MouseEvent event) {
    Stage mainStage = (Stage) botonAtras.getScene().getWindow();
    mainStage.close();
  }

  /**
   * Describe el comportamiento del sistema al escribir sobre el TextField de búsqueda de alumno. 
   * @param event 
   */
  @FXML
  private void buscarAlumno(KeyEvent event) {
    lstAlumnos.getItems().clear();
    if (!tfAlumno.getText().trim().isEmpty()) {
      lstAlumnos.setItems(FXCollections.observableList(obtenerAlumnos(tfAlumno.getText().trim())));
    }
  }

  /**
   * Asigna valores a variables necesarias para agregar una observación. 
   * @param event 
   */
  @FXML
  private void agregarObservacion(ActionEvent event) {
    Alumno seleccionado = lstAlumnos.getSelectionModel().getSelectedItem();
    Seccion seleccionada = comboExperiencias.getSelectionModel().getSelectedItem();
    registrarObservacion(asesor.getNoPersonal(), seleccionada.getNrc(), seleccionado.getMatricula(), taObservacion.getText());
    obtenerObservaciones(seleccionado.getMatricula(), seleccionada.getNrc());
  }

  /**
   * Obtiene de la base de datos los alumnos que coincide con un criterio. 
   * @param nombre criterio
   * @return lista de alumnos que coinciden
   */
  public List<Alumno> obtenerAlumnos(String nombre) {
    List<Alumno> alumnos = new ArrayList<>();
    DAOAlumnoImpl daoAlumno = new DAOAlumnoImpl();
    try {
      alumnos = daoAlumno.obtenerAlumnosFiltrados(nombre);
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Servicio no disponible");
    }
    return alumnos;
  }

  /**
   * Invoca métodos de capa lógica para obtener observaciones de un alumno en una sección en particular.
   * @param matricula identificador del alumno. 
   * @param nrc identificador de la sección
   * @return observaciones.
   */
  private String obtenerObservaciones(String matricula, int nrc) {
    DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
    DAOObservacionImpl daoObservacion = new DAOObservacionImpl();
    List<Observacion> listaObservaciones = null;
    String observaciones = "";
    try {
      Inscripcion inscripcion = daoInscripcion.obtenerInscripcion(matricula, nrc);
      listaObservaciones = daoObservacion.obtenerObservaciones(inscripcion.getFolioInscripcion());
    } catch (Exception ex) {
      ex.printStackTrace();
      Mensajes.displayInformation("Error", "Servicio no disponible");
    }

    for (int i = 0; i < listaObservaciones.size(); i++) {
      observaciones = observaciones + "\n\n" + listaObservaciones.get(i).getObservacion();
    }
    return observaciones;
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

  public Asesor getAsesor() {
    return asesor;
  }

  public void setAsesor(Asesor asesor) {
    this.asesor = asesor;
  }

}

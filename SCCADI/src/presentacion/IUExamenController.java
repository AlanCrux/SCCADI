package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;
import logica.dominio.Calificacion;
import logica.dominio.Seccion;
import presentacion.controladores.IUMenuRecepcionistaController;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alanc
 */
public class IUExamenController implements Initializable {

  @FXML
  private Label lbExperiencia;
  @FXML
  private Label lbNrc;
  @FXML
  private ImageView botonAtras;
  @FXML
  private TableView<Calificacion> tbExamenes;
  @FXML
  private TableColumn<Calificacion, String> tbcAlumno;
  @FXML
  private TableColumn<Calificacion, String> tbcCalificacion;
  @FXML
  private Button btnGuardar;
  @FXML
  private TextField tfDescripcion;
  @FXML
  private TextField tfTipo;
  @FXML
  private DatePicker datePicker;

  private Seccion seccion;
  private boolean nuevo; 

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void efectoMouseFuera(MouseEvent event) {
  }

  @FXML
  private void efectoMouseSobre(MouseEvent event) {
  }

  @FXML
  private void salir(MouseEvent event) {
  }

  @FXML
  private void guardarExamen(ActionEvent event) {
  }

  private List<Alumno> obtenerAlumnos(int nrc) {
    List<Alumno> alumnos = new ArrayList<>();
    DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
    try {
      alumnos = daoInscripcion.obtenerAlumnos(nrc);
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Servicio no disponible, intente más tarde");
    }
    return alumnos;
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

  public Seccion getSeccion() {
    return seccion;
  }

  public void setSeccion(Seccion seccion) {
    this.seccion = seccion;
  }

  public boolean isNuevo() {
    return nuevo;
  }

  public void setNuevo(boolean nuevo) {
    this.nuevo = nuevo;
  }
  
  

}

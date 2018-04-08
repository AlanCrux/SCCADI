package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logica.dominio.Recepcionista;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IUMenuRecepcionistaController implements Initializable {

  @FXML
  private JFXButton btnAlumnos;
  @FXML
  private JFXButton btnActividades;
  @FXML
  private Label lblNombre;
  @FXML
  private Hyperlink hpCerrarSesion;
  private Recepcionista recepcionista; 
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lblNombre.setText(recepcionista.getNombre());
  }  

  @FXML
  private void abrirAlumnos(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUInscripcion.fxml"));
    IUInscripcionController controller = new IUInscripcionController();
    loader.setController(controller);
    controller.setRecepcionista(recepcionista);
    controller.setOrigen(true);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnAlumnos.getScene().getWindow();
    mainStage.close();
  }

  @FXML
  private void abrirActividades(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUConsultarDetalleSemanaActividades.fxml"));
    IUConsultarDetalleSemanaActividadesController controller = new IUConsultarDetalleSemanaActividadesController();
    loader.setController(controller);
    controller.setRecepcionista(recepcionista);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnAlumnos.getScene().getWindow();
    mainStage.close();
  }

  public Recepcionista getRecepcionista() {
    return recepcionista;
  }

  public void setRecepcionista(Recepcionista recepcionista) {
    this.recepcionista = recepcionista;
  }
  
  @FXML
  private void cerrarSesion(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IULogin.fxml"));
    IULoginController controller = new IULoginController();
    loader.setController(controller);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnActividades.getScene().getWindow();
    mainStage.close();
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
}

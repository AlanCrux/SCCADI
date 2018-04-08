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
import logica.dominio.Coordinador;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IUMenuCoordinadorController implements Initializable {

  @FXML
  private JFXButton btnAlumnos;
  @FXML
  private JFXButton btnActividades;
  @FXML
  private Label lblNombre;
  @FXML
  private Hyperlink hpCerrarSesion;

  private Coordinador coordinador; 
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lblNombre.setText(coordinador.getNombre());
  }  

  @FXML
  private void abrirAlumnos(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUAdministrarAlumnos.fxml"));
    IUAdministrarAlumnosController controller = new IUAdministrarAlumnosController();
    loader.setController(controller);
    controller.setCoordinador(coordinador);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnActividades.getScene().getWindow();
    mainStage.close();
  }

  @FXML
  private void abrirActividades(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUProgramacionActividades.fxml"));
    IUProgramacionActividadesController controller = new IUProgramacionActividadesController();
    loader.setController(controller);
    controller.setCoordinador(coordinador);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnActividades.getScene().getWindow();
    mainStage.close();
  }

  public Coordinador getCoordinador() {
    return coordinador;
  }

  public void setCoordinador(Coordinador coordinador) {
    this.coordinador = coordinador;
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
      Logger.getLogger(IUMenuCoordinadorController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
}

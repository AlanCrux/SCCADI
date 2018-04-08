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
import logica.dominio.Asesor;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IUMenuAsesorController implements Initializable {

  @FXML
  private Label lblNombre;
  @FXML
  private Hyperlink hpCerrarSesion;
  @FXML
  private JFXButton btnActividades;
  @FXML
  private JFXButton btnSecciones;

  private Asesor asesor;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lblNombre.setText(asesor.getNombre());
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

  @FXML
  private void abrirActividades(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUConsultarActividadesAsignadas.fxml"));
    IUConsultarActividadesAsignadasController controller = new IUConsultarActividadesAsignadasController();
    loader.setController(controller);
    controller.setAsesorCreado(asesor);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnSecciones.getScene().getWindow();
    mainStage.close();
    
  }

  @FXML
  private void abrirSecciones(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUSecciones.fxml"));
    IUSeccionesController controller = new IUSeccionesController();
    loader.setController(controller);
    controller.setAsesor(asesor);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) btnSecciones.getScene().getWindow();
    mainStage.close();
  }

  public Asesor getAsesor() {
    return asesor;
  }

  public void setAsesor(Asesor asesor) {
    this.asesor = asesor;
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

}

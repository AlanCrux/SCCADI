package presentacion.controladores;

import logica.dominio.Coordinador;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.dominio.Asesor;
import logica.dominio.Recepcionista;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IULoginController implements Initializable {

  @FXML
  private JFXButton btnIngresar;
  @FXML
  private TextField tfNumeroPersonal;
  @FXML
  private Hyperlink hpCadi;

  private Asesor objAsesor;
  private Recepcionista objRecepcionista;
  private Coordinador objCoordinador;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void ingresar(ActionEvent event) {
    String numPersonal = tfNumeroPersonal.getText();
    if (verificarAsesor(numPersonal)) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuAsesor.fxml"));
      IUMenuAsesorController controller = new IUMenuAsesorController();
      loader.setController(controller);
      controller.setAsesor(objAsesor);
      controller.mostrarVentana(loader);
      Stage mainStage = (Stage) btnIngresar.getScene().getWindow();
      mainStage.close();
    } else if (verificarCoordinador(numPersonal)) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuCoordinador.fxml"));
      IUMenuCoordinadorController controller = new IUMenuCoordinadorController();
      loader.setController(controller);
      controller.setCoordinador(objCoordinador);
      controller.mostrarVentana(loader);
      Stage mainStage = (Stage) btnIngresar.getScene().getWindow();
      mainStage.close();
    } else if (verificarRecepcionista(numPersonal)) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuRecepcionista.fxml"));
      IUMenuRecepcionistaController controller = new IUMenuRecepcionistaController();
      loader.setController(controller);
      controller.setRecepcionista(objRecepcionista);
      controller.mostrarVentana(loader);
      Stage mainStage = (Stage) btnIngresar.getScene().getWindow();
      mainStage.close();
    }
  }

  private boolean verificarAsesor(String numPersonal) {
    if (numPersonal.equals("asesor")) {
      Asesor asesor = new Asesor(1234, "Jose Antonio Martinez Salazar", "pepedD@gmail.com");
      objAsesor = asesor; 
      return true;
    } else {
      return false;
    }
  }

  private boolean verificarCoordinador(String numPersonal) {
    if (numPersonal.equals("coordinador")) {
      Coordinador coordinador = new Coordinador(1234, "José Alí Valdivia Ruiz", "ppjavr@gmail.com");
      objCoordinador = coordinador;
      return true;
    } else {
      return false;
    }
  }

  private boolean verificarRecepcionista(String numPersonal) {
    if (numPersonal.equals("recepcionista")) {
      Recepcionista recepcionista = new Recepcionista(1234, "Miguel Alejandro Cámara Árciga", "arcamsoft@gmail.com");
      objRecepcionista = recepcionista;
      return true;
    } else {
      return false;
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
      Logger.getLogger(IULoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}

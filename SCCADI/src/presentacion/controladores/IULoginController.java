package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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
  
  private boolean asesor; 
  private boolean coordinador; 
  private boolean recepcionista; 

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  

  @FXML
  private void ingresar(ActionEvent event) {
    
  }
  
  private void verificarAsesor(String numPersonal){
    
  }
  
  private void verificarCoordinador(String numPersonal){
    
  }
  
  private void verificarRecepcionista(String numPersonal){
    
  }
  
}

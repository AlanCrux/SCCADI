package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Alan Yoset Garcia Cruz
 */
public class IUFichaAlumnoController implements Initializable {

  @FXML
  private ImageView ivFotografia = new ImageView();
  @FXML
  private Label lbMatricula = new Label();
  @FXML
  private Label lbNombre = new Label();
  @FXML
  private Label lbCorreo = new Label();

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    ivFotografia.setFitWidth(60);
    ivFotografia.setFitHeight(80);
  }

  public Image getIvFotografia() {
    return ivFotografia.getImage();
  }

  public void setIvFotografia(Image ivFotografia) {
    this.ivFotografia.setImage(ivFotografia);
  }

  public String getLbMatricula() {
    return lbMatricula.getText();
  }

  public void setLbMatricula(String matricula) {
    this.lbMatricula.setText(matricula);
  }

  public String getLbNombre() {
    return lbNombre.getText();
  }

  public void setLbNombre(String nombre) {
    this.lbNombre.setText(nombre);
  }

  public String getLbCorreo() {
    return lbCorreo.getText();
  }

  public void setLbCorreo(String correo) {
    this.lbCorreo.setText(correo);
  }

}

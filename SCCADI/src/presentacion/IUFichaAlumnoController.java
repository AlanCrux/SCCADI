package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Alan Yoset Garcia Cruz
 */
public class IUFichaAlumnoController implements Initializable {

    @FXML
    private ImageView ivFotografia;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public ImageView getIvFotografia() {
        return ivFotografia;
    }

    public void setIvFotografia(ImageView ivFotografia) {
        this.ivFotografia = ivFotografia;
    }

    public Label getLbMatricula() {
        return lbMatricula;
    }

    public void setLbMatricula(Label lbMatricula) {
        this.lbMatricula = lbMatricula;
    }

    public Label getLbNombre() {
        return lbNombre;
    }

    public void setLbNombre(Label lbNombre) {
        this.lbNombre = lbNombre;
    }

    public Label getLbCorreo() {
        return lbCorreo;
    }

    public void setLbCorreo(Label lbCorreo) {
        this.lbCorreo = lbCorreo;
    }
    
    
}

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.daoimpl.DAOAsesorImpl;
import logica.daoimpl.DAOCoordinadorImpl;
import logica.daoimpl.DAORecepcionistaImpl;
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
    @FXML
    private TextField tfContrasena;
    @FXML
    private Label lbAdvertencia;

    private Asesor objAsesor;
    private Recepcionista objRecepcionista;
    private Coordinador objCoordinador;

    DAOAsesorImpl asesorDao = new DAOAsesorImpl();
    DAOCoordinadorImpl coordinadorDao = new DAOCoordinadorImpl();
    DAORecepcionistaImpl recepcionistaDao = new DAORecepcionistaImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hpCadi = new Hyperlink("https://www.uv.mx/portalcadi/");
    }

    @FXML
    private void ingresar(ActionEvent event) {
        lbAdvertencia.setVisible(false);
        int numPersonal = Integer.parseInt(tfNumeroPersonal.getText());
        String contrasena = tfContrasena.getText();
        if (verificarAsesor(numPersonal, contrasena)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuAsesor.fxml"));
            IUMenuAsesorController controller = new IUMenuAsesorController();
            loader.setController(controller);
            controller.setAsesor(objAsesor);
            controller.mostrarVentana(loader);
            Stage mainStage = (Stage) btnIngresar.getScene().getWindow();
            mainStage.close();
        } else if (verificarCoordinador(numPersonal, contrasena)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuCoordinador.fxml"));
            IUMenuCoordinadorController controller = new IUMenuCoordinadorController();
            loader.setController(controller);
            controller.setCoordinador(objCoordinador);
            controller.mostrarVentana(loader);
            Stage mainStage = (Stage) btnIngresar.getScene().getWindow();
            mainStage.close();
        } else if (verificarRecepcionista(numPersonal, contrasena)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuRecepcionista.fxml"));
            IUMenuRecepcionistaController controller = new IUMenuRecepcionistaController();
            loader.setController(controller);
            controller.setRecepcionista(objRecepcionista);
            controller.mostrarVentana(loader);
            Stage mainStage = (Stage) btnIngresar.getScene().getWindow();
            mainStage.close();
        } else {
            lbAdvertencia.setVisible(true);
        }
    }

    private boolean verificarAsesor(int numPersonal, String contrasena) {

        try {
            if (numPersonal == asesorDao.obtenerAsesor(numPersonal, contrasena).getNoPersonal()
                    && contrasena.equals(asesorDao.obtenerAsesor(numPersonal, contrasena).getContrasena())) {
                objAsesor = asesorDao.obtenerAsesor(numPersonal, contrasena);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    private boolean verificarCoordinador(int numPersonal, String contrasena) {
        try {
            if (numPersonal == coordinadorDao.obtenerCoordinador(numPersonal, contrasena).getNoPersonal()
                    && contrasena.equals(coordinadorDao.obtenerCoordinador(numPersonal, contrasena).getContrasena())) {
                objCoordinador = coordinadorDao.obtenerCoordinador(numPersonal, contrasena);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    private boolean verificarRecepcionista(int numPersonal, String contrasena) {
        try {
            if (numPersonal == recepcionistaDao.obtenerRecepcionista(numPersonal, contrasena).getNoPersonal()
                    && contrasena.equals(recepcionistaDao.obtenerRecepcionista(numPersonal, contrasena).getContrasena())) {
                objRecepcionista = recepcionistaDao.obtenerRecepcionista(numPersonal, contrasena);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
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

package presentacion.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.daoimpl.DAOAvisoImpl;
import logica.dominio.Aviso;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alancrux
 */
public class IUConsultarAvisosController implements Initializable {

    @FXML
    private ListView<Aviso> lstAvisos;
    @FXML
    private TextField tfInicio;
    @FXML
    private TextField tfFinal;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ImageView botonAtras;

    /**
     * Inicializa los componentes gráficos de la ventana IUConsultarAvisos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Aviso> avisos = FXCollections.observableList(obtenerAvisos());
        cargarLista(avisos);

        lstAvisos.setPlaceholder(new Label("No hay avisos por el momento"));
        lstAvisos.getSelectionModel().selectedItemProperty().addListener(event -> {
            if (lstAvisos.getItems().isEmpty()) {
                limpiar();
                lstAvisos.setPlaceholder(new Label("No hay avisos por el momento"));
            } else {
                try {
                    cargarAviso(lstAvisos.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                };
            }
        });
    }

    /**
     * Da un efecto gráfico a un componente de la interfaz.
     *
     * @param event
     */
    @FXML
    public void efectoMouseSobre(MouseEvent event) {
        ImageView ficha = (ImageView) event.getSource();
        ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
    }

    /**
     * Da un efecto gráfico a un componente de la interfaz.
     *
     * @param event
     */
    @FXML
    public void efectoMouseFuera(MouseEvent event) {
        ImageView ficha = (ImageView) event.getSource();
        ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
    }

    /**
     * Describe el comportamiento del sistema al oprimir el botón salir.
     *
     * @param event
     */
    @FXML
    private void salir(MouseEvent event) {
        Stage stage = (Stage) botonAtras.getScene().getWindow();
        stage.close();
    }

    /**
     * Invoca un método de capa lógica para recuperar los Avisos de la base de
     * datos.
     *
     * @return ObservableList cargado con los Avisos recuperados.
     */
    private ObservableList<Aviso> obtenerAvisos() {
        DAOAvisoImpl daoAviso = new DAOAvisoImpl();
        ObservableList<Aviso> listaAvisos = null;
        try {
            listaAvisos = FXCollections.observableList(daoAviso.obtenerAvisos());
        } catch (Exception ex) {
            String mensaje = "Servicio no disponible por el momento";
            Mensajes.displayInformation("Error", mensaje);
        }

        return listaAvisos;
    }

    /**
     * Extrae los datos de un Aviso y los carga en componentes de interfaz.
     *
     * @param aviso el aviso del cual se quieren extraer los datos.
     */
    private void cargarAviso(Aviso aviso) {
        tfInicio.setText(aviso.getFechaInicio() + "");
        tfFinal.setText(aviso.getFechaFin() + "");
        taDescripcion.setText(aviso.getContenido());
    }

    /**
     * Carga en la el componente gráfico la lista de avisos que se le da como
     * parametro
     *
     * @param avisos La lista de avisos que cargara en la interfaz gráfica
     */
    public void cargarLista(ObservableList<Aviso> avisos) {
        lstAvisos.getItems().clear();
        lstAvisos.setItems(avisos);
    }

    /**
     * Limpia los elementos de interfaz.
     */
    private void limpiar() {
        taDescripcion.clear();
        tfFinal.clear();
        tfInicio.clear();
    }

    /**
     * Muestra la ventana.
     *
     * @param loader
     */
    public void mostrarVentana(FXMLLoader loader) {
        try {
            Stage stagePrincipal = new Stage();
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(IUConsultarAvisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;
import logica.dominio.Asesor;
import logica.dominio.ExperienciaEducativa;
import utilerias.Mensajes;

/**
 * Controlador de la ventana IUAlumnos Nos permite visualizar los alumnos
 * inscritos a una sección determinada.
 *
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class IUAlumnosController implements Initializable {

    @FXML
    private ImageView botonAtras;
    @FXML
    private Label lbExperiencia;
    @FXML
    private Label lbNrc;
    @FXML
    private Label lbNumAlumnos;
    @FXML
    private Label lbPeriodo;
    @FXML
    private ImageView imageViewFoto;
    @FXML
    private ListView<Alumno> lst_Alumnos;
    @FXML
    private JFXButton btnConsultar;
    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbCorreo;

    private Asesor asesor;
    private ExperienciaEducativa experiencia;
    private String periodo;
    private int nrc;

    /**
     * Inicializa los componentes de la ventana.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbExperiencia.setText("EE: " + experiencia.getNombre());
        lbNrc.setText("NRC: " + nrc);

        ObservableList<Alumno> alumnos = cargarAlumnos(nrc);
        lbNumAlumnos.setText("Alumnos: " + alumnos.size());
        lbPeriodo.setText(periodo);
        botonAtras.setOnMouseClicked(event -> {
            regresar();
        });
        lst_Alumnos.setItems(alumnos);

        lst_Alumnos.getSelectionModel().selectedItemProperty().addListener(event -> {
            if (lst_Alumnos.getItems().isEmpty()) {
                //imageViewFoto.setImage(new Image("/recursos/imagenes/IMG_ALUMNO.jpg"));
                btnConsultar.setDisable(true);
                btnRegistrar.setDisable(true);
                lbCorreo.setText("");
                lbMatricula.setText("");
            } else {
                btnConsultar.setDisable(false);
                btnRegistrar.setDisable(false);
                try {
                    Alumno seleccionado = lst_Alumnos.getSelectionModel().getSelectedItem();
                    imageViewFoto.setImage(blobToImage(seleccionado.getFotografia()));
                    lbCorreo.setText(seleccionado.getCorreo());
                    lbMatricula.setText(seleccionado.getMatricula());
                } catch (Exception ex) {
                };
            }
        });
        lst_Alumnos.getSelectionModel().selectFirst();
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
            stagePrincipal.setResizable(false);
            stagePrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(IUAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga en el listview los alumnos asociados a la sección.
     *
     * @param nrc el identificador de la sección de la cual se quieren recuperar
     * los alumnos.
     * @return el tamaño de la lista de alumnos recuperados.
     */
    public ObservableList cargarAlumnos(int nrc) {
        ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
        DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
        try {
            alumnos = FXCollections.observableList(daoInscripcion.obtenerAlumnos(nrc));
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
        }
        return alumnos;
    }

    /**
     * Convierte un objeto tipo Blob a Image.
     *
     * @param blobImage el Blob que se quiere convertir.
     * @return el objeto Image generado.
     */
    public Image blobToImage(Blob blobImage) {
        InputStream in;
        BufferedImage bfImage = null;
        try {
            in = blobImage.getBinaryStream();
            bfImage = ImageIO.read(in);
        } catch (SQLException | IOException ex) {
            System.err.println("Error al procesar la imagen");
        }
        Image image = SwingFXUtils.toFXImage(bfImage, null);
        return image;
    }

    /**
     * Cierra la ventana actual y regresa a la ventana IUSecciones.
     */
    public void regresar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUSecciones.fxml"));
        IUSeccionesController controller = new IUSeccionesController();
        loader.setController(controller);
        controller.setAsesor(asesor);
        controller.mostrarVentana(loader);
        Stage mainStage = (Stage) botonAtras.getScene().getWindow();
        mainStage.close();
    }

    public ExperienciaEducativa getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaEducativa experiencia) {
        this.experiencia = experiencia;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }

    /**
     * Cambia la imagen de un ImageView.
     *
     * @param event evento activado.
     */
    @FXML
    public void efectoMouseSobre(MouseEvent event) {
        ImageView ficha = (ImageView) event.getSource();
        ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
    }

    /**
     * Cambia la imagen de un ImageView.
     *
     * @param event evento activado.
     */
    @FXML
    public void efectoMouseFuera(MouseEvent event) {
        ImageView ficha = (ImageView) event.getSource();
        ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @FXML
    void onActionConsultar(ActionEvent event) {

    }

    @FXML
    void onActionRegistrar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IURegistroProgreso.fxml"));
        IURegistroProgresoController controller = new IURegistroProgresoController();
        loader.setController(controller);
        controller.setAlumno(lst_Alumnos.getSelectionModel().getSelectedItem());
        controller.setAsesor(asesor);
        controller.setExperiencia(experiencia);
        controller.setNrc(nrc);
        controller.mostrarVentana(loader);
    }

}

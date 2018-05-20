package presentacion.controladores;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOInscripcionImpl;
import logica.daoimpl.DAOProgresoImpl;
import logica.dominio.Alumno;
import logica.dominio.Asesor;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Inscripcion;
import logica.dominio.Progreso;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García Cruz
 */
public class IURegistroProgresoController implements Initializable {

    @FXML
    private ImageView imageViewFoto;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombre;
    @FXML
    private ImageView botonAtras;
    @FXML
    private Button botonRegistrar;
    @FXML
    private TextField tfBitacoras;
    @FXML
    private TextField tfReflexiones;
    @FXML
    private TextField tfSeguimientos;
    @FXML
    private TextField tfAutoevaluaciones;
    @FXML
    private Label lbBitacoras;
    @FXML
    private Label lbReflexiones;
    @FXML
    private Label lbSeguimientos;
    @FXML
    private Label lbAutoevaluaciones;

    private boolean existeProgreso;
    private Progreso progresoActual;

    private int nrc;
    private ExperienciaEducativa experiencia;
    private Asesor asesor;
    private Alumno alumno;
    private Inscripcion inscripcion;

    /**
     * Inicializa el controlador de la ventana IURegistroProgreso
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbMatricula.setText(alumno.getMatricula());
        lbNombre.setText(alumno.getNombre());
        imageViewFoto.setImage(blobToImage(alumno.getFotografia()));
        botonRegistrar.setDisable(true);
        inscripcion = obtenerInscripcion();

        tfAutoevaluaciones.setOnKeyReleased(event -> {
            if (verificarLimite()) {
                botonRegistrar.setDisable(true);
            } else {
                botonRegistrar.setDisable(false);
            }
        });

        tfBitacoras.setOnKeyReleased(event -> {
            if (verificarLimite()) {
                botonRegistrar.setDisable(true);
            } else {
                botonRegistrar.setDisable(false);
            }
        });

        tfReflexiones.setOnKeyReleased(event -> {
            if (verificarLimite()) {
                botonRegistrar.setDisable(true);
            } else {
                botonRegistrar.setDisable(false);
            }
        });

        tfSeguimientos.setOnKeyReleased(event -> {
            if (verificarLimite()) {
                botonRegistrar.setDisable(true);
            } else {
                botonRegistrar.setDisable(false);
            }
        });

        progresoActual = obtenerProgreso(inscripcion.getFolioInscripcion());
        if (progresoActual != null) {
            existeProgreso = true;
            cargarProgreso(progresoActual);
        }

        cargarMaximos();
    }

    /**
     * Describe el comportamiento del sistema al oprimir el botón registrar
     * @param event 
     */
    @FXML
    private void onActionRegistrar(ActionEvent event) {
        if (existeProgreso) {
            if (actualizarProgreso(progresoActual.getIdProgreso())) {
                Mensajes.displayInformation("Éxito", "Se ha registrado el progreso");
            }
        } else {
            if (insertarProgreso(inscripcion.getFolioInscripcion())) {
                Mensajes.displayInformation("Éxito", "Se ha registrado el progreso");
            }
        }
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
     * @param event 
     */
    @FXML
    private void salir(MouseEvent event) {
        Stage mainStage = (Stage) botonAtras.getScene().getWindow();
        mainStage.close();
    }

    /**
     * Invoca un método de capa lógica para obtener el progreso de un alumno
     * para una experiencia educativa en especifico. 
     * @param folioInscripcion folio de la inscripción asociada al progreso. 
     * @return el progreso obtenido de la BD.
     */
    public Progreso obtenerProgreso(int folioInscripcion) {
        Progreso progreso = null;
        DAOProgresoImpl daoProgreso = new DAOProgresoImpl();
        try {
            progreso = daoProgreso.obtenerProgreso(folioInscripcion);
        } catch (Exception ex) {
            Mensajes.displayInformation("Error", "Servicio no disponible por el momento");
        }
        return progreso;
    }

    /**
     * Invoca un método de capa lógica para insertar en la BD el progreso capturado en la interfaz.
     * @param folioInscripcion el folio de la inscripción asociada al progreso.
     * @return true si se inserta correctamente
     */
    public boolean insertarProgreso(int folioInscripcion) {
        int numAutoevaluaciones = 0;
        if (!tfAutoevaluaciones.getText().trim().isEmpty()) {
            numAutoevaluaciones = Integer.parseInt(tfAutoevaluaciones.getText());
        }

        int numBitacoras = 0;
        if (!tfBitacoras.getText().trim().isEmpty()) {
            numBitacoras = Integer.parseInt(tfBitacoras.getText());
        }

        int numReflexiones = 0;
        if (!tfReflexiones.getText().trim().isEmpty()) {
            numReflexiones = Integer.parseInt(tfReflexiones.getText());
        }

        int numSeguimientos = 0;
        if (!tfSeguimientos.getText().trim().isEmpty()) {
            numSeguimientos = Integer.parseInt(tfSeguimientos.getText());
        }

        Progreso progreso = new Progreso();
        progreso.setFolioInscripcion(folioInscripcion);
        progreso.setNumAutoevaluaciones(numAutoevaluaciones);
        progreso.setNumReflexiones(numReflexiones);
        progreso.setNumBitacoras(numBitacoras);
        progreso.setNumSeguimiento(numSeguimientos);

        DAOProgresoImpl daoProgreso = new DAOProgresoImpl();

        try {
            daoProgreso.insertarProgreso(progreso);
            return true;
        } catch (Exception ex) {
            Mensajes.displayInformation("Error", "Servicio no disponible por el momento");
        }
        return false;
    }

    /**
     * Invoca un método de capa lógica para actualizar el progreso de un alumno en especifico. 
     * @param idProgreso el identificador del progreso que se quiere actualizar
     * @return true si se actualizo correctamente
     */
    public boolean actualizarProgreso(int idProgreso) {
        int numAutoevaluaciones = 0;
        if (!tfAutoevaluaciones.getText().trim().isEmpty()) {
            numAutoevaluaciones = Integer.parseInt(tfAutoevaluaciones.getText());
        }

        int numBitacoras = 0;
        if (!tfBitacoras.getText().trim().isEmpty()) {
            numBitacoras = Integer.parseInt(tfBitacoras.getText());
        }

        int numReflexiones = 0;
        if (!tfReflexiones.getText().trim().isEmpty()) {
            numReflexiones = Integer.parseInt(tfReflexiones.getText());
        }

        int numSeguimientos = 0;
        if (!tfSeguimientos.getText().trim().isEmpty()) {
            numSeguimientos = Integer.parseInt(tfSeguimientos.getText());
        }

        Progreso progreso = new Progreso();
        progreso.setIdProgreso(idProgreso);
        progreso.setNumAutoevaluaciones(numAutoevaluaciones);
        progreso.setNumReflexiones(numReflexiones);
        progreso.setNumBitacoras(numBitacoras);
        progreso.setNumSeguimiento(numSeguimientos);

        DAOProgresoImpl daoProgreso = new DAOProgresoImpl();
        try {
            daoProgreso.actualizarProgreso(progreso);
            return true;
        } catch (Exception ex) {
            Mensajes.displayInformation("Error", "Servicio no disponible por el momento");
        }

        return false;
    }

    /**
     * Carga en componentes de interfaz gráfica los datos obtenidos del progreso.
     * @param progreso el progreso del cual se quieren cargar los datos. 
     */
    public void cargarProgreso(Progreso progreso) {
        tfBitacoras.setText(progreso.getNumBitacoras() + "");
        tfReflexiones.setText(progreso.getNumReflexiones() + "");
        tfSeguimientos.setText(progreso.getNumSeguimiento() + "");
        tfAutoevaluaciones.setText(progreso.getNumAutoevaluaciones() + "");
    }

    /**
     * Carga componentes de interfaz gráfica con los datos obtenidos de la experiencia educativa.
     */
    public void cargarMaximos() {
        lbBitacoras.setText("Max: "+experiencia.getNumBitacoras() + "");
        lbReflexiones.setText("Max: "+experiencia.getNumReflexiones() + "");
        lbSeguimientos.setText("Max: "+experiencia.getNumSeguimiento() + "");
        lbAutoevaluaciones.setText("Max: "+experiencia.getNumAutoevaluaciones() + "");
    }

    /**
     * Este metodo convierte la cadena binarios que arroja la base de datos en
     * una imagen
     *
     * @param blobImage parametro de tipo Blolb que representa una imagen en
     * mysql
     * @return objeto de tipo Image
     */
    public Image blobToImage(Blob blobImage) {
        InputStream in = null;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public ExperienciaEducativa getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaEducativa experiencia) {
        this.experiencia = experiencia;
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
            Logger.getLogger(IURegistroProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Invoca un método de capa lógica para obtener la inscripción correspondiente a un nrc y 
     * matricula especificos
     * @return la inscripción recuperada
     */
    public Inscripcion obtenerInscripcion() {
        DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
        Inscripcion inscrip = new Inscripcion();
        try {
            inscrip = daoInscripcion.obtenerInscripcion(alumno.getMatricula(), nrc);
        } catch (Exception ex) {
            Mensajes.displayInformation("Error", "Servicio no disponible por el momento");
        }
        return inscrip;
    }

    /**
     * Verifica que los valores ingresados por el usuario no superen los valores limite
     * de la experiencia educativa.
     * @return true si algún valor supera el límite.
     */
    public boolean verificarLimite() {
        int numAutoevaluaciones = 0;
        int numBitacoras = 0;
        int numReflexiones = 0;
        int numSeguimientos = 0;

        if (!tfAutoevaluaciones.getText().trim().isEmpty()) {
            numAutoevaluaciones = Integer.parseInt(tfAutoevaluaciones.getText());
        }

        if (!tfBitacoras.getText().trim().isEmpty()) {
            numBitacoras = Integer.parseInt(tfBitacoras.getText());
        }

        if (!tfSeguimientos.getText().trim().isEmpty()) {
            numSeguimientos = Integer.parseInt(tfSeguimientos.getText());
        }

        if (!tfReflexiones.getText().trim().isEmpty()) {
            numReflexiones = Integer.parseInt(tfReflexiones.getText());
        }

        if (numAutoevaluaciones > experiencia.getNumAutoevaluaciones()) {
            tfAutoevaluaciones.setStyle("-fx-border-color: red;");
            return true;
        } else {
            tfAutoevaluaciones.setStyle(null);
        }

        if (numBitacoras > experiencia.getNumBitacoras()) {
            tfBitacoras.setStyle("-fx-border-color: red;");
            return true;
        } else {
            tfBitacoras.setStyle(null);
        }

        if (numReflexiones > experiencia.getNumReflexiones()) {
            tfReflexiones.setStyle("-fx-border-color: red;");
            return true;
        } else {
            tfReflexiones.setStyle(null);
        }

        if (numSeguimientos > experiencia.getNumSeguimiento()) {
            tfSeguimientos.setStyle("-fx-border-color: red;");
            return true;
        } else {
            tfSeguimientos.setStyle(null);
        }

        return false;
    }
}

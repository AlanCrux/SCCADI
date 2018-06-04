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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOAlumnoImpl;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;
import logica.dominio.Coordinador;
import utilerias.Mensajes;

/**
 * Controller de la ventana IUAdministrarAlumnos, permite consultar, editar o
 * elimina a un alumno registrado en la base de datos
 *
 * @author Esmeralda
 * @version 1.0
 */
public class IUAdministrarAlumnosController implements Initializable {
    
    @FXML
    private ImageView botonRegresar;
    @FXML
    private TextField textBuscarAlumno;
    @FXML
    private TableView<Alumno> tablaAlumnos;
    @FXML
    private TableColumn<Alumno, String> columnaMatricula;
    @FXML
    private TableColumn<Alumno, String> columnaNombre;
    @FXML
    private TableColumn<Alumno, String> columnaCorreo;
    @FXML
    private TableColumn<Alumno, String> columnaPrograma;
    @FXML
    private TextField textMatricula;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textPrograma;
    @FXML
    private TextField textContactoEmergencia;
    @FXML
    private TextField textTelefonoEmergencia;
    @FXML
    private ImageView imageFotoAlumno;
    @FXML
    private JFXButton botonEditar;
    @FXML
    private JFXButton botonEliminar;
    @FXML
    private JFXButton botonAgregar;
    
    ObservableList<Alumno> listaAlumno = null;
    DAOAlumnoImpl alumno = new DAOAlumnoImpl();
    DAOInscripcionImpl inscripcion = new DAOInscripcionImpl();
    
    private Coordinador coordinador;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTabla();
        tablaAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Alumno> observable, Alumno oldValue, Alumno newValue) -> {
            try{
                cargarAlumno();
            }catch(Exception e){
                
            }
            
        });
        
        botonRegresar.setOnMouseClicked(event -> {
            regresar();
        });
        
        textBuscarAlumno.setOnKeyReleased(event -> {
            try {
                if (textBuscarAlumno.getText().trim().isEmpty()) {
                    tablaAlumnos.setItems(listaAlumno);
                } else {
                    tablaAlumnos.setItems(filtrarTabla(textBuscarAlumno.getText()));
                    tablaAlumnos.getSelectionModel().selectFirst();
                    
                }
            } catch (Exception e) {
                
            }
        });
    }
    
    @FXML
    public void botonAgregar() {
        
    }
    
    private ObservableList<Alumno> filtrarTabla(String criterio) {
        int size = listaAlumno.size();
        ObservableList<Alumno> filtrada = FXCollections.observableArrayList();
        for (int i = 0; i < listaAlumno.size(); i++) {
            if (listaAlumno.get(i).getNombre().trim().contains(criterio.trim())) {
                filtrada.add(listaAlumno.get(i));
            }
        }
        return filtrada;
    }

    /**
     * Este metodo carga la informacion del alumno seleccionado en los cuadros
     * de texto con su respectiva imagen
     */
    public void cargarAlumno() {
        Alumno seleccion = tablaAlumnos.getSelectionModel().getSelectedItem();
        textNombre.setText(seleccion.getNombre());
        textCorreo.setText(seleccion.getCorreo());
        textMatricula.setText(seleccion.getMatricula());
        textPrograma.setText(seleccion.getProgramaEducativo());
        textContactoEmergencia.setText(seleccion.getContactoEmergencia());
        textTelefonoEmergencia.setText(seleccion.getNumeroEmergencia());
        imageFotoAlumno.setImage(blobToImage(seleccion.getFotografia()));
        
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

    /**
     * Este metodo permite eliminar a un alumno registrado de la base de datos
     */
    @FXML
    public void eliminarAlumno() {
        try {
            Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
            if (alumno.eliminarAlumno(seleccionado.getMatricula())) {
                llenarTabla();
                Mensajes.displayInformation("Eliminacion", "Eliminación exitosa");
                textMatricula.setText("");
                textNombre.setText("");
                textCorreo.setText("");
                textPrograma.setText("");
                textContactoEmergencia.setText("");
                textTelefonoEmergencia.setText("");
                imageFotoAlumno.setImage(new Image("/recursos/imagenes/IMG_ALUMNO.jpg"));
            }            
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "Seleccione un elemento de la tabla");
        }
    }

    /**
     * Este metodo nos permite editar la informacion de un alumno registrado en
     * la base de datos
     */
    @FXML
    public void editarAlumno() {
        try {
            if (botonEditar.getText().equals("Editar")) {
                habilitarCuadros();
                textMatricula.setEditable(false);
                botonEditar.setText("Guardar");
            } else if (validarCamposVacios()) {
                Mensajes.displayWarningAlert("Campos vacios", "Existen campos vacios por favor llenarlos");
               
            } else {
                if (alumno.actualizarAlumno(obtenerDatos())) {
                    llenarTabla();
                    deshabilitarCuadros();
                    botonEditar.setText("Editar");
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Confirmación");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Actualización exitosa");
                    alerta.show();
                } 
            }
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "Seleccione un elemento de la tabla");
        }
        
    }

    /**
     * Este metodo es un auxiliar, habilita los cuadros de texto cuando es
     * necesario
     */
    public void habilitarCuadros() {
        textMatricula.setEditable(true);
        textNombre.setEditable(true);
        textCorreo.setEditable(true);
        textPrograma.setEditable(true);
        textContactoEmergencia.setEditable(true);
        textTelefonoEmergencia.setEditable(true);
        tablaAlumnos.setDisable(true);
        botonEliminar.setDisable(true);
        botonAgregar.setDisable(true);
    }

    /**
     * Este metodo obtiene la informacion proporcionada en la pantalla y crea un
     * objeto de tipo Alumno
     *
     * @return Objeto de tipo Alumno
     */
    public Alumno obtenerDatos() {
        Alumno alumnoActualizado = new Alumno();
        alumnoActualizado.setMatricula(textMatricula.getText());
        alumnoActualizado.setNombre(textNombre.getText());
        alumnoActualizado.setCorreo(textCorreo.getText());
        alumnoActualizado.setProgramaEducativo(textPrograma.getText());
        alumnoActualizado.setContactoEmergencia(textContactoEmergencia.getText());
        alumnoActualizado.setNumeroEmergencia(textTelefonoEmergencia.getText());
        return alumnoActualizado;
    }

    /**
     * Este metodo es un auxiliar, deshabilita los cuadros de texto cuando es
     * necesario
     */
    public void deshabilitarCuadros() {
        textMatricula.setEditable(false);
        textNombre.setEditable(false);
        textCorreo.setEditable(false);
        textPrograma.setEditable(false);
        textContactoEmergencia.setEditable(false);
        textTelefonoEmergencia.setEditable(false);
        tablaAlumnos.setDisable(false);
        botonEliminar.setDisable(false);
        botonAgregar.setDisable(false);
        
    }
    
    public void llenarTabla() {
        try {
            listaAlumno = FXCollections.observableList(alumno.obtenerAlumnos());
            columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columnaPrograma.setCellValueFactory(new PropertyValueFactory<>("programaEducativo"));
            tablaAlumnos.setItems(listaAlumno);
        } catch (Exception ex) {
            ex.printStackTrace();
            Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
        }
    }
    
    public boolean validarCamposVacios() {
        if (textMatricula.getText().equals("") || textNombre.getText().equals("") || textCorreo.getText().equals("")
                || textPrograma.getText().equals("") || textContactoEmergencia.getText().equals("")
                || textTelefonoEmergencia.getText().equals("")) {
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
            Logger.getLogger(IUAdministrarAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void regresar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuCoordinador.fxml"));
        IUMenuCoordinadorController controller = new IUMenuCoordinadorController();
        loader.setController(controller);
        controller.setCoordinador(coordinador);
        controller.mostrarVentana(loader);
        Stage mainStage = (Stage) botonRegresar.getScene().getWindow();
        mainStage.close();
    }
    
    public Coordinador getCoordinador() {
        return coordinador;
    }
    
    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }
    
    @FXML
    public void extenderInscribir() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUInscripcion.fxml"));
        IUInscripcionController controller = new IUInscripcionController();
        loader.setController(controller);
        controller.setCoordinador(coordinador);
        controller.setOrigen(false);
        controller.mostrarVentana(loader);
        Stage mainStage = (Stage) botonAgregar.getScene().getWindow();
        mainStage.close();
    }
}

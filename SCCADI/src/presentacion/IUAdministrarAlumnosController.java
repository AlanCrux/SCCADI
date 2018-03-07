package presentacion;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOAlumnoImpl;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTabla();
        tablaAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Alumno> observable, Alumno oldValue, Alumno newValue) -> {
            try {
                cargarAlumno();
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("Se detuvo la excepción");
            }
        });
    }

    @FXML
    public void botonAgregar() {

    }

    /**
     * Este metodo llena la tabla de alumnos con la lista ya filtrada
     */
    @FXML
    public void filtarAlumnos() {
        String caracter = textBuscarAlumno.getText();
        try {
            listaAlumno = FXCollections.observableList(alumno.obtenerAlumnosFiltrados(caracter));
            columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columnaPrograma.setCellValueFactory(new PropertyValueFactory<>("programaEducativo"));
            tablaAlumnos.setItems(listaAlumno);
        } catch (Exception ex) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("Ha ocurrido un error, intente mas tarde");
            alerta.show();
        }
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
        Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        for (int i = 0; i < listaAlumno.size(); i++) {
            if (listaAlumno.get(i).getMatricula().equals(seleccionado.getMatricula())) {
                try {
                    System.out.println(seleccionado.getMatricula());
                    if (inscripcion.eliminarInscripcionesPorMatricula(seleccionado)) {
                        if (alumno.eliminarAlumno(seleccionado.getMatricula())) {
                            listaAlumno.remove(i);
                            llenarTabla();
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Confirmación");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Se elimino al alumno exitosamente");
                            alerta.show();
                        } else {
                        }
                    } else {
                        System.out.println("else de eliminar inscripcion");
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Ha ocurrido un error, intente mas tarde");
                        alerta.show();
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        }

    }

    /**
     * Este metodo nos permite editar la informacion de un alumno registrado en
     * la base de datos
     */
    @FXML
    public void editarAlumno() {
        try {
            Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
            String matricula = seleccionado.getMatricula();
            if (botonEditar.getText().equals("Editar")) {
                habilitarCuadros();
                botonEditar.setText("Guardar");
            } else if (validarCamposVacios()) {
                if (alumno.actualizarAlumno(matricula, obtenerDatos())) {
                    llenarTabla();
                    deshabilitarCuadros();
                    botonEditar.setText("Editar");
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Confirmación");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Actualización exitosa");
                    alerta.show();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Error");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Ha ocurrido un error, intente mas tarde");
                    alerta.show();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Existen campos vacio, por favor llenalos");
                alerta.show();
            }
        } catch (Exception ex) {
            System.out.println(ex);
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
        String matriculaNueva = textMatricula.getText();
        System.out.println("matricula nueva" + matriculaNueva);
        alumnoActualizado.setMatricula(matriculaNueva);
        alumnoActualizado.setNombre(textNombre.getText());
        alumnoActualizado.setCorreo(textCorreo.getText());
        alumnoActualizado.setProgramaEducativo(textPrograma.getText());
        alumnoActualizado.setContactoEmergencia(textContactoEmergencia.getText());
        alumnoActualizado.setNumeroEmergencia(textTelefonoEmergencia.getText());
        System.out.println(alumnoActualizado.toString());
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
            try {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error en la conexión");
                alerta.setHeaderText(null);
                alerta.setContentText("Ha ocurrido un error, intente mas tarde");
                alerta.show();
            } catch (Exception ex1) {
                Logger.getLogger(IUAdministrarAlumnosController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public boolean validarCamposVacios() {
        if (textMatricula.getText().equals("") || textNombre.getText().equals("") || textCorreo.getText().equals("")
                || textPrograma.getText().equals("") || textContactoEmergencia.getText().equals("")
                || textTelefonoEmergencia.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }
}

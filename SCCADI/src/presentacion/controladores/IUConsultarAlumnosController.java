/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOAlumnoImpl;
import logica.dominio.Alumno;
import logica.dominio.Asesor;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarAlumnosController implements Initializable {

    @FXML
    private ImageView botonAtras;
    @FXML
    private ImageView imageFoto;
    @FXML
    private JFXListView<Alumno> listaAlumnos;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelMatricula;
    @FXML
    private JFXButton botonConsultarProgreso;
    @FXML
    private JFXButton botonRegistrarProgreso;
    @FXML
    private JFXTextField tfBuscar;

    DAOAlumnoImpl alumno = new DAOAlumnoImpl();
    ObservableList<Alumno> listaAlumno = null;
    Asesor objAsesor = new Asesor();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limpiarCampos();
        llenarLista();
        listaAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Alumno> observable, Alumno oldValue, Alumno newValue) -> {
            try {
                cargarAlumno();
            } catch (Exception ex) {
            }
        });

        tfBuscar.setOnKeyReleased(event -> {
            limpiarCampos();
            if (tfBuscar.getText().trim().isEmpty()) {
                listaAlumnos.setItems(listaAlumno);
            } else {
                listaAlumnos.setItems(filtrarAlumnos(tfBuscar.getText()));

            }
        });
    }

    public void llenarLista() {
        try {
            listaAlumno = FXCollections.observableList(alumno.obtenerAlumnosPorSeccion(3342));
            listaAlumnos.setItems(listaAlumno);
        } catch (Exception ex) {
                  Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
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
            Logger.getLogger(IUMenuCoordinadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarAlumno() {
        Alumno alumnoeleccionado = listaAlumnos.getSelectionModel().getSelectedItem();
        labelNombre.setText(alumnoeleccionado.getNombre());
        labelMatricula.setText(alumnoeleccionado.getMatricula());
        imageFoto.setImage(blobToImage(alumnoeleccionado.getFotografia()));
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

    private ObservableList<Alumno> filtrarAlumnos(String criterio) {
        int size = listaAlumno.size();
        ObservableList<Alumno> filtrada = FXCollections.observableArrayList();
        for (int i = 0; i < listaAlumno.size(); i++) {
            if (listaAlumno.get(i).getNombre().trim().contains(criterio.trim())) {
                filtrada.add(listaAlumno.get(i));
            }
        }
        return filtrada;
    }

    public void limpiarCampos() {
        imageFoto.setImage(new Image("/recursos/imagenes/IMG_ALUMNO.jpg"));
        labelMatricula.setText("Matricula");
        labelNombre.setText("Nombre");
    }

}

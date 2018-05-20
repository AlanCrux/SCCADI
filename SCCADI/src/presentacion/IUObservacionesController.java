package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.dao.DAOInscripcion;
import logica.daoimpl.DAOAlumnoImpl;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Seccion;
import presentacion.controladores.IUMenuRecepcionistaController;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class IUObservacionesController implements Initializable {

    @FXML
    private ImageView botonAtras;
    @FXML
    private TextField tfAlumno;
    @FXML
    private Label lbNombreAsesor;
    @FXML
    private TextArea taObservacion;
    @FXML
    private Button btnAgregar;
    @FXML
    private ListView<Alumno> lstAlumnos;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private ComboBox<ExperienciaEducativa> comboExperiencias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstAlumnos.getSelectionModel().selectedItemProperty().addListener(event -> {
            try {
                Alumno seleccionado = lstAlumnos.getSelectionModel().getSelectedItem();
                System.out.println(seleccionado.getMatricula());
                comboExperiencias.setItems(FXCollections.observableList(obtenerExperiencias(seleccionado.getMatricula())));
                System.out.println("TAMAÑO: "+comboExperiencias.getItems().size());
            } catch (Exception ex) {
            };
        });
    }

    public List<ExperienciaEducativa> obtenerExperiencias(String matricula) {
        List<ExperienciaEducativa> experiencias = new ArrayList<>();
        try {
            DAOInscripcionImpl daoIncripcion = new DAOInscripcionImpl();
            List<Seccion> secciones = daoIncripcion.obtenerSecciones(matricula);
            for (int i = 0; i < secciones.size(); i++) {
                experiencias.add(secciones.get(i).getExperienciaEducativa());
                System.out.println("EXP:"+secciones.get(i).getExperienciaEducativa());
            }
        } catch (Exception ex) {
            Logger.getLogger(IUObservacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return experiencias;
    }

    @FXML
    private void efectoMouseFuera(MouseEvent event) {
    }

    @FXML
    private void efectoMouseSobre(MouseEvent event) {
    }

    @FXML
    private void salir(MouseEvent event) {
    }

    @FXML
    private void buscarAlumno(KeyEvent event) {
        lstAlumnos.getItems().clear();
        if (!tfAlumno.getText().trim().isEmpty()) {
            lstAlumnos.setItems(FXCollections.observableList(obtenerAlumnos(tfAlumno.getText().trim())));
        }
    }

    @FXML
    private void agregarObservacion(ActionEvent event) {
    }

    public List<Alumno> obtenerAlumnos(String nombre) {
        List<Alumno> alumnos = new ArrayList<>();
        DAOAlumnoImpl daoAlumno = new DAOAlumnoImpl();
        try {
            alumnos = daoAlumno.obtenerAlumnosFiltrados(nombre);
        } catch (Exception ex) {
            Mensajes.displayInformation("Error", "Servicio no disponible");
        }
        return alumnos;
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
            Logger.getLogger(IUMenuRecepcionistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

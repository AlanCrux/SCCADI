
package presentacion.controladores;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import logica.dominio.ActividadAsignada;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarProgresoController implements Initializable {

    @FXML
    private ImageView botonRegresar;
    @FXML
    private TableView<ActividadAsignada> tablaActividades;
    @FXML
    private TableColumn<ActividadAsignada, String> columnaActividad;
    @FXML
    private TableColumn<ActividadAsignada, String> columnaFechaAct;
    @FXML
    private Label labelActivRealizadas;
    @FXML
    private Label labelTotalActividades;
    @FXML
    private Label laberlPorcObten;
    @FXML
    private Label labelPorcTotal;
    @FXML
    private Label labelExamRealizadas;
    @FXML
    private Label labelTotalExamenes;
    @FXML
    private Label laberlPorcObtenExam;
    @FXML
    private Label labelPorcTotalExamen;
    @FXML
    private TableView<?> tablaExamen;
    @FXML
    private TableColumn<?, ?> columnaDescripcion;
    @FXML
    private TableColumn<?, ?> columnaTipo;
    @FXML
    private TableColumn<?, ?> columnaFecha;
    @FXML
    private TableColumn<?, ?> columnaCalificacion;
    @FXML
    private JFXTextField tfBitacora;
    @FXML
    private Label lbPorcBit;
    @FXML
    private JFXTextField tfHojaSeuimiento;
    @FXML
    private Label lbPorcHojaSeg;
    @FXML
    private JFXTextField tfReflexion;
    @FXML
    private Label lbPorcReflex;
    @FXML
    private JFXTextField tfAutoevaluacion;
    @FXML
    private Label lbPorcAutoeval;
    @FXML
    private JFXTextField tfProgreso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

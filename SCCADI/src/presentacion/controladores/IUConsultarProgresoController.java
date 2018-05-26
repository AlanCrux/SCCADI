package presentacion.controladores;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.daoimpl.DAOExamenImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.daoimpl.DAOProgresoImpl;
import logica.daoimpl.DAOReservacionImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.Examen;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Progreso;
import logica.dominio.Reservacion;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarProgresoController implements Initializable {

    @FXML
    private ImageView botonRegresar;
    @FXML
    private TableView<Reservacion> tablaActividades;
    @FXML
    private TableColumn<Reservacion, String> columnaActividad;
    @FXML
    private TableColumn<Reservacion, String> columnaFechaAct;
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
    private TableView<Examen> tablaExamen;
    @FXML
    private TableColumn<Examen, String> columnaDescripcion;
    @FXML
    private TableColumn<Examen, String> columnaTipo;
    @FXML
    private TableColumn<Examen, Date> columnaFecha;
    @FXML
    private TableColumn<Examen, Float> columnaCalificacion;
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

    ObservableList<Examen> examenes = null;
    ObservableList<Reservacion> actividades = null;
    DAOExamenImpl examen = new DAOExamenImpl();
    DAOExperienciaEducativaImpl expeEdu = new DAOExperienciaEducativaImpl();
    DAOProgresoImpl progreso = new DAOProgresoImpl();
    DAOReservacionImpl reservacion = new DAOReservacionImpl();
    Progreso progresoActual = null;

    int folioInscripcion = 2;
    String matricula = "S15011635";
    ExperienciaEducativa experienciaEdu = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTablaExamen();
        llenarTablaActividades();
        obtenerPorcentajes();
        

    }

    public void llenarTablaExamen() {
        try {
            examenes = FXCollections.observableList(examen.obtenerExamenes(folioInscripcion));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
            columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaExamen.setItems(examenes);
        } catch (Exception ex) {
            Logger.getLogger(IUConsultarProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void llenarTablaActividades() {
        try {
            actividades = FXCollections.observableList(reservacion.obtenerReservacionesPorAlumno(matricula));
            columnaActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaFechaAct.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaActividades.setItems(actividades);
        } catch (Exception ex) {
            Logger.getLogger(IUConsultarProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerPorcentajes() {

        try {

            experienciaEdu = expeEdu.obtenerExperienciasPorAlumno(matricula);
            progresoActual = progreso.obtenerProgreso(folioInscripcion);
        } catch (Exception ex) {
            Logger.getLogger(IUConsultarProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        double porcentajePorExamen = experienciaEdu.getPorcentajeExamenes() / experienciaEdu.getNumExamenes();
        double porcentajeObtExamen = 0;
        int totalExamenes = 0;
        int totalActividades = 0;

        for (int i = 0; i < examenes.size(); i++) {
            totalExamenes++;
            //porcentajeObtExamen = porcentajeObtExamen + (examenes.get(i).getCalificacion() * porcentajePorExamen) / 10;
        }
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getAsistio()) {
                totalActividades++;
            }
        }
        labelPorcTotalExamen.setText(Double.toString(porcentajeObtExamen));
        String totalExameness = Integer.toString(totalExamenes);
        labelExamRealizadas.setText(totalExameness);
        tfAutoevaluacion.setText(Integer.toString(progresoActual.getNumAutoevaluaciones()));
        tfBitacora.setText(Integer.toString(progresoActual.getNumBitacoras()));
        tfHojaSeuimiento.setText(Integer.toString(progresoActual.getNumSeguimiento()));
        tfReflexion.setText(Integer.toString(progresoActual.getNumReflexiones()));
        labelPorcTotalExamen.setText(Integer.toString(experienciaEdu.getPorcentajeExamenes()) + "%");

        System.out.println(Integer.toString(experienciaEdu.getPorcentajeTaller()) + "%");
        labelPorcTotal.setText(Integer.toString(experienciaEdu.getPorcentajeTaller()) + "%");

        labelActivRealizadas.setText(Integer.toString(totalActividades));

        double porcentajeObtBitacora = (progresoActual.getNumBitacoras()
                * experienciaEdu.getPorcentajeBitacoras()) / experienciaEdu.getNumBitacoras();
        lbPorcBit.setText(Double.toString(porcentajeObtBitacora) + "%");

        double porcentajeObtHojaSeg = (progresoActual.getNumSeguimiento()
                * experienciaEdu.getPorcentajeSeguimiento()) / experienciaEdu.getNumSeguimiento();
        lbPorcHojaSeg.setText(Double.toString(porcentajeObtHojaSeg) + "%");

        double porcentajeObtReflexion = (progresoActual.getNumReflexiones()
                * experienciaEdu.getPorcentajeReflexiones()) / experienciaEdu.getNumReflexiones();
        lbPorcReflex.setText(Double.toString(porcentajeObtReflexion) + "%");

        double porcentajeAutoEval = (progresoActual.getNumAutoevaluaciones()
                * experienciaEdu.getPorcentajeAutoevaluaciones()) / experienciaEdu.getNumAutoevaluaciones();
        lbPorcAutoeval.setText(Double.toString(porcentajeAutoEval) + "%");
        laberlPorcObtenExam.setText(Double.toString(porcentajeObtExamen) + "%");

        double porcentajeActividades = (totalActividades
                * experienciaEdu.getPorcentajeTaller()) / experienciaEdu.getNumTaller();
        laberlPorcObten.setText(Double.toString(porcentajeActividades) + "%");

        double porcentajeObtTotal = porcentajeObtBitacora + porcentajeObtHojaSeg
                + porcentajeObtReflexion + porcentajeAutoEval + porcentajeActividades
                + porcentajeObtExamen;
        tfProgreso.setText(Double.toString(porcentajeObtTotal)+"%");
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
}

package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.daoimpl.DAOActividadAsignadaImpl;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.ActividadProgramada;
import logica.dominio.ExperienciaEducativa;
import utilerias.Herramientas;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarDetalleSemanaActividadesController implements Initializable {

    @FXML
    private JFXComboBox<ExperienciaEducativa> comboExperiencia;
    @FXML
    private JFXComboBox<ActividadProgramada> comboSemana;
    @FXML
    private TableView<ActividadAsignada> tablaActividades;
    @FXML
    private TableColumn<ActividadAsignada, String> columnaNombre;
    @FXML
    private TableColumn<ActividadAsignada, String> columnaAsesor;
    @FXML
    private TableColumn<ActividadAsignada, Date> columnaFecha;
    @FXML
    private TableColumn<ActividadAsignada, Time> columnaHora;
    @FXML
    private TableColumn<ActividadAsignada, String> columnaSala;
    @FXML
    private TableColumn<ActividadAsignada, Integer> columnaCupo;
    @FXML
    private JFXButton botonReservar;

    DAOActividadAsignadaImpl actividad = new DAOActividadAsignadaImpl();
    DAOActividadProgramadaImpl actividadP = new DAOActividadProgramadaImpl();
    DAOExperienciaEducativaImpl experienciaEdu = new DAOExperienciaEducativaImpl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTabla();
        llenarComboSemana();
        llenarComboExp();
    }

    /**
     * Metoodo que llena una tabla con los datos de las actividades que ya han
     * sido asignadas a los asesores
     */
    public void llenarTabla() {
        try {
            ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadAsignada());
            ObservableList<ActividadAsignada> listaActividadesNueva = FXCollections.observableArrayList();
            Date ahora = new Date();
            for (int i = 0; i < listaActividades2.size(); i++) {
                Date fecha = listaActividades2.get(i).getFecha();
                if (fecha.after(ahora)) {
                    listaActividadesNueva.add(listaActividades2.get(i));
                }
            }
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
            columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
            columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
            tablaActividades.setItems(listaActividadesNueva);
        } catch (Exception ex) {
            Herramientas.displayWarningAlert("Error conexion", "No se pudo obtener la información");
        }
    }
    
    /**
     * Metodo que llena el comboBox de las semanas con los rangos de fecha de las
     * actividades programadas
     */
    public void llenarComboSemana() {
        try {
            ObservableList<ActividadProgramada> listaSemanas = FXCollections.observableList(actividadP.obtenerActividadProgramada());
            ObservableList<ActividadProgramada> listaSemanas2 = FXCollections.observableArrayList();
            Date ahora = new Date();
            for (int i = 0; i < listaSemanas.size(); i++) {
                Date fechaInicio = listaSemanas.get(i).getFechaInicio();
                Date fechaFin = listaSemanas.get(i).getFechaFin();
                if (fechaInicio.after(ahora)) {
                    listaSemanas2.add(listaSemanas.get(i));
                }
            }
            comboSemana.getItems().addAll(listaSemanas2);
        } catch (Exception ex) {
            Herramientas.displayWarningAlert("Error conexion", "No se pudo obtener la información");
            Logger.getLogger(IUConsultarActividadesAsignadasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que llena el combo de experiencias educativas con las EE
     * registradas en la base de datos
     */
    public void llenarComboExp() {
        try {
            ObservableList<ExperienciaEducativa> listaExperiencias = FXCollections.observableList(experienciaEdu.obtenerExperiencias());
            comboExperiencia.getItems().addAll(listaExperiencias);

        } catch (Exception ex) {
            Herramientas.displayWarningAlert("Error conexion", "No se pudo obtener la información");
        }
    }
    
    /**
     * Metodo que filtra los registros asignados a los asesores por experiencia
     * educativa
     */
    @FXML
    public void filtrarPorExperiencia() {
        int experiencia = comboExperiencia.getValue().getIdExperiencia();
        System.out.println("experiencia en exp " + experiencia);

        try {
            ObservableList<ActividadAsignada> listaActividades2 = FXCollections.observableList(actividad.obtenerActividadesPorExperiencia(experiencia));
            ObservableList<ActividadAsignada> listaActividadesNueva = FXCollections.observableArrayList();
            Date ahora = new Date();
            for (int i = 0; i < listaActividades2.size(); i++) {
                Date fecha = listaActividades2.get(i).getFecha();
                if (fecha.after(ahora)) {
                    listaActividadesNueva.add(listaActividades2.get(i));
                }
            }
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
            columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
            columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
            tablaActividades.setItems(listaActividadesNueva);
        } catch (Exception ex) {
            Herramientas.displayWarningAlert("Error conexion", "No se pudo obtener la información");
        }
    }

    /**
     * Metodo que filtra las actividades asignadas a los asesores por un intervalo
     * de tiempo
     */
    @FXML
    public void filtrarPorSemana2() {
        try {
            int experiencia = comboExperiencia.getValue().getIdExperiencia();

            Date fechaMin = comboSemana.getValue().getFechaInicio();
            Date fechaMax = comboSemana.getValue().getFechaFin();
            System.out.println("experiencia en fecha " + experiencia);
            ObservableList<ActividadAsignada> listaActividades2
                    = FXCollections.observableList(actividad.obtenerActividadesPorFecha(experiencia, fechaMin, fechaMax));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
            columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombreSala"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
            columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
            tablaActividades.setItems(listaActividades2);
        } catch (Exception ex) {
            Herramientas.displayWarningAlert("Error conexion", "Seleccione una experiencia educativa primero");
        }
    }
}

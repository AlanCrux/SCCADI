/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.dao.DAOSala;
import logica.daoimpl.DAOActividadAsignadaImpl;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.daoimpl.DAOAsesorImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.daoimpl.DAOSalaImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.ActividadProgramada;
import logica.dominio.Asesor;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Sala;
import utilerias.DateConvertUtils;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUAsignarActividadesSemanalesController implements Initializable {

    @FXML
    private Label labelRegistroExitoso;
    @FXML
    private ImageView botonAtras;
    @FXML
    private ListView<ActividadProgramada> listaActividades;
    @FXML
    private JFXTextField textNombre;
    @FXML
    private JFXTextField textCupo;
    @FXML
    private JFXTextField textHora;
    @FXML
    private JFXTextField textMinuto;
    @FXML
    private DatePicker pickerFecha;
    @FXML
    private JFXComboBox<Sala> comboSalas;
    @FXML
    private JFXComboBox<Asesor> comboAsesor;
    @FXML
    private JFXButton botonAsignar;

    @FXML
    private JFXComboBox<ExperienciaEducativa> comboEE;

    ObservableList<ActividadProgramada> listaActividadesProgramadas = null;
    DAOActividadProgramadaImpl actividadPro = new DAOActividadProgramadaImpl();
    DAOExperienciaEducativaImpl experienciaEdu = new DAOExperienciaEducativaImpl();
    DAOActividadAsignadaImpl actividadAsig = new DAOActividadAsignadaImpl();
    DAOSalaImpl salas = new DAOSalaImpl();
    DAOAsesorImpl asesores = new DAOAsesorImpl();
    ActividadProgramada actividadSeleccionada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTabla();
        llenarComboExp();

        listaActividades.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends ActividadProgramada> observable, ActividadProgramada oldValue, ActividadProgramada newValue) -> {
            try {
                actividadSeleccionada = listaActividades.getSelectionModel().getSelectedItem();
                textNombre.setText(actividadSeleccionada.getNombre());

            } catch (Exception ex) {
                Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
            }
        });
    }
    
    /**
     * Metodo que llena la tabla de las actividades programadas registradas 
     */
    public void llenarTabla() {
        try {
            listaActividadesProgramadas = FXCollections.observableList(actividadPro.obtenerActividadesProgramadas());
            listaActividades.setItems(listaActividadesProgramadas);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible, intente más tarde");
        }
    }
    
    /**
     * Metodo que llena el combo de las experiencias educativas
     */
    public void llenarComboExp() {
        try {
            ObservableList<ExperienciaEducativa> listaExperiencias = FXCollections.observableList(experienciaEdu.obtenerExperiencias());
            comboEE.getItems().addAll(listaExperiencias);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información de las experiencias educativas");
        }
    }
/**
 * Metodo que llena el combo de las salas
 */
    public void llenarComboSala() {
        try {
            ObservableList<Sala> listaSalas = FXCollections.observableList(salas.obtenerSalas());
            comboSalas.getItems().addAll(listaSalas);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información de las salas");
        }
    }
/**
 * Metodo que llena el combo de los asesores
 */
    public void llenarComboAsesor() {
        try {
            ObservableList<Asesor> listaAsesores = FXCollections.observableList(asesores.obtenerAsesores());
            comboAsesor.getItems().addAll(listaAsesores);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información de los asesores");
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

    /**
     * Metodo que llena la tabla filtrada por experiencia educativa
     */
    @FXML
    public void llenarTablaFiltrada() {
        int experiencia = comboEE.getValue().getIdExperiencia();
        try {
            listaActividadesProgramadas = FXCollections.observableList(actividadPro.obtenerActividadesProgramadasPorEE(experiencia));
            listaActividades.setItems(listaActividadesProgramadas);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible, intente más tarde");
        }
    }
    
    
    /**
     * Metodo que realiza la asignacion de una actividad a un asesor
     */
    @FXML
    public void asignarAsesor() {
        ActividadAsignada actividadCreada = new ActividadAsignada();

        if (botonAsignar.getText().equals("Asignar asesor")) {
            if (listaActividades.getSelectionModel().getSelectedItem() == null) {
                Mensajes.displayWarningAlert("Error", "Debes seleccionar una actividad");
            } else {
                labelRegistroExitoso.setVisible(false);
                botonAsignar.setText("Guardar");
                habilitarCuadros();
                llenarComboAsesor();
                llenarComboSala();
            }
        } else if (camposVacios()) {
            Mensajes.displayWarningAlert("Error conexion", "Por favor ingresar toda la información");
        } else {
            int hora1 = Integer.parseInt(textHora.getText());
            int minuto = Integer.parseInt(textMinuto.getText());
            int cupo = Integer.parseInt(textCupo.getText());
            Time hora = new Time(hora1, minuto, 0);
            int idActividad = listaActividades.getSelectionModel().getSelectedItem().getIdActividadProgramada();
            actividadCreada.setIdActividadProgramda(idActividad);

            actividadCreada.setHora(hora);
            actividadCreada.setCupoMaximo(cupo);
            actividadCreada.setIdSala(comboSalas.getSelectionModel().getSelectedItem().getIdSala());
            actividadCreada.setNoPersonal(comboAsesor.getSelectionModel().getSelectedItem().getNoPersonal());

            if (DateConvertUtils.asUtilDate(pickerFecha.getValue()).after(actividadSeleccionada.getFechaFin())) {
                actividadCreada.setFecha(actividadSeleccionada.getFechaFin());
            } else if (DateConvertUtils.asUtilDate(pickerFecha.getValue()).before(actividadSeleccionada.getFechaInicio())) {
                actividadCreada.setFecha(actividadSeleccionada.getFechaInicio());
            } else {
                actividadCreada.setFecha(DateConvertUtils.asUtilDate(pickerFecha.getValue()));
            }
            try {
                if (actividadAsig.insertarActividadAsignada(actividadCreada)) {
                    labelRegistroExitoso.setVisible(true);
                    botonAsignar.setText("Asignar asesor");
                    deshabilitarCuadros();
                    limpiarCuadros();
                }
            } catch (Exception ex) {
                Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible, intente más tarde");
                Logger.getLogger(IUAsignarActividadesSemanalesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void habilitarCuadros() {
        pickerFecha.setDisable(false);
        textHora.setEditable(true);
        textMinuto.setEditable(true);
        textCupo.setEditable(true);
        comboSalas.setDisable(false);
        comboAsesor.setDisable(false);
        listaActividades.setDisable(true);
    }

    public void deshabilitarCuadros() {
        pickerFecha.setDisable(true);
        textHora.setEditable(false);
        textMinuto.setEditable(false);
        textCupo.setEditable(false);
        comboSalas.setDisable(true);
        comboAsesor.setDisable(true);
    }

    private boolean camposVacios() {
        if (pickerFecha.getValue() == null || textHora.getText().equals("")
                || textMinuto.getText().equals("") || textCupo.getText().equals("")
                || comboSalas.getSelectionModel().isEmpty() || comboAsesor.getSelectionModel().isEmpty()) {
            return true;
        }
        return false;
    }

    public void limpiarCuadros() {
        textCupo.setText("");
        textHora.setText("");
        textMinuto.setText("");
        textNombre.setText("");
        pickerFecha.getEditor().clear();
        comboAsesor.setPromptText("");
        comboSalas.setPromptText("");
    }

}

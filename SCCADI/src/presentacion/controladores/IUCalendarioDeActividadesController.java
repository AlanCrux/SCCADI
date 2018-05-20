/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.dominio.ActividadProgramada;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUCalendarioDeActividadesController implements Initializable {

    @FXML
    private TableView<ActividadProgramada> tablaActividades;
    @FXML
    private TableColumn<ActividadProgramada, String> colNombre;
    @FXML
    private TableColumn<ActividadProgramada, Date> colFechaInicio;
    @FXML
    private TableColumn<ActividadProgramada, Date> colFechaFin;
    @FXML
    private TableColumn<ActividadProgramada, String> colExperiencia;
    @FXML
    private TableColumn<ActividadProgramada, Integer> colModulo;
    @FXML
    private TableColumn<ActividadProgramada, Integer> colUnidad;
    @FXML
    private ImageView btnRegresar;
    @FXML
    private Label labelMes;
    @FXML
    private JFXComboBox<String> comboMes;

    ObservableList<String> meses;
    ObservableList<ActividadProgramada> actividades;
    DAOActividadProgramadaImpl actividad = new DAOActividadProgramadaImpl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        meses = FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        comboMes.setItems(meses);
        Date hoy = new Date();
        comboMes.getSelectionModel().select(hoy.getMonth());
        llenarTabla(hoy.getMonth()+1);
        labelMes.setText(mostrarMes(hoy.getMonth() + 1));
        comboMes.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            llenarTabla(comboMes.getSelectionModel().getSelectedIndex() + 1);
            labelMes.setText(comboMes.getSelectionModel().getSelectedItem());

        });
    }

    public void llenarTabla(int mes) {
        try {
            actividades = FXCollections.observableList(actividad.obtenerActividadesProgramadasPorMes(mes));
            colExperiencia.setCellValueFactory(new PropertyValueFactory<>("experienciaEducativa"));
            colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
            colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
            colModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
            System.out.println("------------------------------------ actividades del mes " + mes);
            System.out.println(actividades.toString());
            System.out.println("INDICADOR"+actividades.size());
            tablaActividades.setItems(actividades);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(IUConsultarProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String mostrarMes(int mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
        }
        return "Mes";
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

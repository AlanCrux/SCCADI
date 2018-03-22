/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import logica.daoimpl.DAOActividadAsignadaImpl;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.daoimpl.DAOSalaImpl;
import logica.dominio.ActividadAsignada;
import logica.dominio.Asesor;
import logica.dominio.Sala;
import utilerias.Herramientas;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUConsultarActividadesAsignadasController implements Initializable {

    @FXML
    private ImageView botonRegresar;
    @FXML
    private Label labelNombreAsesor;
    @FXML
    private JFXComboBox<?> comboExperienciaEdu;
    @FXML
    private JFXComboBox<?> comboSemana;
    @FXML
    private TableView<?> tablaActividades;
    @FXML
    private TableColumn<?, ?> columnaActividad;
    @FXML
    private TableColumn<?, ?> columnaSala;
    @FXML
    private TableColumn<?, ?> columnaHora;
    @FXML
    private TableColumn<?, ?> columnaFecha;
    @FXML
    private JFXButton botonRegistrarAsistencia;
    
    ObservableList listaActividades = null;
    ObservableList listaActividadesP = null;
    ObservableList listaSalas = null;
    DAOActividadAsignadaImpl actividad = new DAOActividadAsignadaImpl();
    DAOActividadProgramadaImpl actividadP = new DAOActividadProgramadaImpl();
    DAOSalaImpl salas = new DAOSalaImpl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Asesor asesor = new Asesor();
        asesor.setNoPersonal(1234);
        asesor.setNombre("Jose Antonio Martinez Salazar");
    }

    public void llenarTabla() {

    }
    
    public void filtrarTabla(){
        try {
            listaActividades = FXCollections.observableArrayList(actividad.obtenerActividadAsignada());
            listaActividadesP = FXCollections.observableArrayList(actividadP.obtenerActividadProgramada());
            listaSalas = FXCollections.observableArrayList(salas.obtenerSala());
            for (int i = 0; i < listaActividades.size(); i++) {
                Sala sala = (Sala) listaSalas.get(i);
                String nombreSala = sala.getNombre();
//                ActividadAsignada actividadA = new ActividadAsignada(nombreSala, fecha, hora, nombre);
                
                
            }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(IUConsultarActividadesAsignadasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

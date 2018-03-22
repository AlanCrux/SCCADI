package presentacion.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logica.daoimpl.DAOSeccionImpl;
import logica.dominio.Seccion;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Alan Yoset Garcia Cruz
 */
public class IUInscripcionController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private Label lbNombreAsesor;
  @FXML
  private Label lbNombreAsesor1;
  @FXML
  private Label lbNombreAsesor11;
  @FXML
  private TextField tfMatricula;
  @FXML
  private TextField tfNombre;
  @FXML
  private TextField tfCorreo;
  @FXML
  private Button btnFoto;
  @FXML
  private Button btnInscribir;
  @FXML
  private TextField tfNombreEmergencia;
  @FXML
  private TextField tfTelefonoEmergencia;
  @FXML
  private TableView<Seccion> tablaExperiencias;
  @FXML
  private TableColumn<Seccion, String> tbcNRC;
  @FXML
  private TableColumn<Seccion, String> tbcExperiencia;
  @FXML
  private TableColumn<Seccion, String> tbcNivel;
  @FXML
  private TableColumn<Seccion, String> tbcCupo;
  @FXML
  private TableColumn<Seccion, String> tbcAsesor;
  @FXML
  private Label lbNombreAsesor111;
  @FXML
  private ImageView imageViewFoto;

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
//    tbcNRC = new TableColumn<>("NRC");
//    tbcExperiencia = new TableColumn<>("Experiencia");
//    tbcNivel = new TableColumn<>("Nivel");
//    tbcCupo = new TableColumn<>("Cupo");
//    tbcAsesor = new TableColumn<>("Asesor");

    tbcNRC.setCellValueFactory(new PropertyValueFactory<>("nrc"));
    tbcExperiencia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
    tbcNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
    tbcCupo.setCellValueFactory(new PropertyValueFactory<>("cupo"));
    tbcAsesor.setCellValueFactory(new PropertyValueFactory<>("asesor"));
    
    tablaExperiencias.setItems(obtenerSecciones());
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

  public ObservableList<Seccion> obtenerSecciones() {
    ObservableList<Seccion> secciones = null;
    DAOSeccionImpl daoSeccion = new DAOSeccionImpl();

    try {
      secciones = FXCollections.observableList(daoSeccion.obtenerSecciones());
    } catch (Exception ex) {
      ex.printStackTrace();
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }

    return secciones;
  }
}

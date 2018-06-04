package presentacion;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logica.daoimpl.DAOExamenImpl;
import logica.dominio.Examen;
import logica.dominio.Seccion;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alanc
 */
public class IUExamenesController implements Initializable {

  @FXML
  private Label lbExperiencia;
  @FXML
  private Label lbNrc;
  @FXML
  private TableView<Examen> tbExamenes;
  @FXML
  private TableColumn<Examen, String> tbcDescripcion;
  @FXML
  private TableColumn<Examen, String> tbcTipo;
  @FXML
  private TableColumn<Examen, String> tbcFecha;
  @FXML
  private ImageView botonAtras;
  @FXML
  private Button btnNuevo;
  @FXML
  private Button btnEditar;

  private Seccion seccion;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

    tbExamenes.setItems(FXCollections.observableList(obtenerExamenes(seccion.getNrc())));
    tbExamenes.getSelectionModel().selectFirst();
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
  private void agregarExamen(ActionEvent event) {
    int maximo = seccion.getExperienciaEducativa().getNumExamenes();
    if (tbExamenes.getItems().size() < maximo) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUExamen.fxml"));
      IUExamenController controller = new IUExamenController();
      loader.setController(controller);
      controller.mostrarVentana(loader);
    } else {
      Mensajes.displayInformation("Límite excedido", "No se pueden aplicar más exámenes");
    }
  }

  @FXML
  private void editarExamen(ActionEvent event) {
  }

  private List<Examen> obtenerExamenes(int nrc) {
    List<Examen> examenes = new ArrayList<>();
    DAOExamenImpl daoExamen = new DAOExamenImpl();
    try {
      examenes = daoExamen.obtenerExamenesPorSeccion(nrc);
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Servicio no disponible, intente más tarde");
    }
    return examenes;
  }

  public Seccion getSeccion() {
    return seccion;
  }

  public void setSeccion(Seccion seccion) {
    this.seccion = seccion;
  }

}

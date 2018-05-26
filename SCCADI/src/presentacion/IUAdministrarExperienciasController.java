package presentacion;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.dominio.ExperienciaEducativa;
import presentacion.controladores.IUMenuRecepcionistaController;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alanc
 */
public class IUAdministrarExperienciasController implements Initializable {

  @FXML
  private Label lbExperiencia;
  @FXML
  private ImageView botonAtras;
  @FXML
  private TableView<ExperienciaEducativa> tbExperiencias;
  @FXML
  private TableColumn<ExperienciaEducativa, String> tbcNombre;
  @FXML
  private TableColumn<ExperienciaEducativa, String> tbcNivel;
  @FXML
  private TextField tfNombre;
  @FXML
  private TextField tfNumAutoevaluaciones;
  @FXML
  private TextField tfNumActividades;
  @FXML
  private TextField tfBitacoras;
  @FXML
  private TextField tfReflexiones;
  @FXML
  private TextField tfSeguimiento;
  @FXML
  private TextField tfExamenes;
  @FXML
  private TextField tfNivel;
  @FXML
  private TextField tfPorAutoevaluaciones;
  @FXML
  private TextField tfPorActividades;
  @FXML
  private TextField tfPorBitacoras;
  @FXML
  private TextField tfPorReflexiones;
  @FXML
  private TextField tfPorSeguimiento;
  @FXML
  private TextField tfPorExamenes;
  @FXML
  private JFXButton btnAceptar;
  @FXML
  private JFXButton btnEditar;
  @FXML
  private JFXButton btnEliminar;
  @FXML
  private TextField tfModulos;

  @FXML
  private TextField tfUnidades;

  @FXML
  private JFXButton btnGuardar;

  @FXML
  private JFXButton btnCancelar;
  private boolean banderaAgregar;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tbcNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));

    tbExperiencias.setItems(FXCollections.observableList(obtenerExperiencias()));
    tbExperiencias.getSelectionModel().selectedItemProperty().addListener(event -> {
      if (tbExperiencias.getItems().size() > 0) {
        try {
          cargarExperiencia(tbExperiencias.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
        }
      } else {
        limpiar();
      }

    });
    tbExperiencias.getSelectionModel().selectFirst();

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

  /**
   * Describe el funcionamiento del sistema al oprimir el botón agregar.
   *
   * @param event
   */
  @FXML
  private void onActionAgregar(ActionEvent event) {
    btnEditar.setVisible(false);
    btnEliminar.setVisible(false);
    btnAceptar.setVisible(false);

    btnGuardar.setVisible(true);
    btnCancelar.setVisible(true);

    JFXButton seleccionado = (JFXButton) event.getSource();
    if (seleccionado.getText().equals("AGREGAR")) {
      banderaAgregar = true;
      limpiar();
    } else {
      banderaAgregar = false;
    }

    habilitarCuadros();
    tbExperiencias.setDisable(true);

    tfNombre.requestFocus();
  }

  /**
   * Describe el comportamiento del sistema al presionar el botón guardar. Si la bandera es
   * verdadera, inserta el aviso en la base de datos. Si la bandera es falsa, actualiza los datos
   * del aviso en la base de datos.
   *
   * @param event
   */
  @FXML
  void onActionGuardar(ActionEvent event) {
    if (banderaAgregar) {
      deshabilitarCuadros();
      tbExperiencias.setDisable(false);
      if (insertarExperiencia()) {
        Mensajes.displayInformation("Éxito", "El aviso ha sido guardado correctamente");
      }
      limpiar();
      resetButtons();
      tbExperiencias.getSelectionModel().selectFirst();

    } else {
      deshabilitarCuadros();
      tbExperiencias.setDisable(false);
      if (editarExperiencia()) {
        Mensajes.displayInformation("Éxito", "Los cambios se han guardado en el sistema");
      }
      limpiar();
      resetButtons();
      tbExperiencias.getSelectionModel().selectFirst();
    }
  }

  @FXML
  private void onActionEliminar(ActionEvent event) {
  }

  private List<ExperienciaEducativa> obtenerExperiencias() {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    List<ExperienciaEducativa> experiencias = new ArrayList<>();
    try {
      experiencias = daoExperiencia.obtenerExperiencias();
    } catch (Exception ex) {
      Logger.getLogger(IUAdministrarExperienciasController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return experiencias;
  }

  private boolean insertarExperiencia() {
    return true;
  }

  private boolean editarExperiencia() {
    return true;
  }

  private boolean eliminarExperiencia() {
    return true;
  }

  private void cargarExperiencia(ExperienciaEducativa experiencia) {
    tfNombre.setText(experiencia.getNombre());
    tfNumAutoevaluaciones.setText(experiencia.getNumAutoevaluaciones() + "");
    tfNumActividades.setText(experiencia.getNumTaller() + "");
    tfBitacoras.setText(experiencia.getNumBitacoras() + "");
    tfReflexiones.setText(experiencia.getNumReflexiones() + "");
    tfSeguimiento.setText(experiencia.getNumSeguimiento() + "");
    tfExamenes.setText(experiencia.getNumExamenes() + "");
    tfNivel.setText(experiencia.getNivel());
    tfPorAutoevaluaciones.setText(experiencia.getPorcentajeAutoevaluaciones() + "%");
    tfPorActividades.setText(experiencia.getPorcentajeTaller() + "%");
    tfPorBitacoras.setText(experiencia.getPorcentajeBitacoras() + "%");
    tfPorReflexiones.setText(experiencia.getPorcentajeReflexiones() + "%");
    tfPorSeguimiento.setText(experiencia.getPorcentajeSeguimiento() + "%");
    tfPorExamenes.setText(experiencia.getPorcentajeExamenes() + "%");
    tfModulos.setText(experiencia.getNumModulos() + "");
    tfUnidades.setText(experiencia.getNumUnidades() + "");
  }

  private void limpiar() {

  }

  private void habilitarCuadros() {

  }

  private void deshabilitarCuadros() {

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

  /**
   * Vuelve visibles los botones principales de la interfaz y no visibles los secundarios.
   */
  public void resetButtons() {
    btnEditar.setVisible(true);
    btnEliminar.setVisible(true);
    btnAceptar.setVisible(true);

    btnGuardar.setVisible(false);
    btnCancelar.setVisible(false);
  }

  public boolean verificarCamposVacios() {
    return tfNombre.getText().trim().isEmpty() && tfNumAutoevaluaciones.getText().trim().isEmpty()
        && tfNumActividades.getText().trim().isEmpty()
        && tfBitacoras.getText().trim().isEmpty()
        && tfReflexiones.getText().trim().isEmpty()
        && tfSeguimiento.getText().trim().isEmpty()
        && tfExamenes.getText().trim().isEmpty()
        && tfNivel.getText().trim().isEmpty()
        && tfPorAutoevaluaciones.getText().trim().isEmpty()
        && tfPorActividades.getText().trim().isEmpty()
        && tfPorBitacoras.getText().trim().isEmpty()
        && tfPorReflexiones.getText().trim().isEmpty()
        && tfPorSeguimiento.getText().trim().isEmpty()
        && tfPorExamenes.getText().trim().isEmpty()
        && tfModulos.getText().trim().isEmpty()
        && tfUnidades.getText().trim().isEmpty();
  }

  @FXML
  void verificarCampos(KeyEvent event) {
    if (verificarCamposVacios()) {
      btnGuardar.setDisable(true);
    } else {
      btnGuardar.setDisable(false);
    }
  }

}

package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.daoimpl.DAOAvisoImpl;
import logica.dominio.Aviso;
import logica.dominio.Coordinador;
import utilerias.DateConvertUtils;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IUAdministrarAvisoController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private DatePicker datePickerInicio;
  @FXML
  private DatePicker datePickerFin;
  @FXML
  private TextArea taContenido;
  @FXML
  private JFXButton btnAceptar;
  @FXML
  private JFXButton btnEditar;
  @FXML
  private JFXButton btnEliminar;
  @FXML
  private ListView<Aviso> listViewAvisos;
  @FXML
  private Pane paneDisable;
  @FXML
  private TextField tfAsunto;
  @FXML
  private JFXButton btnGuardar;

  @FXML
  private JFXButton btnCancelar;

  private Coordinador coordinador;
  private boolean banderaAgregar;

  /**
   * Inicializa el controlador de la interfaz IUAdministrarAviso
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    listViewAvisos.getSelectionModel().selectedItemProperty().addListener(event -> {
      if (listViewAvisos.getItems().isEmpty()) {
        limpiar();
      } else {
        try {
          extraerDatos(listViewAvisos.getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
        };
      }
    });

    datePickerInicio.valueProperty().addListener((ov, oldValue, newValue) -> {
      try {
        Date today = new Date();
        Date selectedDate = DateConvertUtils.asUtilDate(datePickerInicio.getValue());
        if (today.after(selectedDate)) {
          datePickerInicio.setValue(DateConvertUtils.asLocalDate(today));
        }
        selectedDate = DateConvertUtils.asUtilDate(datePickerInicio.getValue());
        Date dateFin = DateConvertUtils.asUtilDate(datePickerFin.getValue());
        if (selectedDate.after(dateFin)) {
          datePickerFin.setValue(datePickerInicio.getValue());
        }
      } catch (Exception ex) {
      };

    });

    datePickerFin.valueProperty().addListener((ov, oldValue, newValue) -> {
      try {
        if (validarFecha()) {
          Date date = new Date();
          datePickerFin.setValue(datePickerInicio.getValue());
        }
      } catch (Exception ex) {
      };
    });

    listViewAvisos.setItems(obtenerAvisos());
    listViewAvisos.getSelectionModel().selectFirst();
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz.
   *
   * @param event
   */
  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz.
   *
   * @param event
   */
  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
  }

  /**
   * Describe el comportamiento del sistema al oprimir el botón salir. 
   * @param event 
   */
  @FXML
  private void salir(MouseEvent event) {
    Stage stage = (Stage) botonAtras.getScene().getWindow();
    stage.close();
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
    listViewAvisos.setDisable(true);

    tfAsunto.requestFocus();
  }

  /**
   * Describe el comportamiento del sistema al oprimir el botón eliminar. 
   * @param event 
   */
  @FXML
  private void onActionEliminar(ActionEvent event) {
    Aviso aviso = listViewAvisos.getSelectionModel().getSelectedItem();
    if (Mensajes.displayWarningAlertWithChoice("Aviso: " + aviso.getAsunto(), "¿Desea eliminar este aviso?")) {
      if (eliminaAviso()) {
        Mensajes.displayInformation("Éxito", "El aviso se ha eliminado correctamente");
      }
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

  /**
   * Describe el comportamiento del sistema al presionar el botón guardar.
   * Si la bandera es verdadera, inserta el aviso en la base de datos.
   * Si la bandera es falsa, actualiza los datos del aviso en la base de datos.
   * @param event 
   */
  @FXML
  void onActionGuardar(ActionEvent event) {
    if (banderaAgregar) {
      if (!validarCamposVacios()) {
        deshabilitarCuadros();
        listViewAvisos.setDisable(false);
        if (agregarAviso()) {
          Mensajes.displayInformation("Éxito", "El aviso ha sido guardado correctamente");
        }
        limpiar();
        resetButtons();
        listViewAvisos.getSelectionModel().selectFirst();
      } else {
        Mensajes.displayInformation("Campos vacios", "Favor de introducir todos los campos.");
      }
    } else {
      if (!validarCamposVacios()) {
        deshabilitarCuadros();
        listViewAvisos.setDisable(false);
        if (editarAviso()) {
          Mensajes.displayInformation("Éxito", "Los cambios se han guardado en el sistema");
        }
        limpiar();
        resetButtons();
        listViewAvisos.getSelectionModel().selectFirst();
      } else {
        Mensajes.displayInformation("Campos vacios", "Favor de introducir todos los campos.");
      }
    }
  }

  /**
   * Describe el comportamiento del sistema al oprimir el botón cancelar. 
   * @param event 
   */
  @FXML
  void onActionCancelar(ActionEvent event) {
    limpiar();
    btnEditar.setVisible(true);
    btnEliminar.setVisible(true);
    btnAceptar.setVisible(true);

    btnGuardar.setVisible(false);
    btnCancelar.setVisible(false);
    listViewAvisos.getSelectionModel().selectFirst();
    listViewAvisos.setDisable(false);
    listViewAvisos.requestFocus();
    deshabilitarCuadros();
  }

  /**
   * Limpia los elementos de interfaz.
   */
  private void limpiar() {
    tfAsunto.clear();
    taContenido.clear();
    datePickerInicio.setValue(null);
    datePickerFin.setValue(null);
  }

  /**
   * Extrae los datos de un Aviso y los carga en componentes de interfaz.
   *
   * @param aviso el aviso del cual se quieren extraer los datos.
   */
  private void extraerDatos(Aviso aviso) {
    LocalDate fechaInicio = DateConvertUtils.asLocalDate(aviso.getFechaInicio());
    LocalDate fechaFin = DateConvertUtils.asLocalDate(aviso.getFechaFin());

    tfAsunto.setText(aviso.getAsunto());
    taContenido.setText(aviso.getContenido());
    datePickerInicio.setValue(fechaInicio);
    datePickerFin.setValue(fechaFin);
  }

  /**
   * Invoca un método de capa lógica para recuperar los Avisos de la base de datos.
   *
   * @return ObservableList cargado con los Avisos recuperados.
   */
  private ObservableList<Aviso> obtenerAvisos() {
    DAOAvisoImpl daoAviso = new DAOAvisoImpl();
    ObservableList<Aviso> listaAvisos = null;
    try {
      listaAvisos = FXCollections.observableList(daoAviso.obtenerAvisos());
    } catch (Exception ex) {
      String mensaje = "Error, intente más tarde";
      Mensajes.displayInformation("Error", mensaje);
    }

    return listaAvisos;
  }

  /**
   * Habilita la escritura en los componentes de interfaz.
   */
  private void habilitarCuadros() {
    paneDisable.toBack();
  }

  /**
   * Deshabilita la escritura en los componentes de interfaz.
   */
  private void deshabilitarCuadros() {
    paneDisable.toFront();
  }

  /**
   * Valida que los componentes de interfaz no se encuentren vacios.
   *
   * @return true si algún componente de interfaz se encuentra vacio.
   */
  private boolean validarCamposVacios() {
    if (tfAsunto.getText().trim().isEmpty()) {
      return true;
    }

    if (taContenido.getText().trim().isEmpty()) {
      return true;
    }

    if (datePickerInicio.getValue() == null || datePickerFin.getValue() == null) {
      return true;
    }

    return false;
  }

  /**
   * Valida que la fecha del datePicker de inicio sea anterior a la del datePicker de fin. 
   * @return true si la fecha de inicio esta después de la fecha de fin. 
   */
  private boolean validarFecha() {
    Date dateUno = DateConvertUtils.asUtilDate(datePickerInicio.getValue());
    Date dateDos = DateConvertUtils.asUtilDate(datePickerFin.getValue());
    if (dateUno.after(dateDos)) {
      return true;
    }
    return false;
  }

  /**
   * Invoca métodos de capa lógica para agregar un Aviso en la BD.
   *
   * @return true si el Aviso se inserto correctamente.
   */
  public boolean agregarAviso() {
    DAOAvisoImpl daoAviso = new DAOAvisoImpl();
    Aviso aviso = new Aviso();
    aviso.setAsunto(tfAsunto.getText());
    aviso.setContenido(taContenido.getText());
    aviso.setFechaInicio(DateConvertUtils.asUtilDate(datePickerInicio.getValue()));
    aviso.setFechaFin(DateConvertUtils.asUtilDate(datePickerFin.getValue()));

    try {
      daoAviso.insertarAviso(aviso);
      listViewAvisos.getItems().clear();
      listViewAvisos.setItems(obtenerAvisos());
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      Mensajes.displayInformation("Error", "Error, intente más tarde");
    }

    return false;
  }

  /**
   * Invoca métodos de capa lógica para editar un Aviso en la BD.
   *
   * @return true si el Aviso se edito correctamente.
   */
  public boolean editarAviso() {
    DAOAvisoImpl daoAviso = new DAOAvisoImpl();
    Aviso aviso = new Aviso();
    aviso.setIdAviso(listViewAvisos.getSelectionModel().getSelectedItem().getIdAviso());
    aviso.setAsunto(tfAsunto.getText());
    aviso.setContenido(taContenido.getText());
    aviso.setFechaInicio(DateConvertUtils.asUtilDate(datePickerInicio.getValue()));
    aviso.setFechaFin(DateConvertUtils.asUtilDate(datePickerFin.getValue()));

    try {
      daoAviso.actualizarAviso(aviso);
      listViewAvisos.getItems().clear();
      listViewAvisos.setItems(obtenerAvisos());
      return true;
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Error, intente más tarde");
    }

    return false;
  }

  /**
   * Invoca métodos de capa lógica para eliminar un Aviso en la BD.
   *
   * @return true si el Aviso se elimino correctamente.
   */
  public boolean eliminaAviso() {
    DAOAvisoImpl daoAviso = new DAOAvisoImpl();
    int idAviso = listViewAvisos.getSelectionModel().getSelectedItem().getIdAviso();

    try {
      daoAviso.eliminarAviso(idAviso);
      listViewAvisos.getItems().clear();
      listViewAvisos.setItems(obtenerAvisos());
      return true;
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Error, intente más tarde");
    }
    return false;
  }

  /**
   * Muestra la ventana. 
   * @param loader 
   */
  public void mostrarVentana(FXMLLoader loader) {
    try {
      Stage stagePrincipal = new Stage();
      Parent root = (Parent) loader.load();
      Scene scene = new Scene(root);
      stagePrincipal.setScene(scene);
      stagePrincipal.show();
    } catch (IOException ex) {
      Logger.getLogger(IUAdministrarAvisoController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Coordinador getCoordinador() {
    return coordinador;
  }

  public void setCoordinador(Coordinador coordinador) {
    this.coordinador = coordinador;
  }
}

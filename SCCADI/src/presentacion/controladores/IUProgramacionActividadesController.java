package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.dominio.ActividadProgramada;
import logica.dominio.ExperienciaEducativa;
import utilerias.DateConvertUtils;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Alan
 */
public class IUProgramacionActividadesController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private TextField tfBuscar;
  @FXML
  private ComboBox<Integer> comboModulo;
  @FXML
  private ComboBox<Integer> comboUnidad;
  @FXML
  private TextField tfNombre;
  @FXML
  private ComboBox<ExperienciaEducativa> comboExperiencia;
  @FXML
  private DatePicker datePickerInicio;
  @FXML
  private DatePicker datePickerFin;
  @FXML
  private ListView<ActividadProgramada> listViewActividades;
  @FXML
  private JFXButton btnAceptar;
  @FXML
  private JFXButton btnEditar;
  @FXML
  private JFXButton btnEliminar;
  @FXML
  private Pane paneCombos;

  private ObservableList<ExperienciaEducativa> experiencias;
  private ObservableList<ActividadProgramada> actividades;

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    experiencias = obtenerExperiencias();
    actividades = obtenerActividades();

    comboExperiencia.getSelectionModel().selectedItemProperty().addListener(event -> {
      ExperienciaEducativa experiencia = comboExperiencia.getSelectionModel().getSelectedItem();
      cargarModulos(experiencia);
      cargarUnidades(experiencia);
    });

    listViewActividades.getSelectionModel().selectedItemProperty().addListener(event -> {
      if (listViewActividades.getItems().isEmpty()) {
        limpiar();
      } else{
        try{
          extraerDatos(listViewActividades.getSelectionModel().getSelectedItem());
        }catch(Exception ex){};
      }
      
    });
    
    tfBuscar.setOnKeyReleased(event->{
      if (tfBuscar.getText().trim().isEmpty()) {
        listViewActividades.setItems(actividades);
      }else{
        listViewActividades.setItems(filtrarActividades(tfBuscar.getText()));
        listViewActividades.getSelectionModel().selectFirst();
      }
    });

    cargarTablaActividades();
    cargarComboExperiencias();
    listViewActividades.getSelectionModel().selectFirst();
  }

  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
  }

  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
  }

  @FXML
  private void salir(MouseEvent event) {
  }

  private void cargarComboExperiencias() {
    comboExperiencia.setItems(experiencias);
  }

  private void cargarTablaActividades() {
    listViewActividades.setItems(actividades);
  }

  private void cargarModulos(ExperienciaEducativa experiencia) {
    ObservableList<Integer> modulos = FXCollections.observableArrayList();
    for (int i = 1; i <= experiencia.getNumModulos(); i++) {
      modulos.add(i);
    }
    comboModulo.setItems(modulos);
  }

  private void cargarUnidades(ExperienciaEducativa experiencia) {
    ObservableList<Integer> unidades = FXCollections.observableArrayList();
    for (int i = 1; i <= experiencia.getNumUnidades(); i++) {
      unidades.add(i);
    }
    comboUnidad.setItems(unidades);
  }

  private ObservableList<ActividadProgramada> obtenerActividades() {
    DAOActividadProgramadaImpl daoActividad = new DAOActividadProgramadaImpl();
    ObservableList<ActividadProgramada> ListaActividades = null;
    try {
      ListaActividades = FXCollections.observableList(daoActividad.obtenerActividadesProgramadas());
    } catch (Exception ex) {
      String mensaje = "Conexión fallida, no se ha podido recuperar la información de la base de datos, intenta más tarde ";
      Mensajes.displayInformation("Error", mensaje);
    }

    return ListaActividades;
  }

  private void extraerDatos(ActividadProgramada actividad) {
    ExperienciaEducativa experiencia = null;

    for (int i = 0; i < experiencias.size(); i++) {
      if (experiencias.get(i).getIdExperiencia() == actividad.getIdExperiencia()) {
        experiencia = experiencias.get(i);
      }
    }

    comboExperiencia.getSelectionModel().select(experiencia);
    tfNombre.setText(actividad.getNombre());
    comboModulo.getSelectionModel().select(actividad.getModulo() - 1);
    comboUnidad.getSelectionModel().select(actividad.getUnidad() - 1);

    LocalDate fechaInicio = DateConvertUtils.asLocalDate(actividad.getFechaInicio());
    LocalDate fechaFin = DateConvertUtils.asLocalDate(actividad.getFechaFin());

    datePickerInicio.setValue(fechaInicio);
    datePickerFin.setValue(fechaFin);
  }

  public ObservableList<ExperienciaEducativa> obtenerExperiencias() {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    ObservableList<ExperienciaEducativa> ListaExperiencias = null;
    try {
      ListaExperiencias = FXCollections.observableList(daoExperiencia.obtenerExperiencias());
    } catch (Exception ex) {
      String mensaje = "Conexión fallida, no se ha podido recuperar la información de la base de datos, intenta más tarde ";
      Mensajes.displayInformation("Error", mensaje);
    }
    return ListaExperiencias;
  }

  private void limpiar() {
    tfNombre.clear();
    cargarComboExperiencias();
    datePickerInicio.setValue(null);
    datePickerFin.setValue(null);
  }

  private void habilitarCuadros() {
    paneCombos.toBack();
  }

  private void deshabilitarCuadros() {
    paneCombos.toFront();
  }

  @FXML
  private void onActionAgregar(ActionEvent event) {
    if (btnAceptar.getText().equals("AGREGAR")) {
      btnAceptar.setText("GUARDAR");
      limpiar();
      habilitarCuadros();
      tfNombre.requestFocus();

      //Desactivar las demas cosas
      listViewActividades.setDisable(true);
      btnEditar.setDisable(true);
      btnEliminar.setDisable(true);
      tfBuscar.setDisable(true);
      comboExperiencia.getSelectionModel().selectFirst();
      comboModulo.getSelectionModel().selectFirst();
      comboUnidad.getSelectionModel().selectFirst();
    } else {
      if (!validarCamposVacios()) {
        btnAceptar.setText("AGREGAR");
        deshabilitarCuadros();
        //Activar las demas cosas
        listViewActividades.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        tfBuscar.setDisable(false);
        if (agregarActividadProgramada()) {
          Mensajes.displayInformation("Éxito", "La actividad ha sido registrada exitosamente ¿deseas asignarla a un asesor?");
        }
        listViewActividades.getSelectionModel().selectFirst();
      } else {
        Mensajes.displayInformation("Campos vacios", "Se requieren todos los campos");
      }
    }
  }

  @FXML
  private void onActionEditar(ActionEvent event) {
    if (btnEditar.getText().equals("EDITAR")) {
      btnEditar.setText("ACTUALIZAR");
      habilitarCuadros();
      tfNombre.requestFocus();

      //Desactivar las demas cosas
      listViewActividades.setDisable(true);
      btnAceptar.setDisable(true);
      btnEliminar.setDisable(true);
      tfBuscar.setDisable(true);
    } else {
      if (!validarCamposVacios()) {
        btnEditar.setText("EDITAR");
        deshabilitarCuadros();
        //Desactivar las demas cosas
        listViewActividades.setDisable(false);
        btnAceptar.setDisable(false);
        btnEliminar.setDisable(false);
        tfBuscar.setDisable(false);
        if (editarActividadProgramada()) {
          Mensajes.displayInformation("Éxito", "Los datos de la actividad han sido actualizados correctamente");
        }
        listViewActividades.getSelectionModel().selectFirst();
      }else {
        Mensajes.displayInformation("Campos vacios", "Se requieren todos los campos");
      }
    }
  }

  @FXML
  private void onActionEliminar(ActionEvent event) {
    ActividadProgramada actividad = listViewActividades.getSelectionModel().getSelectedItem();
    if (Mensajes.displayWarningAlertWithChoice("Actividad: "+actividad.getNombre(), "¿Seguro que quiere eliminar esta actividad?")) {
      if (eliminarActividadProgramada()) {
        Mensajes.displayInformation("Éxito", "Eliminación exitosa");
      }
    }
  }

  private boolean validarCamposVacios() {
    if (tfNombre.getText().trim().isEmpty()) {
      return true;
    }

    if (datePickerInicio.getValue() == null || datePickerFin.getValue() == null) {
      return true;
    }

    return false;
  }

  public boolean agregarActividadProgramada() {
    DAOActividadProgramadaImpl daoActividad = new DAOActividadProgramadaImpl();
    ActividadProgramada actividad = new ActividadProgramada();
    actividad.setNombre(tfNombre.getText());
    actividad.setFechaInicio(DateConvertUtils.asUtilDate(datePickerInicio.getValue()));
    actividad.setFechaFin(DateConvertUtils.asUtilDate(datePickerFin.getValue()));
    actividad.setModulo(comboModulo.getSelectionModel().getSelectedItem());
    actividad.setUnidad(comboUnidad.getSelectionModel().getSelectedItem());
    actividad.setIdExperiencia(comboExperiencia.getSelectionModel().getSelectedItem().getIdExperiencia());
    try {
      daoActividad.insertarActividadProgramada(actividad);
      listViewActividades.getItems().clear();
      actividades = obtenerActividades();
      cargarTablaActividades();
      return true;
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Conexión fallida, no se ha podido guardar la nueva actividad, vuelve a intentar");
    }
    return false;
  }

  public boolean editarActividadProgramada() {
    DAOActividadProgramadaImpl daoActividad = new DAOActividadProgramadaImpl();
    ActividadProgramada actividad = new ActividadProgramada();
    actividad.setIdActividadProgramada(listViewActividades.getSelectionModel().getSelectedItem().getIdActividadProgramada());
    actividad.setNombre(tfNombre.getText());
    actividad.setFechaInicio(DateConvertUtils.asUtilDate(datePickerInicio.getValue()));
    actividad.setFechaFin(DateConvertUtils.asUtilDate(datePickerFin.getValue()));
    actividad.setModulo(comboModulo.getSelectionModel().getSelectedItem());
    actividad.setUnidad(comboUnidad.getSelectionModel().getSelectedItem());
    actividad.setIdExperiencia(comboExperiencia.getSelectionModel().getSelectedItem().getIdExperiencia());

    try {
      daoActividad.actualizarActividadProgramada(actividad);
      listViewActividades.getItems().clear();
      actividades = obtenerActividades();
      cargarTablaActividades();
      return true;
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Conexión fallida, no se ha podido guardar la nueva actividad, vuelve a intentar");
    }
    return false;
  }

  public boolean eliminarActividadProgramada() {
    DAOActividadProgramadaImpl daoActividad = new DAOActividadProgramadaImpl();
    int idActividad = listViewActividades.getSelectionModel().getSelectedItem().getIdActividadProgramada();

    try {
      daoActividad.eliminarActividadProgramada(idActividad);
      listViewActividades.getItems().clear();
      actividades = obtenerActividades();
      cargarTablaActividades();
      return true;
    } catch (Exception ex) {
      Mensajes.displayInformation("Error", "Conexión fallida, no se ha podido guardar la nueva actividad, vuelve a intentar");
    }
    return false;
  }
  
  private ObservableList<ActividadProgramada> filtrarActividades(String criterio){
    int size = actividades.size(); 
    ObservableList<ActividadProgramada> filtrada = FXCollections.observableArrayList();
    for (int i = 0; i < actividades.size(); i++) {
      if (actividades.get(i).getNombre().trim().contains(criterio.trim())) {
        filtrada.add(actividades.get(i)); 
      }
    }
    return filtrada; 
  }
}

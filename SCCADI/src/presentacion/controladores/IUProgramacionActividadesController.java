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
 * Controlador de la ventana IUProgramacionActividades
 * Permite gestionar las actividades programadas. 
 * @author Alan Yoset García Cruz
 * @version 1.0 
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
   * Inicializa los componentes de la interfaz. 
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
      } else {
        try {
          extraerDatos(listViewActividades.getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
        };
      }

    });

    tfBuscar.setOnKeyReleased(event -> {
      if (tfBuscar.getText().trim().isEmpty()) {
        listViewActividades.setItems(actividades);
      } else {
        listViewActividades.setItems(filtrarActividades(tfBuscar.getText()));
        listViewActividades.getSelectionModel().selectFirst();
      }
    });

    cargarTablaActividades();
    cargarComboExperiencias();
    listViewActividades.getSelectionModel().selectFirst();
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz. 
   * @param event 
   */
  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz. 
   * @param event 
   */
  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
  }

  /**
   * Regresa el sistema a la ventana anterior. 
   * @param event 
   */
  @FXML
  private void salir(MouseEvent event) {
  }

  /**
   * Carga el comboExperiencias de la interfaz con la lista de experiencias global. 
   */
  private void cargarComboExperiencias() {
    comboExperiencia.setItems(experiencias);
  }

  /**
   * Carga el tableView de actividades de la interfaz con la lista de actividades global. 
   */
  private void cargarTablaActividades() {
    listViewActividades.setItems(actividades);
  }

  /**
   * Carga el combo de módulos de la interfaz de acuerdo con una experiencia educativa. 
   * @param experiencia ExperienciaEducativa de la cual se quieren cargar los módulos. 
   */
  private void cargarModulos(ExperienciaEducativa experiencia) {
    ObservableList<Integer> modulos = FXCollections.observableArrayList();
    for (int i = 1; i <= experiencia.getNumModulos(); i++) {
      modulos.add(i);
    }
    comboModulo.setItems(modulos);
  }

  /**
   * Carga el combo de unidades de la interfaz de acuerdo con una experiencia educativa. 
   * @param experiencia ExperienciaEducativa de la cual se quieren cargar las unidades. 
   */
  private void cargarUnidades(ExperienciaEducativa experiencia) {
    ObservableList<Integer> unidades = FXCollections.observableArrayList();
    for (int i = 1; i <= experiencia.getNumUnidades(); i++) {
      unidades.add(i);
    }
    comboUnidad.setItems(unidades);
  }

  /**
   * Invoca un método de capa lógica para recuperar las actividades programadas de la base de datos. 
   * @return ObservableList cargado con las actividades programadas recuperadas. 
   */
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

  /**
   * Extrae los datos de una ActividadProgramada y los carga en componentes de interfaz. 
   * @param actividad la ActividadProgramada de la cual se quieren extraer los datos. 
   */
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

  /**
   * Invoca un método de capa lógica para recuperar las experiencias educativas de la base de datos. 
   * @return ObservableList cargada de las experiencias educativas recuperadas. 
   */
  public ObservableList<ExperienciaEducativa> obtenerExperiencias() {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    ObservableList<ExperienciaEducativa> ListaExperiencias = null;
    try {
      ListaExperiencias = FXCollections.observableList(daoExperiencia.obtenerExperiencias());
    } catch (Exception ex) {
      //String mensaje = "Conexión fallida, no se ha podido recuperar la información de la base de datos, intenta más tarde ";
      //Mensajes.displayInformation("Error", mensaje);
    }
    return ListaExperiencias;
  }

  /**
   * Limpia los elementos de interfaz. 
   */
  private void limpiar() {
    tfNombre.clear();
    cargarComboExperiencias();
    datePickerInicio.setValue(null);
    datePickerFin.setValue(null);
  }

  /**
   * Habilita la escritura en los componentes de interfaz.
   */
  private void habilitarCuadros() {
    paneCombos.toBack();
  }

  /**
   * Deshabilita la escritura en los componentes de interfaz.
   */
  private void deshabilitarCuadros() {
    paneCombos.toFront();
  }

  /**
   * Define el comportamiento del sistema cuando se da clic en el botón Agregar. 
   * @param event 
   */
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
        limpiar();
        listViewActividades.getSelectionModel().selectFirst();
      } else {
        Mensajes.displayInformation("Campos vacios", "Se requieren todos los campos");
      }
    }
  }

  /**
   * Define el comportamiento del sistema cuando se da clic en el botón Editar.
   * @param event 
   */
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
        limpiar();
        listViewActividades.getSelectionModel().selectFirst();
      } else {
        Mensajes.displayInformation("Campos vacios", "Se requieren todos los campos");
      }
    }
  }

  /**
   * Define el comportamiento del sistema cuando se da clic en el botón Eliminar. 
   * @param event 
   */
  @FXML
  private void onActionEliminar(ActionEvent event) {
    ActividadProgramada actividad = listViewActividades.getSelectionModel().getSelectedItem();
    if (Mensajes.displayWarningAlertWithChoice("Actividad: " + actividad.getNombre(), "¿Seguro que quiere eliminar esta actividad?")) {
      if (eliminarActividadProgramada()) {
        Mensajes.displayInformation("Éxito", "Eliminación exitosa");
      }
    }
  }

  /**
   * Valida que los componentes de interfaz no se encuentren vacios. 
   * @return true si algún componente de interfaz se encuentra vacio. 
   */
  private boolean validarCamposVacios() {
    if (tfNombre.getText().trim().isEmpty()) {
      return true;
    }

    if (datePickerInicio.getValue() == null || datePickerFin.getValue() == null) {
      return true;
    }

    return false;
  }

  /**
   * Invoca métodos de capa lógica para insertar una nueva ActividadProgramada en la BD. 
   * @return true si la ActividadProgramada se inserto correctamente. 
   */
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

  /**
   * Invoca métodos de capa lógica para editar una nueva ActividadProgramada en la BD. 
   * @return true si la ActividadProgramada se actualizó correctamente. 
   */
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

  /**
   * Invoca métodos de capa lógica para eliminar una nueva ActividadProgramada en la BD. 
   * @return true si la ActividadProgramada se elimino correctamente. 
   */
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

  /**
   * Permite filtrar las actividades de la lista global de acuerdo con un criterio. 
   * @param criterio el criterio para filtrar las actividades. 
   * @return ObservableList con las actividades que aplican el criterio. 
   */
  private ObservableList<ActividadProgramada> filtrarActividades(String criterio) {
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

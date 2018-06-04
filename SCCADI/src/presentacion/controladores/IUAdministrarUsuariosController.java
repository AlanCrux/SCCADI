/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.TokenType;
import logica.daoimpl.DAOAsesorImpl;
import logica.daoimpl.DAOCoordinadorImpl;
import logica.daoimpl.DAORecepcionistaImpl;
import logica.dominio.Asesor;
import logica.dominio.Coordinador;
import logica.dominio.Recepcionista;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUAdministrarUsuariosController implements Initializable {

    @FXML
    private TableView<Asesor> tablaAsesores;

    @FXML
    private TableColumn<Asesor, Integer> columnaNumPersonalAsesor;

    @FXML
    private TableColumn<Asesor, String> columnaNombreAsesor;

    @FXML
    private Label labelPersonal;

    @FXML
    private JFXTextField textNumPersonal;

    @FXML
    private JFXTextField textCorreo;

    @FXML
    private JFXTextField textNombre;

    @FXML
    private JFXPasswordField textContrasena;

    @FXML
    private JFXButton botonAgregar;

    @FXML
    private JFXButton botonEditar;

    @FXML
    private JFXButton botonEliminar;

    @FXML
    private Label labelMensaje;

    @FXML
    private TextField textBuscar;

    @FXML
    private Label labelTipoUsuario;

    @FXML
    private JFXComboBox<String> comboTipoUsuario;

    @FXML
    private JFXComboBox<String> comboTipoPersonal;

    @FXML
    private TableView<Recepcionista> tablaRecepcionistas;

    @FXML
    private TableColumn<Recepcionista, Integer> columnaNumPersonalRecep;

    @FXML
    private TableColumn<Recepcionista, String> columnaNombreRecep;

    @FXML
    private TableView<Coordinador> tablaCoordinadores;

    @FXML
    private TableColumn<Coordinador, Integer> columnaNumPersonalCoord;

    @FXML
    private TableColumn<Coordinador, String> columnaNombreCoord;

    ObservableList<Asesor> listaAsesores;
    ObservableList<Recepcionista> listaRecepcionistas;
    ObservableList<Coordinador> listaCoordinadores;
    ObservableList<String> roles;
    DAOAsesorImpl asesor = new DAOAsesorImpl();
    DAORecepcionistaImpl recepcionista = new DAORecepcionistaImpl();
    DAOCoordinadorImpl coordinador = new DAOCoordinadorImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roles = FXCollections.observableArrayList("Asesor", "Recepcionista", "Coordinador");
        comboTipoUsuario.setItems(roles);
        comboTipoPersonal.setItems(roles);
        comboTipoPersonal.getSelectionModel().select(0);
        llenarTabla(comboTipoPersonal.getSelectionModel().getSelectedItem());
        comboTipoPersonal.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                llenarTabla(comboTipoPersonal.getSelectionModel().getSelectedItem());
            } catch (Exception ex) {
                Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
            }
        });
        textBuscar.setOnKeyReleased(event -> {
            try {
                if (textBuscar.getText().trim().isEmpty()) {
                    tablaAsesores.setItems(listaAsesores);
                    tablaRecepcionistas.setItems(listaRecepcionistas);
                    tablaCoordinadores.setItems(listaCoordinadores);
                } else {
                    tablaAsesores.setItems(filtrarTablaAsesor(textBuscar.getText()));
                    tablaAsesores.getSelectionModel().selectFirst();

                    tablaRecepcionistas.setItems(filtrarTablaRecepcionista(textBuscar.getText()));
                    tablaRecepcionistas.getSelectionModel().selectFirst();

                    tablaCoordinadores.setItems(filtrarTablaCoordinador(textBuscar.getText()));
                    tablaCoordinadores.getSelectionModel().selectFirst();
                }
            } catch (Exception e) {

            }
        });
    }

    public void llenarTabla(String tipoUsuario) {
        switch (tipoUsuario) {
            case "Asesor":
                tablaCoordinadores.toBack();
                tablaRecepcionistas.toBack();
                tablaAsesores.toFront();
                tablaAsesores.toFront();
                labelPersonal.setText("Asesores");
                try {
                    listaAsesores = FXCollections.observableList(asesor.obtenerAsesores());
                    columnaNombreAsesor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    columnaNumPersonalAsesor.setCellValueFactory(new PropertyValueFactory<>("noPersonal"));
                    tablaAsesores.setItems(listaAsesores);
                } catch (Exception ex) {
                    Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
                }
                break;
            case "Recepcionista":
                labelPersonal.setText("Recepcionistas");
                tablaCoordinadores.toBack();
                tablaAsesores.toBack();
                tablaRecepcionistas.toFront();
                tablaRecepcionistas.toFront();
                try {
                    listaRecepcionistas = FXCollections.observableList(recepcionista.obtenerRecepcionistas());
                    columnaNombreRecep.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    columnaNumPersonalRecep.setCellValueFactory(new PropertyValueFactory<>("noPersonal"));
                    tablaRecepcionistas.setItems(listaRecepcionistas);
                } catch (Exception ex) {
                    Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
                }
                break;
            case "Coordinador":
                labelPersonal.setText("Coordinadores");

                tablaAsesores.toBack();
                tablaRecepcionistas.toBack();
                tablaCoordinadores.toFront();
                tablaCoordinadores.toFront();
                try {
                    listaCoordinadores = FXCollections.observableList(coordinador.obtenerCoordinadores());
                    columnaNombreCoord.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    columnaNumPersonalCoord.setCellValueFactory(new PropertyValueFactory<>("noPersonal"));
                    tablaCoordinadores.setItems(listaCoordinadores);
                } catch (Exception ex) {
                    Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
                }
                break;
        }
    }

    private ObservableList<Asesor> filtrarTablaAsesor(String criterio) {
        int size = listaAsesores.size();
        ObservableList<Asesor> filtrada = FXCollections.observableArrayList();
        for (int i = 0; i < listaAsesores.size(); i++) {
            if (listaAsesores.get(i).getNombre().trim().contains(criterio.trim())) {
                filtrada.add(listaAsesores.get(i));
            }
        }
        return filtrada;
    }

    private ObservableList<Recepcionista> filtrarTablaRecepcionista(String criterio) {
        int size = listaRecepcionistas.size();
        ObservableList<Recepcionista> filtrada = FXCollections.observableArrayList();
        for (int i = 0; i < listaRecepcionistas.size(); i++) {
            if (listaRecepcionistas.get(i).getNombre().trim().contains(criterio.trim())) {
                filtrada.add(listaRecepcionistas.get(i));
            }
        }
        return filtrada;
    }

    private ObservableList<Coordinador> filtrarTablaCoordinador(String criterio) {
        int size = listaCoordinadores.size();
        ObservableList<Coordinador> filtrada = FXCollections.observableArrayList();
        for (int i = 0; i < listaCoordinadores.size(); i++) {
            if (listaCoordinadores.get(i).getNombre().trim().contains(criterio.trim())) {
                filtrada.add(listaCoordinadores.get(i));
            }
        }
        return filtrada;
    }

    @FXML
    public void mostrarAsesor() {
        Asesor asesorSeleccionado = tablaAsesores.getSelectionModel().getSelectedItem();
        textContrasena.setText(asesorSeleccionado.getContrasena());
        textCorreo.setText(asesorSeleccionado.getCorreo());
        textNombre.setText(asesorSeleccionado.getNombre());
        textNumPersonal.setText(Integer.toString(asesorSeleccionado.getNoPersonal()));
    }

    @FXML
    public void mostrarRecepcionista() {
        Recepcionista recepcionistaSeleccionada = tablaRecepcionistas.getSelectionModel().getSelectedItem();
        textContrasena.setText(recepcionistaSeleccionada.getContrasena());
        textCorreo.setText(recepcionistaSeleccionada.getCorreo());
        textNombre.setText(recepcionistaSeleccionada.getNombre());
        textNumPersonal.setText(Integer.toString(recepcionistaSeleccionada.getNoPersonal()));
    }

    @FXML
    public void mostrarCoordinador() {
        Coordinador coordinadorSeleccionado = tablaCoordinadores.getSelectionModel().getSelectedItem();
        textContrasena.setText(coordinadorSeleccionado.getContrasena());
        textCorreo.setText(coordinadorSeleccionado.getCorreo());
        textNombre.setText(coordinadorSeleccionado.getNombre());
        textNumPersonal.setText(Integer.toString(coordinadorSeleccionado.getNoPersonal()));
    }

    @FXML
    public void verificarNumeroPersonal() {
        Pattern patternFileName = Pattern.compile("^[0-9]*$");
        TextFormatter formato = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternFileName.matcher(change.getControlNewText()).matches() ? change : null;
        });
        textNumPersonal.setTextFormatter(formato);
    }

    @FXML
    public void insertarUsuario() {
        if (botonAgregar.getText().equals("Agregar")) {
            labelMensaje.setVisible(false);
            habilitarCampos();
            botonAgregar.setText("Guardar");
            botonEditar.setDisable(true);
            botonEliminar.setDisable(true);

        } else if (validarCamposVacios() || comboTipoUsuario.getSelectionModel().getSelectedIndex() == -1) {
            labelMensaje.setText("Campos vacios");
            labelMensaje.setVisible(true);

        } else {
            switch (comboTipoUsuario.getSelectionModel().getSelectedItem()) {
                case "Asesor":
                    try {
                        insertarAsesor();
                    } catch (Exception ex) {
                        Mensajes.displayWarningAlert("Usuario repetido", "Ya existe un asesor con ese numero de personal");
                    }
                    break;
                case "Recepcionista":
                    try {
                        insertarRecepcionista();
                    } catch (Exception ex) {
                        Mensajes.displayWarningAlert("Usuario repetido", "Ya existe una recepcionista con ese numero de personal");
                    }
                    break;
                case "Coordinador":
                    try {
                        insertarCoodinador();
                    } catch (Exception ex) {
                        Mensajes.displayWarningAlert("Usuario repetido", "Ya existe un coordinador con ese numero de personal");
                    }
                    break;
                default:
                    deshabilitarCampos();
                    botonAgregar.setText("Agregar");
                    botonEditar.setDisable(false);
                    botonEliminar.setDisable(false);
                    break;
            }

        }

    }

    public void insertarAsesor() throws MySQLIntegrityConstraintViolationException, Exception {
        try {
            Asesor asesorNuevo = new Asesor();
            asesorNuevo.setContrasena(textContrasena.getText());
            asesorNuevo.setCorreo(textCorreo.getText());
            asesorNuevo.setNombre(textNombre.getText());
            asesorNuevo.setNoPersonal(Integer.parseInt(textNumPersonal.getText()));

            if (asesor.insertarAsesor(asesorNuevo)) {
                labelMensaje.setText("¡Registro exitoso!");
                labelMensaje.setVisible(true);
            }
        } catch (Exception e) {
            throw e;
        }
        llenarTabla("Asesor");
    }

    public void insertarRecepcionista() throws MySQLIntegrityConstraintViolationException, Exception {
        try {
            Recepcionista recepcionistaNueva = new Recepcionista();
            recepcionistaNueva.setContrasena(textContrasena.getText());
            recepcionistaNueva.setCorreo(textCorreo.getText());
            recepcionistaNueva.setNombre(textNombre.getText());
            recepcionistaNueva.setNoPersonal(Integer.parseInt(textNumPersonal.getText()));
            if (recepcionista.insertarRecepcionista(recepcionistaNueva)) {
                labelMensaje.setText("¡Registro exitoso!");
                labelMensaje.setVisible(true);
            }
        } catch (Exception e) {
            throw e;
        }
        llenarTabla("Recepcionista");
    }

    public void insertarCoodinador() throws MySQLIntegrityConstraintViolationException, Exception {
        try {
            Coordinador coodinadorNuevo = new Coordinador();
            coodinadorNuevo.setContrasena(textContrasena.getText());
            coodinadorNuevo.setCorreo(textCorreo.getText());
            coodinadorNuevo.setNombre(textNombre.getText());
            coodinadorNuevo.setNoPersonal(Integer.parseInt(textNumPersonal.getText()));
            if (coordinador.insertarCoordinador(coodinadorNuevo)) {
                labelMensaje.setText("¡Registro exitoso!");
                labelMensaje.setVisible(true);
            }
        } catch (Exception e) {
            throw e;
        }
        llenarTabla("Coordinador");
    }

    public void habilitarCampos() {
        labelTipoUsuario.setVisible(true);
        comboTipoUsuario.setVisible(true);

        tablaAsesores.setDisable(true);
        tablaRecepcionistas.setDisable(true);

        textContrasena.setText("");
        textCorreo.setText("");
        textNombre.setText("");
        textNumPersonal.setText("");

        textContrasena.setEditable(true);
        textContrasena.setStyle("-jfx-unfocus-color: #26C300");
        textCorreo.setEditable(true);
        textCorreo.setStyle("-jfx-unfocus-color: #26C300");
        textNombre.setEditable(true);
        textNombre.setStyle("-jfx-unfocus-color: #26C300");
        textNumPersonal.setEditable(true);
        textNumPersonal.setStyle("-jfx-unfocus-color: #26C300");
    }

    public void deshabilitarCampos() {
        labelTipoUsuario.setVisible(false);
        comboTipoUsuario.setVisible(false);

        tablaAsesores.setDisable(false);
        tablaRecepcionistas.setDisable(false);

        textContrasena.setText("");
        textCorreo.setText("");
        textNombre.setText("");
        textNumPersonal.setText("");

        textContrasena.setEditable(false);
        textContrasena.setStyle("-jfx-unfocus-color: BLACK");
        textCorreo.setEditable(false);
        textCorreo.setStyle("-jfx-unfocus-color: BLACK");
        textNombre.setEditable(false);
        textNombre.setStyle("-jfx-unfocus-color: BLACK");
        textNumPersonal.setEditable(false);
        textNumPersonal.setStyle("-jfx-unfocus-color: BLACK");
    }

    public boolean validarCamposVacios() {
        if (textContrasena.getText().equals("") || textCorreo.getText().equals("") || textNombre.getText().equals("")
                || textNumPersonal.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void editarUsuario() {
        if (botonEditar.getText().equals("Editar")) {
            labelMensaje.setVisible(false);
            habilitarCampos2();
            botonEditar.setText("Guardar");
            botonAgregar.setDisable(true);
            botonEliminar.setDisable(true);
            labelTipoUsuario.setVisible(false);
            comboTipoUsuario.setVisible(false);

        } else if (validarCamposVacios()) {
            labelMensaje.setText("Campos vacios");
            labelMensaje.setVisible(true);
        } else if (tablaRecepcionistas.getSelectionModel().getSelectedIndices().size() > 0
                && tablaRecepcionistas.getSelectionModel().getSelectedItem().getTipo().equals("recepcionista")) {
            try {
                Recepcionista recepcionistaNueva = new Recepcionista();
                recepcionistaNueva.setContrasena(textContrasena.getText());
                recepcionistaNueva.setCorreo(textCorreo.getText());
                recepcionistaNueva.setNombre(textNombre.getText());
                recepcionistaNueva.setNoPersonal(Integer.parseInt(textNumPersonal.getText()));

                if (recepcionista.actualizarRecepcionista(recepcionistaNueva)) {
                    System.out.println(recepcionistaNueva.getNombre());
                    System.out.println("actualizacion exitosa ");
                    labelMensaje.setText("Actualización exitosa");
                    labelMensaje.setVisible(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(IUAdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            llenarTabla("Recepcionista");
            deshabilitarCampos();
            botonEditar.setText("Editar");
            botonAgregar.setDisable(false);
            botonEliminar.setDisable(false);

        } else if (tablaAsesores.getSelectionModel().getSelectedIndices().size() > 0
                && tablaAsesores.getSelectionModel().getSelectedItem().getTipo().equals("asesor")) {
            try {
                Asesor asesorNuevo = new Asesor();
                asesorNuevo.setContrasena(textContrasena.getText());
                asesorNuevo.setCorreo(textCorreo.getText());
                asesorNuevo.setNombre(textNombre.getText());
                asesorNuevo.setNoPersonal(Integer.parseInt(textNumPersonal.getText()));

                if (asesor.actualizarAsesor(asesorNuevo)) {
                    labelMensaje.setText("Actualización exitosa");
                    labelMensaje.setVisible(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(IUAdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            llenarTabla("Asesor");
            deshabilitarCampos();
            botonEditar.setText("Editar");
            botonAgregar.setDisable(false);
            botonEliminar.setDisable(false);
        } else if (tablaCoordinadores.getSelectionModel().getSelectedIndices().size() > 0
                && tablaCoordinadores.getSelectionModel().getSelectedItem().getTipo().equals("coordinador")) {
            try {
                Coordinador coodinadorNuevo = new Coordinador();
                coodinadorNuevo.setContrasena(textContrasena.getText());
                coodinadorNuevo.setCorreo(textCorreo.getText());
                coodinadorNuevo.setNombre(textNombre.getText());
                coodinadorNuevo.setNoPersonal(Integer.parseInt(textNumPersonal.getText()));

                if (coordinador.actualizarCoordinador(coodinadorNuevo)) {
                    labelMensaje.setText("Actualización exitosa");
                    labelMensaje.setVisible(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(IUAdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            llenarTabla("Coordinador");
            deshabilitarCampos();
            botonEditar.setText("Editar");
            botonAgregar.setDisable(false);
            botonEliminar.setDisable(false);
        }
    }

    @FXML
    public void eliminarUsuario() {
        if (tablaRecepcionistas.getSelectionModel().getSelectedIndices().size() > 0
                && tablaRecepcionistas.getSelectionModel().getSelectedItem().getTipo().equals("recepcionista")) {
            try {
                Recepcionista recpEliminar = tablaRecepcionistas.getSelectionModel().getSelectedItem();
                if (recepcionista.eliminarRecepcionista(recpEliminar.getNoPersonal())) {
                    labelMensaje.setText("Eliminacion exitosa");
                    labelMensaje.setVisible(true);
                    llenarTabla("Recepcionista");
                    textContrasena.setText("");
                    textCorreo.setText("");
                    textNombre.setText("");
                    textNumPersonal.setText("");
                }
            } catch (Exception ex) {
                Logger.getLogger(IUAdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (tablaAsesores.getSelectionModel().getSelectedIndices().size() > 0
                && tablaAsesores.getSelectionModel().getSelectedItem().getTipo().equals("asesor")) {
            try {
                Asesor asesorEliminar = tablaAsesores.getSelectionModel().getSelectedItem();
                if (asesor.eliminarAsesor(asesorEliminar.getNoPersonal())) {
                    labelMensaje.setText("Eliminacion exitosa");
                    labelMensaje.setVisible(true);
                    llenarTabla("Asesor");
                    textContrasena.setText("");
                    textCorreo.setText("");
                    textNombre.setText("");
                    textNumPersonal.setText("");
                }
            } catch (Exception ex) {
                Logger.getLogger(IUAdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (tablaCoordinadores.getSelectionModel().getSelectedIndices().size() > 0
                && tablaCoordinadores.getSelectionModel().getSelectedItem().getTipo().equals("coordinador")) {
            try {
                Coordinador cordiEliminar = tablaCoordinadores.getSelectionModel().getSelectedItem();
                if (coordinador.eliminarCoordinador(cordiEliminar.getNoPersonal())) {
                    labelMensaje.setText("Eliminacion exitosa");
                    labelMensaje.setVisible(true);
                    llenarTabla("Coordinador");
                    textContrasena.setText("");
                    textCorreo.setText("");
                    textNombre.setText("");
                    textNumPersonal.setText("");
                }
            } catch (Exception ex) {
                Logger.getLogger(IUAdministrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void habilitarCampos2() {
        labelTipoUsuario.setVisible(true);
        comboTipoUsuario.setVisible(true);

        tablaAsesores.setDisable(true);
        tablaRecepcionistas.setDisable(true);

        textContrasena.setEditable(true);
        textContrasena.setStyle("-jfx-unfocus-color: #26C300");
        textCorreo.setEditable(true);
        textCorreo.setStyle("-jfx-unfocus-color: #26C300");
        textNombre.setEditable(true);
        textNombre.setStyle("-jfx-unfocus-color: #26C300");
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
            stagePrincipal.setResizable(false);
            stagePrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(IUAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

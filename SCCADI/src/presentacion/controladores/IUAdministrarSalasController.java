/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.daoimpl.DAOSalaImpl;
import logica.dominio.Sala;
import utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IUAdministrarSalasController implements Initializable {

    @FXML
    private JFXTextField textCupo;
    @FXML
    private TableView<Sala> tablaSalas;
    @FXML
    private TableColumn<Sala, String> columnaSala;
    @FXML
    private TableColumn<Sala, String> columnaCupo;
    @FXML
    private TextField textBuscar;
    @FXML
    private JFXTextField textSala;
    @FXML
    private JFXButton botonAgregar;
    @FXML
    private JFXButton botonEliminar;
    @FXML
    private JFXButton botonEditar;
    @FXML
    private Label labelMensaje;

    ObservableList<Sala> listaSalas;
    DAOSalaImpl sala = new DAOSalaImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTabla();
        textBuscar.setOnKeyReleased(event -> {
            try {
                if (textBuscar.getText().trim().isEmpty()) {
                    tablaSalas.setItems(listaSalas);
                } else {
                    tablaSalas.setItems(filtrarSalas(textBuscar.getText()));
                    tablaSalas.getSelectionModel().selectFirst();

                }
            } catch (Exception e) {

            }
        });
    }

    private ObservableList<Sala> filtrarSalas(String criterio) {
        int size = listaSalas.size();
        ObservableList<Sala> filtrada = FXCollections.observableArrayList();
        for (int i = 0; i < listaSalas.size(); i++) {
            if (listaSalas.get(i).getNombre().trim().contains(criterio.trim())) {
                filtrada.add(listaSalas.get(i));
            }
        }
        return filtrada;
    }

    public void llenarTabla() {
        try {
            listaSalas = FXCollections.observableArrayList(sala.obtenerSalas());
            columnaSala.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaCupo.setCellValueFactory(new PropertyValueFactory<>("cupo"));
            tablaSalas.setItems(listaSalas);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible");
        }

    }

    @FXML
    public void mostrarSala() {
        Sala salaSeleccionada = tablaSalas.getSelectionModel().getSelectedItem();
        textCupo.setText(Integer.toString(salaSeleccionada.getCupo()));
        textSala.setText(salaSeleccionada.getNombre());
    }

    @FXML
    void agregarSala(ActionEvent event) {
        if (botonAgregar.getText().equals("Agregar")) {
            botonAgregar.setText("Guardar");
            botonEditar.setDisable(true);
            botonEliminar.setDisable(true);
            tablaSalas.setDisable(true);
            textCupo.setEditable(true);
            textSala.setEditable(true);

            textCupo.clear();
            textSala.clear();
        } else {
            if (camposVacios()) {
                labelMensaje.setText("Campos vacios");
                labelMensaje.setVisible(true);
            } else {
                try {
                    if (sala.insertarSala(obtenerDatos())) {
                        labelMensaje.setText("¡Registro exitoso!");
                        labelMensaje.setVisible(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(IUAdministrarSalasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            botonAgregar.setText("Agregar");
            botonEditar.setDisable(false);
            botonEliminar.setDisable(false);
            tablaSalas.setDisable(false);
            textCupo.setEditable(false);
            textSala.setEditable(false);
            labelMensaje.setVisible(false);
            textCupo.clear();
            textSala.clear();

        }
    }

    @FXML
    void editarSala(ActionEvent event) {
        if (botonEditar.getText().equals("Editar")) {
            botonEditar.setText("Guardar");
            botonAgregar.setDisable(true);
            botonEliminar.setDisable(true);
            tablaSalas.setDisable(true);
            textCupo.setEditable(true);
            textSala.setEditable(true);

        } else {
            if (camposVacios()) {
                labelMensaje.setText("Campos vacios");
                labelMensaje.setVisible(true);
            } else {
                Sala salaNueva = new Sala();
                salaNueva.setNombre(textSala.getText());
                salaNueva.setCupo(Integer.parseInt(textCupo.getText()));
                salaNueva.setIdSala(tablaSalas.getSelectionModel().getSelectedItem().getIdSala());
                try {
                    if (sala.actualizarSala(salaNueva)) {
                        System.out.println("entro a actualizar");
                        System.out.println(obtenerDatos().toString());
                        labelMensaje.setText("¡Actualización exitosa!");
                        labelMensaje.setVisible(true);

                        llenarTabla();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(IUAdministrarSalasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            botonEditar.setText("Editar");
            botonAgregar.setDisable(false);
            botonEliminar.setDisable(false);
            tablaSalas.setDisable(false);
            textCupo.setEditable(false);
            textSala.setEditable(false);
            labelMensaje.setVisible(false);
            textCupo.clear();
            textSala.clear();

        }

    }

    @FXML
    void eliminarSala(ActionEvent event) {
        try {
            sala.eliminarSala(tablaSalas.getSelectionModel().getSelectedItem().getIdSala());
            llenarTabla();
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Sala asignada", "No se puede eliminar una sala que ya tenga una actividad"
                    + " asignada");
        }

    }

    public Sala obtenerDatos() {
        Sala nuevaSala = new Sala();

        nuevaSala.setNombre(textSala.getText());
        nuevaSala.setCupo(Integer.parseInt(textCupo.getText()));

        return nuevaSala;
    }

    public boolean camposVacios() {
        if (textCupo.getText().equals("") || textSala.getText().equals("")) {
            return true;
        }
        return false;
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

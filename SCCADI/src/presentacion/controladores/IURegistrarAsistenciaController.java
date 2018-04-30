/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.dao.DAOReservacion;
import logica.daoimpl.DAOReservacionImpl;
import logica.dominio.Alumno;
import org.controlsfx.control.CheckListView;
import utilerias.Mensajes;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class IURegistrarAsistenciaController implements Initializable {

    @FXML
    private ImageView botonRegresar;
    @FXML
    private Label labelActividad;
    @FXML
    private Label labelFecha;
    @FXML
    private JFXButton botonImprimir;
    @FXML
    private JFXButton botonRegistrar;
    @FXML
    private CheckListView<Alumno> listaAlumnos;
    @FXML
    private Label labelExito;

    ObservableList<Alumno> listaAlumno = null;
    DAOReservacionImpl reservacion = new DAOReservacionImpl();
    Date hoy = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = dateFormat.format(hoy);
    String nombreActividad = "Conversación 05";
    String nombreAsesor = "Esmeralda";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarLista();
        labelFecha.setText(fecha);
        labelActividad.setText(nombreActividad);

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
            Logger.getLogger(IURegistrarAsistenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarLista() {
        try {
            listaAlumno = FXCollections.observableList(reservacion.obtenerAlumnosReservacion(14));
            listaAlumnos.setItems(listaAlumno);
        } catch (Exception ex) {
            Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible, intente más tarde");

        }
    }

    @FXML
    public void guardarAsistencia() {
        for (int i = 0; i < listaAlumno.size(); i++) {
            if (listaAlumnos.getCheckModel().isChecked(i)) {
                try {
                    reservacion.actualizarEstatusReservacion(true, listaAlumnos.getCheckModel().getItem(i).getMatricula());
                } catch (Exception ex) {
                    Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible, intente más tarde");
                }

            } else {
                try {
                    reservacion.actualizarEstatusReservacion(false, listaAlumnos.getCheckModel().getItem(i).getMatricula());
                } catch (Exception ex) {
                    Mensajes.displayWarningAlert("Error conexion", "Servicio no disponible, intente más tarde");
                }
            }
        }

        listaAlumnos.getCheckModel().clearChecks();
        labelExito.setText("¡Asistencia registrada :)!");
        labelExito.setVisible(true);

    }

    @FXML
    public void generarPDF(){
        FileOutputStream archivo = null;
        try {
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            String fecha = formateador.format(ahora);
            ObservableList<Alumno> data = listaAlumno.sorted();
            String nombreArchivo = nombreActividad +"-"+nombreAsesor;
            archivo = new FileOutputStream("C:\\Users\\Esmeralda\\Desktop\\" + nombreArchivo + ".pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.add(new Paragraph("------------------------------------------ CENTRO DE AUTOACCESO ---------------------------------------------"));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Actividad: " + nombreActividad));
            documento.add(new Paragraph("Fecha:" + fecha));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("LISTA DE ALUMNOS"));
            documento.add(new Paragraph("\n"));
            for (int i = 0; i < data.size(); i++) {
                documento.add(new Paragraph("[   ] " + data.get(i).toString()));
                documento.add(new Paragraph("\n"));
            }   documento.close();
            labelExito.setText("¡Lista guardada!");
            labelExito.setVisible(true);
        } catch (FileNotFoundException ex) {
            Mensajes.displayWarningAlert("Error conexion", "No se puede guardar la lista porque otro programa la esta utilizando");
        } catch (DocumentException ex) {
            
        } finally {
            try {
                archivo.close();
            } catch (Exception ex) {
                
            }
        }
    }

}

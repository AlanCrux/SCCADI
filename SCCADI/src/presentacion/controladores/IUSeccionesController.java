package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.daoimpl.DAOSeccionImpl;
import logica.dominio.Asesor;
import logica.dominio.ExperienciaEducativa;
import logica.dominio.Seccion;
import utilerias.Herramientas;

/**
 * Controlador de la ventana IUSecciones
 * Nos permite visualizar las secciones asociadas a un asesor.
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class IUSeccionesController implements Initializable {
  @FXML
  private ImageView botonAtras;
  @FXML
  private Label lbNombreAsesor;
  @FXML
  private Label lbPeriodo;
  @FXML
  private FlowPane mpSecciones;
  
  private Asesor asesor;
  private String periodo;
  private final String COLOR_AVANZADO = "#FFB7E7";
  private final String COLOR_INTERMEDIO = "#F7DC6F"; 
  private final String COLOR_BASICO = "#76D7C4"; 
  
  /**
   * Inicializa los componentes de la ventana.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    periodo = "Periodo: Feb - Jul 2018";
    lbNombreAsesor.setText(asesor.getNombre());
    lbPeriodo.setText(periodo);
    obtenerSecciones(mpSecciones, asesor.getNoPersonal());
  }

  /**
   * Llena un flowPane con lase secciones asociadas a un asesor.
   * @param mpSecciones FlowPane a llenar
   * @param noPersonal identificador del asesor. 
   * @return número de secciones obtenidas.
   */
  public int obtenerSecciones(FlowPane mpSecciones, int noPersonal) {
    List<Seccion> secciones = new ArrayList();
    DAOSeccionImpl daoSeccion = new DAOSeccionImpl();
    int cantidadSecciones;

    try {
      secciones = daoSeccion.obtenerSecciones(noPersonal);
    } catch (Exception ex) {
      Herramientas.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }

    cantidadSecciones = secciones.size();

    for (int i = 0; i < cantidadSecciones; i++) {
      mpSecciones.getChildren().add(obtenerExperiencia(secciones.get(i).getNrc(), secciones.get(i).getIdExperiencia()));
    }
    return cantidadSecciones;
  }

  /**
   * Obtiene la experiencia educativa asociada a un id y la devuelve en un contenedor tipo FichaSeccion.
   * @param nrc identificador de la seccion.
   * @param idExperiencia identificador de la experiencia educativa.
   * @return contenedor con los datos de la experiencia educativa y la sección.
   */
  public FichaSeccion obtenerExperiencia(int nrc, int idExperiencia) {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    ExperienciaEducativa experiencia = new ExperienciaEducativa();

    try {
      experiencia = daoExperiencia.obtenerExperiencia(idExperiencia);
    } catch (Exception ex) {
      Herramientas.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }

    String nombreExperiencia = experiencia.getNombre();
    String nivel = experiencia.getNivel();
    FichaSeccion fichaSeccion = new FichaSeccion(nrc, nombreExperiencia, nivel, periodo);
    
    fichaSeccion.setPrefSize(160, 70);
    fichaSeccion.setAsesor(asesor);
   
    if (nivel.contains("Avanzado")) {
      fichaSeccion.setBackgroundColor(COLOR_AVANZADO);
    } else if (nivel.contains("Intermedio")) {
      fichaSeccion.setBackgroundColor(COLOR_INTERMEDIO);
    } else {
     fichaSeccion.setBackgroundColor(COLOR_BASICO);
    }

    return fichaSeccion;
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
  
  /**
   * Cierra la ventana. 
   * @param event evento que activa el método
   */
  @FXML
  public void salir(MouseEvent event) {
    Stage mainStage = (Stage) botonAtras.getScene().getWindow();
    mainStage.close();
  }


  public Asesor getAsesor() {
    return asesor;
  }

  public void setAsesor(Asesor asesor) {
    this.asesor = asesor;
  }

  public String getPeriodo() {
    return periodo;
  }

  public void setPeriodo(String periodo) {
    this.periodo = periodo;
  }
  
  /**
   * Muestra la ventana asociada a un loader. 
   * @param loader FXMLLloader del cual se quiere mostrar la ventana.
   */
  public void mostrarVentana(FXMLLoader loader){
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

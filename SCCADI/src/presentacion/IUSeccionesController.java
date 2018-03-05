package presentacion;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author alancrux
 */
public class IUSeccionesController implements Initializable {
  //Componentes de la IU
  @FXML
  private ImageView botonAtras;
  @FXML
  private Label lbNombreAsesor;
  @FXML
  private Label lbPeriodo;
  @FXML
  private FlowPane mpSecciones;
  
  //Atributos globales
  private Asesor asesor;
  private String periodo;
  private final String COLOR_AVANZADO = "#FFB7E7";
  private final String COLOR_INTERMEDIO = "#F7DC6F"; 
  private final String COLOR_BASICO = "#76D7C4"; 
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Inicializar datos de la ventana
    
    //Prueba ------------------------------------------------------------------------
    //asesor = new Asesor(1234, "José Antonio", "Martinez", "Salazar", "pepedD@gmail.com");
 
    //--------------------------------------------------------------------------------
    periodo = "Periodo: Feb - Jul 2018";
    lbNombreAsesor.setText(asesor.getNombre() +" "+ asesor.getApPaterno() +" "+ asesor.getApMaterno());
    lbPeriodo.setText(periodo);
    
    obtenerSecciones(mpSecciones, asesor.getNoPersonal());
  }

  public int obtenerSecciones(FlowPane mpSecciones, int noPersonal) {
    List<Seccion> secciones = new ArrayList();
    DAOSeccionImpl daoSeccion = new DAOSeccionImpl();
    int cantidadSecciones;

    try {
      secciones = daoSeccion.obtenerSecciones(noPersonal);
    } catch (SQLException ex) {
      System.out.println("Se perdio la conexión con la BD");
    }

    cantidadSecciones = secciones.size();

    for (int i = 0; i < cantidadSecciones; i++) {
      mpSecciones.getChildren().add(obtenerExperiencia(secciones.get(i).getNrc(), secciones.get(i).getIdExperiencia()));
    }
    return cantidadSecciones;
  }

  public FichaSeccion obtenerExperiencia(int nrc, int idExperiencia) {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    ExperienciaEducativa experiencia = new ExperienciaEducativa();

    try {
      experiencia = daoExperiencia.obtenerExperiencia(idExperiencia);
    } catch (SQLException ex) {
      System.out.println("Se perdio la conexión con la BD");
    }

    String nombreExperiencia = experiencia.getNombre();
    String nivel = experiencia.getNivel();
    FichaSeccion fichaSeccion = new FichaSeccion(nrc, nombreExperiencia, nivel);
    
    fichaSeccion.setPrefSize(120, 70);
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
  
  public void mostrarVentana(FXMLLoader loader){
    try {
      Stage stagePrincipal = new Stage();
      Parent root = (Parent) loader.load();
      Scene scene = new Scene(root);
      stagePrincipal.setScene(scene);
      stagePrincipal.show();
    } catch (IOException ex) {
      Logger.getLogger(IUAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}

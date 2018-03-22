package presentacion;

import presentacion.controladores.IUAlumnosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logica.dominio.Asesor;

/**
 * Esta clase nos permite asociar los datos de una sección a un contenedor tipo Button. 
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class FichaSeccion extends Button {

  private String datosSeccion;
  private String BACKGROUND_COLOR;
  private Asesor asesor;

  /**
   * Construye el objeto. 
   * @param nrc nrc de la sección.
   * @param experiencia nombre de la experiencia educativa de la sección.
   * @param nivel nivel de la experiencia educativa de la sección. 
   */
  public FichaSeccion(int nrc, String experiencia, String nivel, String periodo) {
    datosSeccion = "NRC: " + nrc + "\n";
    datosSeccion += "EE: " + experiencia + "\n";
    datosSeccion += "Nivel: " + nivel;

    setText(datosSeccion);
    setCursor(Cursor.HAND);
    this.setOnMouseEntered(event -> {
      efectoMouseSobre();
    });

    this.setOnMouseExited(event -> {
      efectoMouseFuera();
    });

    this.setOnAction(event -> {
      mostrarVentanaAlumnos(nrc, experiencia,periodo);
    });
  }

  /**
   * Aplica un cambio de color al objeto.
   */
  public void efectoMouseSobre() {
    setText("Ver alumnos");
    setStyle("-fx-background-color: #85C1E9");
  }

  /**
   * Aplica un cambio de color al objeto.
   */
  public void efectoMouseFuera() {
    setText(datosSeccion);
    setStyle("-fx-background-color: " + BACKGROUND_COLOR);
  }

  /**
   * Muestra la ventana IUAlumnos. 
   * @param nrc el nrc de la sección.
   * @param experiencia el nombre de la experiencia educativa de la sección. 
   */
  public void mostrarVentanaAlumnos(int nrc, String experiencia, String periodo) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("IUAlumnos.fxml"));
    IUAlumnosController controller = new IUAlumnosController();
    loader.setController(controller);

    controller.setNrc(nrc);
    controller.setExperiencia(experiencia);
    controller.setAsesor(asesor);
    controller.setPeriodo(periodo);
    controller.mostrarVentana(loader);
    
    Stage mainStage = (Stage) this.getScene().getWindow();
    mainStage.close();
  }

  public String getBackgroundColor() {
    return BACKGROUND_COLOR;
  }

  public void setBackgroundColor(String backgroundColor) {
    setStyle("-fx-background-color: " + backgroundColor);
    this.BACKGROUND_COLOR = backgroundColor;
  }

  public Asesor getAsesor() {
    return asesor;
  }

  public void setAsesor(Asesor asesor) {
    this.asesor = asesor;
  }
  
}

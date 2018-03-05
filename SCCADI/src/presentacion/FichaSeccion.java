package presentacion;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logica.dominio.Asesor;

/**
 *
 * @author alancrux
 */
public class FichaSeccion extends Button{
  private String datosSeccion;
  private String BACKGROUND_COLOR;
  private Asesor asesor; 

  public FichaSeccion(int nrc, String experiencia, String nivel) {
    datosSeccion = "NRC: "+ nrc + "\n";
    datosSeccion += "EE: "+ experiencia + "\n";
    datosSeccion += "Nivel: "+ nivel;
    
    setText(datosSeccion);
    setCursor(Cursor.HAND);
    this.setOnMouseEntered(event->{
      efectoMouseSobre();
    });
    
    this.setOnMouseExited(event->{
      efectoMouseFuera();
    });
    
    this.setOnAction(event->{
      mostrarVentanaAlumnos(nrc,experiencia);
    });
  }
  
  public void efectoMouseSobre(){
    setText("Ver alumnos");
    setStyle("-fx-background-color: #85C1E9");
  }
  
  public void efectoMouseFuera(){
    setText(datosSeccion);
    setStyle("-fx-background-color: "+BACKGROUND_COLOR);
  }
  
  public void mostrarVentanaAlumnos(int nrc, String experiencia){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("IUAlumnos.fxml"));
	IUAlumnosController controller = new IUAlumnosController();
	loader.setController(controller);
    
    controller.setNrc(nrc);
    controller.setExperiencia(experiencia);
    controller.mostrarVentana(loader);
    controller.setAsesor(asesor);
    
    Stage mainStage = (Stage) this.getScene().getWindow();
    mainStage.close();
  }

  public String getBackgroundColor() {
    return BACKGROUND_COLOR;
  }

  public void setBackgroundColor(String backgroundColor) {
    setStyle("-fx-background-color: "+backgroundColor);
    this.BACKGROUND_COLOR = backgroundColor;
  }

  public Asesor getAsesor() {
    return asesor;
  }

  public void setAsesor(Asesor asesor) {
    this.asesor = asesor;
  }  
}

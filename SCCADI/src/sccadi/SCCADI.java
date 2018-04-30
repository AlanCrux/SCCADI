package sccadi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentacion.controladores.IUAsignarActividadesSemanalesController;
import presentacion.controladores.IUConsultarAlumnosController;
import presentacion.controladores.IULoginController;
import presentacion.controladores.IURegistrarAsistenciaController;

/**
 *
 * @author alancrux
 */
public class SCCADI extends Application {

  private Stage stagePrincipal;

  /**
   * Inicializa el programa.
   *
   * @param stagePrincipal el stage sobre el cual cargara la escena.
   */
  @Override
  public void start(Stage stagePrincipal) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUConsultarAlumnos.fxml"));
     IUConsultarAlumnosController controller = new IUConsultarAlumnosController();
    loader.setController(controller);
    controller.mostrarVentana(loader);
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}

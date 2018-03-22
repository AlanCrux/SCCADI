package sccadi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.dominio.Asesor;
import presentacion.IUSeccionesController;

/**
 *
 * @author alancrux
 */
public class SCCADI extends Application {
  private Stage stagePrincipal;

  /**
   * Inicializa el programa.
   * @param stagePrincipal el stage sobre el cual cargara la escena. 
   */
  @Override
  public void start(Stage stagePrincipal) {
    try {
            AnchorPane page = FXMLLoader.load(getClass().getResource("/presentacion/IUAdministrarAlumnos.fxml"));
            Scene scene = new Scene(page);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
            stagePrincipal.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(SCCADI.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Muestra la ventana principal.
   */
  public void showMainWindows() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUAdministrarAlumnos.fxml"));
    IUSeccionesController seccionesController = new IUSeccionesController();
    loader.setController(seccionesController);
    
    Asesor asesor = new Asesor();
    asesor.setNoPersonal(1234);
    asesor.setNombre("Jose Antonio Martinez Salazar");
    
    seccionesController.setAsesor(asesor);
    
    seccionesController.mostrarVentana(loader);
    
  }

}

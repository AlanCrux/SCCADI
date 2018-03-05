package sccadi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import logica.dominio.Asesor;
import presentacion.IUSeccionesController;

/**
 *
 * @author alancrux
 */
public class SCCADI extends Application {
  private Stage stagePrincipal;

  @Override
  public void start(Stage stagePrincipal) {
    this.stagePrincipal = stagePrincipal;
    showMainWindows();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  public void showMainWindows() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUSecciones.fxml"));
    IUSeccionesController seccionesController = new IUSeccionesController();
    loader.setController(seccionesController);
    
    Asesor asesor = new Asesor();
    asesor.setNoPersonal(1234);
    asesor.setNombre("Jose Antonio");
    asesor.setApPaterno("Martinez");
    asesor.setApMaterno("Salazar");
    
    seccionesController.setAsesor(asesor);
    
    seccionesController.mostrarVentana(loader);
    
  }

}

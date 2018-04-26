package sccadi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import presentacion.IURegistroProgresoController;
import presentacion.controladores.IUAdministrarAvisoController;
import presentacion.controladores.IUConsultarAvisosController;
import presentacion.controladores.IULoginController;
import presentacion.controladores.IUReservacionActividadesController;

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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUConsultarAvisos.fxml"));
    IUConsultarAvisosController controller = new IUConsultarAvisosController();
    loader.setController(controller);
    controller.mostrarVentana(loader);
    
    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUAdministrarAviso.fxml"));
    IUAdministrarAvisoController controller = new IUAdministrarAvisoController();
    loader.setController(controller);
    controller.mostrarVentana(loader);*/
    
    
    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IULogin.fxml"));
    IULoginController controller = new IULoginController();
    loader.setController(controller);
    controller.mostrarVentana(loader);*/
    
    
    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IURegistroProgreso.fxml"));
    IURegistroProgresoController controller = new IURegistroProgresoController();
    loader.setController(controller);
    controller.mostrarVentana(loader);*/
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}

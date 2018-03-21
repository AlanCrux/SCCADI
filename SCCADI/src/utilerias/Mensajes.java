package utilerias;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
/**
 * Provee métodos para la creación de alertas. 
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class Herramientas {

  private static final String BUTTONACEPT = "buttonAccept";

  private Herramientas() {

  }

  /**
   * Muestra una alerta de tipo advertencia
   *
   * @param titulo
   * @param mensaje
   */
  public static void displayWarningAlert(String titulo, String mensaje) {

    ButtonType btAccept = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);

    Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, btAccept);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    
    alert.showAndWait();
  }

}

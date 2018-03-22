package utilerias;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Provee métodos para la creación de alertas. 
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class Mensajes {

  private static final String BUTTONACEPT = "buttonAccept";

  private Mensajes() {

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
  
  public static void displayInformation(String title, String message) {
    Stage primaryStage = new Stage();
    StackPane stackPane = new StackPane();
    stackPane.setStyle("-fx-background-color: #0F1F38;");
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text(title));
    content.setBody(new Text(message));

    JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

    JFXButton button = new JFXButton("Okay");
    button.setOnAction((ActionEvent event) -> {
      dialog.close();
      primaryStage.close();
    });
    content.setActions(button);

    Scene scene = new Scene(stackPane);

    primaryStage.setScene(scene);
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    dialog.show();
    primaryStage.showAndWait();
  }
  
  public static boolean displayWarningAlertWithChoice(String title, String message) {
	boolean choice = false;

	ButtonType btAccept = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
	ButtonType btCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

	Alert alert = new Alert(Alert.AlertType.WARNING, message, btAccept, btCancel);
	alert.setTitle(title);
	alert.setHeaderText(null);

	Optional<ButtonType> result = alert.showAndWait();
	if (result.isPresent() && result.get() == btAccept) {
	  choice = true;
	}
	return choice;
  }

}

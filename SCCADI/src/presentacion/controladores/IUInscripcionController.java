package presentacion.controladores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOAlumnoImpl;
import logica.daoimpl.DAOInscripcionImpl;
import logica.daoimpl.DAOSeccionImpl;
import logica.dominio.Alumno;
import logica.dominio.Coordinador;
import logica.dominio.Inscripcion;
import logica.dominio.Recepcionista;
import logica.dominio.Seccion;
import utilerias.Mensajes;

/**
 * Controlador de la ventana IUInscripcion
 * Nos permite inscribir a un alumno. 
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class IUInscripcionController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private Label lbNombreAsesor;
  @FXML
  private Label lbNombreAsesor1;
  @FXML
  private Label lbNombreAsesor11;
  @FXML
  private TextField tfMatricula;
  @FXML
  private TextField tfNombre;
  @FXML
  private TextField tfCorreo;
  @FXML
  private TextField tfProgramaEducativo;
  @FXML
  private TextField tfNombreEmergencia;
  @FXML
  private TextField tfTelefonoEmergencia;
  @FXML
  private Button btnFoto;
  @FXML
  private Button btnInscribir;
  @FXML
  private TableView<Seccion> tablaExperiencias;
  @FXML
  private TableColumn<Seccion, String> tbcNRC;
  @FXML
  private TableColumn<Seccion, String> tbcExperiencia;
  @FXML
  private TableColumn<Seccion, String> tbcNivel;
  @FXML
  private TableColumn<Seccion, String> tbcCupo;
  @FXML
  private TableColumn<Seccion, String> tbcAsesor;
  @FXML
  private Label lbNombreAsesor111;
  @FXML
  private ImageView imageViewFoto;
  private boolean origen; 
  private final ObservableList<Seccion> secciones = FXCollections.observableArrayList();
  private Recepcionista recepcionista; 
  private Coordinador coordinador;
  /**
   * Inicializa los componentes de la ventana
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    obtenerSecciones();
    tbcNRC.setCellValueFactory(new PropertyValueFactory<>("nrc"));
    tbcExperiencia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
    tbcNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
    tbcCupo.setCellValueFactory(new PropertyValueFactory<>("cupo"));
    tbcAsesor.setCellValueFactory(new PropertyValueFactory<>("asesor"));

    tablaExperiencias.setItems(secciones);
    tablaExperiencias.getSelectionModel().selectFirst();
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz. 
   * @param event 
   */
  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow_transparent.png"));
  }

  /**
   * Da un efecto gráfico a un componente de la interfaz. 
   * @param event 
   */
  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    ficha.setImage(new Image("/recursos/iconos/back_arrow.png"));
  }

  @FXML
  private void salir(MouseEvent event) {
    if (origen) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUMenuRecepcionista.fxml"));
      IUMenuRecepcionistaController controller = new IUMenuRecepcionistaController();
      loader.setController(controller);
      controller.setRecepcionista(recepcionista);
      controller.mostrarVentana(loader);
      Stage mainStage = (Stage) botonAtras.getScene().getWindow();
      mainStage.close();
    } else{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUAdministrarAlumnos.fxml"));
      IUAdministrarAlumnosController controller = new IUAdministrarAlumnosController();
      loader.setController(controller);
      controller.setCoordinador(coordinador);
      controller.mostrarVentana(loader);
      Stage mainStage = (Stage) botonAtras.getScene().getWindow();
      mainStage.close();
    }
    
  }

  /**
   * Invoca a un método de capa lógica para recuperar las secciones registradas en la BD.
   * @return ObservableList cargada de las secciones recuperadas.
   */
  public ObservableList<Seccion> obtenerSecciones() {
    ObservableList<Seccion> listaSecciones = null;
    DAOSeccionImpl daoSeccion = new DAOSeccionImpl();

    try {
      listaSecciones = FXCollections.observableList(daoSeccion.obtenerSecciones());
    } catch (Exception ex) {
      Mensajes.displayWarningAlert("Error conexion", "No se pudo obtener la información");
    }

    for (int i = 0; i < listaSecciones.size(); i++) {
      if (listaSecciones.get(i).getCupo() > 0) {
        secciones.add(listaSecciones.get(i));
      }
    }
    return secciones;
  }

  /**
   * Toma los datos de los componentes de interfaz para generar una nueva inscripción.
   * @return true si la inscripción se realizo con éxito. 
   */
  public boolean inscribirAlumno() {
    DAOAlumnoImpl daoAlumno = new DAOAlumnoImpl();
    DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
    DAOSeccionImpl daoSeccion = new DAOSeccionImpl();

    Alumno alumno = new Alumno();
    alumno.setMatricula(tfMatricula.getText());
    alumno.setNombre(tfNombre.getText());
    alumno.setCorreo(tfCorreo.getText());
    alumno.setProgramaEducativo(tfProgramaEducativo.getText());
    alumno.setContactoEmergencia(tfNombreEmergencia.getText());
    alumno.setNumeroEmergencia(tfTelefonoEmergencia.getText());
    alumno.setFotografia(imageToBlob(imageViewFoto));

    Inscripcion inscripcion = new Inscripcion();
    inscripcion.setMatricula(alumno.getMatricula());
    inscripcion.setNrc(tablaExperiencias.getSelectionModel().getSelectedItem().getNrc());

    Seccion seccion = new Seccion();
    seccion.setNrc(tablaExperiencias.getSelectionModel().getSelectedItem().getNrc());
    seccion.setCupo(tablaExperiencias.getSelectionModel().getSelectedItem().getCupo());

    try {
      daoAlumno.insertarAlumno(alumno);
      daoInscripcion.insertarInscripcion(inscripcion);
      daoSeccion.actualizarSeccion(seccion);
      return true;
    } catch (SQLException ex) {
      Mensajes.displayInformation("Inscripción fallida", "Ya existe el alumno");
    } catch (Exception ex) {
      Mensajes.displayInformation("Inscripción fallida", "No se ha guardado");
    }
    return false;
  }

  /**
   * Define el comportamiento del sistema cuando se da clic en el botón foto. 
   * @param event 
   */
  @FXML
  public void onActionFoto(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");
    File selectedFile = fileChooser.showOpenDialog(imageViewFoto.getScene().getWindow());
    if (selectedFile != null) {
      Image image = new Image(selectedFile.toURI().toString());
      imageViewFoto.setImage(image);
    }

  }

  /**
   * Define el comportamiento del sistema cuando se da clic en el botón inscribir.
   * @param event 
   */
  @FXML
  public void onActionInscribir(ActionEvent event) {
    if (!camposVacios()) {
      if (inscribirAlumno()) {
        Mensajes.displayInformation("Éxito", "Inscripción exitosa");
        limpiar();
      }
    } else {
      Mensajes.displayInformation("Campos vacios", "Se requieren todos los campos");
    }
  }

  /**
   * Verifica si alguno de los componentes de interfaz requeridos esta vacio. 
   * @return true si algún componente esta vacio. 
   */
  private boolean camposVacios() {
    if (tfMatricula.getText().trim().isEmpty()) {
      return true;
    }

    if (tfNombre.getText().trim().isEmpty()) {
      return true;
    }

    if (tfCorreo.getText().trim().isEmpty()) {
      return true;
    }

    if (tfProgramaEducativo.getText().trim().isEmpty()) {
      return true;
    }

    if (tfNombreEmergencia.getText().trim().isEmpty()) {
      return true;
    }

    if (tfTelefonoEmergencia.getText().trim().isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * Convierte la imagen de un ImageView a un objeto de tipo Blob. 
   * @param logo ImageView que contiene la imagen a convertir. 
   * @return Blob de la imagen. 
   */
  private Blob imageToBlob(ImageView logo) {
    Blob blFile = null;
    try {
      BufferedImage bImage = SwingFXUtils.fromFXImage(logo.getImage(), null);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(bImage, "png", baos);
      blFile = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
    } catch (Exception ex) {
      Logger.getLogger(IUInscripcionController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return blFile;
  }

  /**
   * Limpia los componentes de interfaz. 
   */
  private void limpiar() {
    tfMatricula.clear();
    tfNombre.clear();
    tfCorreo.clear();
    tfProgramaEducativo.clear();
    tfNombreEmergencia.clear();
    tfTelefonoEmergencia.clear();
    imageViewFoto.setImage(new Image("/recursos/imagenes/IMG_ALUMNO.jpg"));
    tablaExperiencias.getItems().clear();
    obtenerSecciones();
    tablaExperiencias.setItems(secciones);
  }

  public Recepcionista getRecepcionista() {
    return recepcionista;
  }

  public void setRecepcionista(Recepcionista recepcionista) {
    this.recepcionista = recepcionista;
  }

  public Coordinador getCoordinador() {
    return coordinador;
  }

  public void setCoordinador(Coordinador coordinador) {
    this.coordinador = coordinador;
  }
  
  
  /**
   * Muestra la ventana.
   *
   * @param loader el loader con la ruta de la ventana que se quiere cargar.
   */
  public void mostrarVentana(FXMLLoader loader) {
    try {
      Stage stagePrincipal = new Stage();
      Parent root = (Parent) loader.load();
      Scene scene = new Scene(root);
      stagePrincipal.setScene(scene);
      stagePrincipal.show();
    } catch (IOException ex) {
      Logger.getLogger(IUMenuRecepcionistaController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void setOrigen(boolean origen) {
    this.origen = origen;
  }
  
  
}

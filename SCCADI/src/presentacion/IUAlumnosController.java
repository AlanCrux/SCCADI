package presentacion;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;
import logica.dominio.Asesor;

/**
 * FXML Controller class
 *
 * @author alancrux
 */
public class IUAlumnosController implements Initializable {

  @FXML
  private ImageView botonAtras;
  @FXML
  private Label lbExperiencia;
  @FXML
  private Label lbNrc;
  @FXML
  private Label lbNumAlumnos;
  @FXML
  private Label lbPeriodo;
  @FXML
  private FlowPane mpAlumnos;

  private Asesor asesor;
  private String experiencia;
  private int nrc;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lbExperiencia.setText("EE: " + experiencia);
    lbNrc.setText("NRC: " + nrc);
    lbNumAlumnos.setText("Alumnos: " + cargarAlumnos(nrc));
    
    botonAtras.setOnMouseClicked(event->{
      regresar();
    });
  }

  public void mostrarVentana(FXMLLoader loader) {
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

  public int cargarAlumnos(int nrc) {
    List<Alumno> alumnos = new ArrayList();
    DAOInscripcionImpl daoInscripcion = new DAOInscripcionImpl();
    try {
      alumnos = daoInscripcion.obtenerAlumnos(nrc);
    } catch (SQLException ex) {
      System.out.println("Se perdió la conexión con la base de datos");
    }
    int cantidadAlumnos = alumnos.size();

    for (int i = 0; i < cantidadAlumnos; i++) {
      mpAlumnos.getChildren().add(cargarFichaAlumno(alumnos.get(i)));
    }

    return cantidadAlumnos;
  }

  public Pane cargarFichaAlumno(Alumno alumno) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("IUFichaAlumno.fxml"));
    IUFichaAlumnoController fichaController = new IUFichaAlumnoController();

    loader.setController(fichaController);
    Pane fichaAlumno = new Pane();

    try {
      fichaAlumno = (Pane) loader.load();
    } catch (IOException ex) {
      Logger.getLogger(IUAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    fichaController.setIvFotografia(blobToImage(alumno.getFotografia()));
    fichaController.setLbMatricula(alumno.getMatricula());
    fichaController.setLbNombre(alumno.getNombre());
    fichaController.setLbCorreo(alumno.getCorreo());

    return fichaAlumno;
  }

  public Image blobToImage(Blob blobImage) {
    InputStream in;
    BufferedImage bfImage = null;
    try {
      in = blobImage.getBinaryStream();
      bfImage = ImageIO.read(in);
    } catch (SQLException | IOException ex) {
      System.err.println("Error al procesar la imagen");
    }
    Image image = SwingFXUtils.toFXImage(bfImage, null);
    return image;
  }

  public void regresar() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("IUSecciones.fxml"));
    IUSeccionesController controller = new IUSeccionesController();
    loader.setController(controller);
    controller.setAsesor(asesor);
    controller.mostrarVentana(loader);
    Stage mainStage = (Stage) botonAtras.getScene().getWindow();
    mainStage.close();
  }

  public String getExperiencia() {
    return experiencia;
  }

  public void setExperiencia(String experiencia) {
    this.experiencia = experiencia;
  }

  public int getNrc() {
    return nrc;
  }

  public void setNrc(int nrc) {
    this.nrc = nrc;
  }

  public Asesor getAsesor() {
    return asesor;
  }

  public void setAsesor(Asesor asesor) {
    this.asesor = asesor;
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
}

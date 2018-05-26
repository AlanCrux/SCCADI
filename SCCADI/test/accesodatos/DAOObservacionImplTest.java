package accesodatos;

import java.util.ArrayList;
import java.util.List;
import logica.daoimpl.DAOObservacionImpl;
import logica.dominio.Observacion;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alanc
 */
public class DAOObservacionImplTest {
  
  public DAOObservacionImplTest() {
  }

  /**
   * Verifica que se obtengan de la base de datos las observaciones para un alumno en especifico.
   */
  @Test
  public void testObtenerObservaciones() throws Exception {
    System.out.println("obtenerObservaciones");
    int folioInscripcion = 29;
    DAOObservacionImpl instance = new DAOObservacionImpl();
    int sizeResult = 3; 
    List<Observacion> result = instance.obtenerObservaciones(folioInscripcion);
    assertEquals(sizeResult, result.size());
  }

  /**
   * Verifica que se pueda insertar una observación en la base de datos a un alumno en particular. 
   */
  @Test
  public void testInsertarObservacion() throws Exception {
    System.out.println("insertarObservacion");
    Observacion observacion = new Observacion();
    observacion.setFolioInscripcion(29);
    observacion.setNoPersonal(3342);
    observacion.setObservacion("Muy buena comprensión lectora - Hernan Uriel Falconi Falconi");
    DAOObservacionImpl instance = new DAOObservacionImpl();
    boolean expResult = true;
    boolean result = instance.insertarObservacion(observacion);
    assertEquals(expResult, result);
  }
  
}

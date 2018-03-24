
package accesodatos;

import java.util.Date;
import java.util.List;
import logica.daoimpl.DAOActividadProgramadaImpl;
import logica.dominio.ActividadProgramada;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class TestDAOActividadProgramada {
  
  public TestDAOActividadProgramada() {
  }
  
  /**
   * Prueba unitaria del método que inserta una ActividadProgramada de la BD.
   */
  @Test
  public void testInsertarActividadProgramada() throws Exception {
    System.out.println("insertarActividadProgramada");
    Date date = new Date();
    ActividadProgramada actividadProgramada = new ActividadProgramada(5,"Conversation 03", date, date, 1, 1, 2);
    DAOActividadProgramadaImpl instance = new DAOActividadProgramadaImpl();
    boolean expResult = true;
    boolean result = instance.insertarActividadProgramada(actividadProgramada);
    assertEquals(expResult, result);
  }

  /**
   * Prueba unitaria del método que actualiza una ActividadProgramada de la BD.
   */
  @Test
  public void testActualizarActividadProgramada() throws Exception {
    System.out.println("Actualizar ActividadProgramada");
    Date date = new Date();
    ActividadProgramada actividadProgramada = new ActividadProgramada(1,"Conversation 01", date, date, 1, 1, 1);
    DAOActividadProgramadaImpl instance = new DAOActividadProgramadaImpl();
    boolean expResult = true;
    boolean result = instance.actualizarActividadProgramada(actividadProgramada);
    assertEquals(expResult, result);
  }

  /**
   * Prueba unitaria del método que elimina una ActividadProgramada de la BD.
   */
  @Test
  public void testEliminarActividadProgramada() throws Exception {
    System.out.println("Eliminar ActividadProgramada");
    int idActividadProgramada = 13;
    DAOActividadProgramadaImpl instance = new DAOActividadProgramadaImpl();
    boolean expResult = true;
    boolean result = instance.eliminarActividadProgramada(idActividadProgramada);
    assertEquals(expResult, result);
  }
  
}

package accesodatos;

import java.util.List;
import logica.daoimpl.DAOReservacionImpl;
import logica.dominio.Reservacion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alan
 */
public class TestDAOReservacionImpl {

  /**
   * Test of obtenerReservacionesPorAlumno method, of class DAOReservacionImpl.
   */
  @Test
  public void testObtenerReservacionesPorAlumno() throws Exception {
    System.out.println("Obtener reservaciones por alumno");
    String matricula = "S15011638";
    DAOReservacionImpl instance = new DAOReservacionImpl();
    List<Reservacion> result = instance.obtenerReservacionesPorAlumno(matricula);
    assertEquals(4, result.size());
    
  }
  
}

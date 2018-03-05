package accesodatos;

import java.util.List;
import logica.daoimpl.DAOSeccionImpl;
import logica.dominio.Seccion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alancrux
 */
public class TestDAOSeccionImpl {

  /**
   * Verifica que el número de secciones obtenido de una consulta sea correspondiente al
   * número de secciones asignadas a un asesor dado su noPersonal. 
   */
  @Test
  public void testObtenerSecciones() throws Exception {
    System.out.println("obtenerSecciones");
    int noPersonal = 1234;
    DAOSeccionImpl instance = new DAOSeccionImpl();
    List<Seccion> result = instance.obtenerSecciones(noPersonal);
    
    int esperado = 1; 
    int obtenido = result.size();
    assertEquals(esperado, obtenido);

  }
}

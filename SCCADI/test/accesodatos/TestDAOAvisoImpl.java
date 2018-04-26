package accesodatos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.daoimpl.DAOAvisoImpl;
import logica.dominio.Aviso;
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
public class TestDAOAvisoImpl {
  
  public TestDAOAvisoImpl() {
  }

  /**
   * Test of obtenerAvisos method, of class DAOAvisoImpl.
   */
  @Test
  public void testObtenerAvisos() throws Exception {
    System.out.println("obtenerAvisos");
    DAOAvisoImpl instance = new DAOAvisoImpl();  
    List<Aviso> result = instance.obtenerAvisos();
    assertEquals(2, result.size());
  }
}

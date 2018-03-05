package accesodatos;

import java.util.List;
import logica.daoimpl.DAOInscripcionImpl;
import logica.dominio.Alumno;
import logica.dominio.Inscripcion;
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
public class TestDAOInscripcionImpl {
  /**
   * Verifica que el número de alumnos obtenidos tras una consulta sea correspondiente
   * al numero de alumnos registrados para una sección dado el nrc. 
   */
  @Test
  public void testObtenerAlumnos() throws Exception {
    System.out.println("obtenerAlumnos");
    int nrc = 26524;
    DAOInscripcionImpl instance = new DAOInscripcionImpl();
    List<Alumno> result = instance.obtenerAlumnos(nrc);
    int esperado = 10; 
    int resultado = result.size();
    assertEquals(esperado, resultado);
  }
}

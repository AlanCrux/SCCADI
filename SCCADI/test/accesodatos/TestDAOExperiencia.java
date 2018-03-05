package accesodatos;

import logica.daoimpl.DAOExperienciaEducativaImpl;
import logica.dominio.ExperienciaEducativa;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Contiene las pruebas unitarias de los métodos 
 * implementados de la clase DAOExperienciaEducativaImpl.
 * @author Alan Yoset Garcia Cruz
 */
public class TestDAOExperiencia {

  /**
   * Prueba el método que obtiene una experiencia educativa de la BD
   * dado su identificador. 
   * @throws java.lang.Exception
   */
  @Test
  public void testObtenerExperiencia() throws Exception {
    System.out.println("obtenerExperiencia");
    int idExperiencia = 1;
    DAOExperienciaEducativaImpl instance = new DAOExperienciaEducativaImpl();
    ExperienciaEducativa result = instance.obtenerExperiencia(idExperiencia);
    String esperado = "1 Inglés Basico l"; 
    String obtenido = result.getIdExperiencia()+" "+result.getNombre()+" "+result.getNivel(); 
    assertEquals(esperado, obtenido);
  }
 
}

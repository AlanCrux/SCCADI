package accesodatos;

import logica.daoimpl.DAOProgresoImpl;
import logica.dominio.Progreso;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alancrux
 */
public class DAOProgresoImplTest {
    /**
     * Test of obtenerProgreso method, of class DAOProgresoImpl.
     */
    @Test
    public void testObtenerProgreso() throws Exception {
        System.out.println("obtenerProgreso");
        int folioInscripcion = 25;
        DAOProgresoImpl instance = new DAOProgresoImpl();
        Progreso result = instance.obtenerProgreso(folioInscripcion);
        assertNotNull(result);
    }
}

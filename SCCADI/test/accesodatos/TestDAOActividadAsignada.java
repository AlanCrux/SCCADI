/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.daoimpl.DAOActividadAsignadaImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Esmeralda
 */
public class TestDAOActividadAsignada {

    DAOActividadAsignadaImpl actividad = new DAOActividadAsignadaImpl();

    public TestDAOActividadAsignada() {
    }

    @Test
    public void obtenerActividadAsignadaAlAsesor() {
        try {
            int tamanoLista = actividad.obtenerActividadAsignadaAlAsesor(1234).size();
            int tamanoEsperado = 2;

            Assert.assertEquals(tamanoEsperado, tamanoLista);
        } catch (Exception ex) {
            Logger.getLogger(TestDAOActividadAsignada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void obtenerActividadesPorExperiencia() {
        try {
            int tamanoLista = actividad.obtenerActividadesPorExperiencia(1234, 1).size();
            int tamanoEsperado = 2;

            Assert.assertEquals(tamanoEsperado, tamanoLista);
        } catch (Exception ex) {
            Logger.getLogger(TestDAOActividadAsignada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void obtenerActividadesAsignadas() {
        try {
            int tamanoLista = actividad.obtenerActividadAsignada().size();
            int tamanoEsperado = 20;
            Assert.assertEquals(tamanoEsperado, tamanoLista);

        } catch (Exception ex) {
            Logger.getLogger(TestDAOActividadAsignada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void obtenerActividadesPorExperienciaAsesores() {
        try {
            int tamanoLista = actividad.obtenerActividadesPorExperiencia(1).size();
            int tamanoEsperado = 20;

            Assert.assertEquals(tamanoEsperado, tamanoLista);
        } catch (Exception ex) {
            Logger.getLogger(TestDAOActividadAsignada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

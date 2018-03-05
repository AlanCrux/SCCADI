/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasDAOAlumno;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.daoimpl.DAOAlumnoImpl;
import logica.dominio.Alumno;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * En esta clase se encuentran las pruebas unitarias de la clase DAOAlumno
 * @author Esmeralda
 * @version 1.0
 */
public class PruebasDAOAlumno {
    DAOAlumnoImpl alumnoAux = new DAOAlumnoImpl();
    Alumno alumno = new Alumno("S123", "hernan ", "aaaaa", "aaaaa", "aaaa", "aaaaaa");
    
    /**
     * Prueba del metodo para editar los datos de un alumno 
     */
    @Test
    public void editarAlumno() {
        try {
            boolean editarAlumno = alumnoAux.actualizarAlumno("S123", alumno);
            boolean resultadoEsperado = true;
            assertEquals(resultadoEsperado, editarAlumno);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasDAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Prueba del metodo que elimina los datos de un alumno
     */
    @Test
    public void eliminarAlumno() {
        boolean eliminarAlumno = alumnoAux.eliminarAlumno("S123");
        boolean resultadoEsperado = true;

        assertEquals(eliminarAlumno, resultadoEsperado);
    }
}

package accesodatos;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.daoimpl.DAOAlumnoImpl;
import logica.dominio.Alumno;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * En esta clase se encuentran las pruebas unitarias de la clase DAOAlumno
 *
 * @author Esmeralda Jiménez Ramos
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public class TestDAOAlumno {

  DAOAlumnoImpl alumnoAux = new DAOAlumnoImpl();
  Alumno alumno = new Alumno("S123", "hernan ", "aaaaa", "aaaaa", "aaaa", "aaaaaa");

  /**
   * Prueba del metodo para editar los datos de un alumno.
   */
  @Test
  public void editarAlumno() {
    try {
      boolean editarAlumno = alumnoAux.actualizarAlumno("S123", alumno);
      boolean resultadoEsperado = true;
      assertEquals(resultadoEsperado, editarAlumno);
    } catch (Exception ex) {
      Logger.getLogger(TestDAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Prueba del metodo que elimina los datos de un alumno.
   */
  @Test
  public void eliminarAlumno() {
    boolean eliminarAlumno = false;
    try {
      eliminarAlumno = alumnoAux.eliminarAlumno("S123");
    } catch (Exception ex) {
      Logger.getLogger(TestDAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
    }
    boolean resultadoEsperado = true;
    assertEquals(eliminarAlumno, resultadoEsperado);
  }
  
  /**
   * Prueba unitaria del método que inserta un alumno en la BD.
   */
  public void testInsertarAlumno(){
    Alumno nuevoAlumno = new Alumno("S321", "Josesito ", "aaaaa", "aaaaa", "aaaa", "aaaaaa");
    boolean obtenido = false;
    try {
      obtenido = alumnoAux.insertarAlumno(nuevoAlumno);
    } catch (Exception ex) {
      Logger.getLogger(TestDAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
    }
    boolean esperado = true; 
    assertEquals(obtenido, esperado);
  }
}

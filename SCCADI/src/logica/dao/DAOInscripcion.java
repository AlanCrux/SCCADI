package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Alumno;
import logica.dominio.Inscripcion;
import logica.dominio.Seccion;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public interface DAOInscripcion {
    public List<Inscripcion> obtenerInscripciones() throws SQLException;
    public List<Alumno> obtenerAlumnos(int nrc) throws SQLException; 
    public List<Seccion> obtenerSecciones(String matricula) throws SQLException; 
    public Inscripcion obtenerInscripciones(int folio) throws SQLException;
    public boolean insertarInscripciones(Inscripcion inscripcion) throws SQLException;
    public boolean actualizarInscripciones(int folio) throws SQLException;
    public boolean eliminarInscripciones(int folio) throws SQLException;
}

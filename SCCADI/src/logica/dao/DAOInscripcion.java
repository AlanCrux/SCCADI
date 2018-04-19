package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Alumno;
import logica.dominio.Inscripcion;
import logica.dominio.Seccion;

/**
 * Define los métodos a implementar en el DAOInscripcionImpl.
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public interface DAOInscripcion {
    public List<Inscripcion> obtenerInscripciones(String matricula) throws Exception;
    public List<Alumno> obtenerAlumnos(int nrc) throws Exception; 
    public List<Seccion> obtenerSecciones(String matricula) throws Exception; 
    public Inscripcion obtenerInscripciones(int folio) throws Exception;
    public boolean insertarInscripcion(Inscripcion inscripcion) throws Exception;
    public boolean actualizarInscripcion(int folio) throws Exception;
    public boolean eliminarInscripcion(int folio) throws Exception;
    public boolean eliminarInscripcionesPorMatricula(Alumno alumno) throws Exception;
}

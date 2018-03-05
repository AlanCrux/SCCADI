package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Alumno;

/**
 * En esta clase se definen las interfaces a utilizar en el DAOAlumno
 * @author Esmeralda
 * @version 1.0
 */

public interface DaoAlumno {
  public List<Alumno> obtenerAlumnos() throws SQLException;
  public List<Alumno> obtenerAlumnosFiltrados(String caracterBusqueda) throws SQLException;
  public Alumno obtenerAlumno(String matricula) throws SQLException;
  public boolean insertarAlumno(Alumno Alumno) throws SQLException;
  public boolean actualizarAlumno(String identificador, Alumno alumno) throws SQLException;
  public boolean eliminarAlumno(Alumno alumno) throws SQLException;
  public boolean eliminarAlumno(String matricula) throws SQLException;
}

package logica.dao;

import java.util.List;
import logica.dominio.Alumno;

/**
 * En esta clase se definen las interfaces a utilizar en el DAOAlumno
 * @author Esmeralda
 * @version 1.0
 */

public interface DaoAlumno {
  public List<Alumno> obtenerAlumnos() throws Exception;
  public List<Alumno> obtenerAlumnosFiltrados(String caracterBusqueda) throws Exception;
  public Alumno obtenerAlumno(String matricula) throws Exception;
  public boolean insertarAlumno(Alumno Alumno) throws Exception;
  public boolean actualizarAlumno(String matricula, Alumno alumno) throws Exception;
  public boolean eliminarAlumno(Alumno alumno) throws Exception;
  public boolean eliminarAlumno(String matricula) throws Exception;
}

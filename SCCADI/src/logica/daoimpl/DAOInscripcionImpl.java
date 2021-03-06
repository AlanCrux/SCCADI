package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOInscripcion;
import logica.dominio.Alumno;
import logica.dominio.Inscripcion;
import logica.dominio.Seccion;

/**
 * Esta clase implementa los métodos definidos en la interfaz DAOInscripcion nos permite realizar
 * inserciones, consultas, actualizaciones y eliminar datos de la tabla Inscripcion en la base de
 * datos.
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOInscripcionImpl extends Conexion implements DAOInscripcion {

  /**
   * Obtiene de la base de datos los alumnos inscritos a una sección.
   *
   * @param nrc el identificador de la sección.
   * @return la lista de alumnos inscritos.
   * @throws Exception
   */
  @Override
  public List<Alumno> obtenerAlumnos(int nrc) throws Exception {
    List<Alumno> alumnos = new ArrayList();
    String consulta = "select * from Alumno as T1 INNER JOIN Inscripcion as T2 ON T1.matricula = T2.matricula where nrc =?;";

    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(consulta);
      st.setInt(1, nrc);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        Alumno alumno = new Alumno();
        alumno.setMatricula(rs.getString("matricula"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setCorreo(rs.getString("correo"));
        alumno.setContactoEmergencia(rs.getString("contactoEmergencia"));
        alumno.setProgramaEducativo(rs.getString("programaEducativo"));
        alumno.setNumeroEmergencia(rs.getString("numeroEmergencia"));
        alumno.setFotografia(rs.getBlob("fotografia"));
        alumnos.add(alumno);
      }
      rs.close();
      st.close();
    } catch (Exception ex) {
      throw ex;
    }
    return alumnos;
  }

  /**
   * Obtiene las secciones a las que esta inscrito un alumno.
   * @param matricula identificador del alumno.
   * @return lista con las secciones a las que esta inscrito el alumno. 
   * @throws Exception ocurre si hay un error en la consulta. 
   */
  @Override
  public List<Seccion> obtenerSecciones(String matricula) throws Exception {
    List<Seccion> secciones = new ArrayList();
    String consulta = "select * from Seccion as T1 INNER JOIN Inscripcion as T2 ON T1.nrc = T2.nrc where matricula =?;";

    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(consulta);
      st.setString(1, matricula);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        int nrc = rs.getInt("nrc");
        int idExperiencia = rs.getInt("idExperiencia");
        int nPersonal = rs.getInt("noPersonal");
        String periodo = rs.getString("periodo");
        int cupo = rs.getInt("cupo");
        Seccion seccion = new Seccion(nrc, idExperiencia, nPersonal, periodo, cupo);
        secciones.add(seccion);
      }
      rs.close();
      st.close();
    } catch (Exception ex) {
      throw ex;
    }
    return secciones;
  }

  /**
   * Obtiene las inscripciones asociadas a un alumno en la BD.
   * @param matricula identificador del alumno. 
   * @return lista con las inscripciones del alumno
   * @throws Exception ocurre si hay un error en la consulta. 
   */
  @Override
  public List<Inscripcion> obtenerInscripciones(String matricula) throws Exception {
    List<Inscripcion> incripciones = new ArrayList();
    String consulta = "select * from inscripcion where matricula =?;";

    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(consulta);
      st.setString(1, matricula);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setNrc(rs.getInt("nrc"));
        inscripcion.setMatricula(rs.getString("matricula"));
        inscripcion.setFolioInscripcion(rs.getInt("folioInscripcion"));
        incripciones.add(inscripcion);
      }
      rs.close();
      st.close();
    } catch (Exception ex) {
      throw ex;
    }
    return incripciones;
  }
  
  /**
   * Obtiene una inscripcion de un alumno en una sección especifica.
   * @param matricula identificador del alumno. 
   * @param nrc identificador de la seccion
   * @return lista con las inscripciones del alumno
   * @throws Exception ocurre si hay un error en la consulta. 
   */
  public Inscripcion obtenerInscripcion(String matricula, int nrc) throws Exception {
    Inscripcion inscripcion = null; 
    String consulta = "select * from inscripcion where matricula =? AND nrc=?;";

    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(consulta);
      st.setString(1, matricula);
      st.setInt(2, nrc);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        inscripcion = new Inscripcion();
        inscripcion.setNrc(rs.getInt("nrc"));
        inscripcion.setMatricula(rs.getString("matricula"));
        inscripcion.setFolioInscripcion(rs.getInt("folioInscripcion"));
      }
      rs.close();
      st.close();
    } catch (Exception ex) {
      throw ex;
    }
    return inscripcion;
  }

  @Override
  public Inscripcion obtenerInscripciones(int folio) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Inserta una inscripción en la base de datos.
   *
   * @param inscripcion la inscripción que se va a insertar.
   * @return true si la inscripción se inserto correctamente.
   * @throws Exception Puede lanzar una excepción si hay error de conexión con la BD.
   */
  @Override
  public boolean insertarInscripcion(Inscripcion inscripcion) throws Exception {
    String query = "INSERT INTO inscripcion(nrc,matricula) values(?,?)";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setInt(1, inscripcion.getNrc());
      st.setString(2, inscripcion.getMatricula());
      st.executeUpdate();
      return true;
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        this.close();
      } catch (Exception e) {
        throw e;
      }
    }
  }

  @Override
  public boolean actualizarInscripcion(int folio) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarInscripcion(int folio) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Permite eliminar inscripciones vinculadas a un alumno.
   *
   * @param alumno matricula del alumno del cual se quieren eliminar las inscripciones.
   * @return true si se eliminaron las inscripciones correctemente.
   * @throws Exception Puede lanzar una excepción si hay error de conexión con la BD.
   */
  @Override
  public boolean eliminarInscripcionesPorMatricula(Alumno alumno) throws Exception {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("delete from Inscripcion where matricula = ?");
      st.setString(1, alumno.getMatricula());
      st.executeUpdate();
      st.close();
    } catch (SQLException e) {
      System.out.println(e);
      return false;
    } finally {
      this.close();
    }
    return true;
  }

}

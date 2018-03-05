package logica.daoimpl;

import datos.Conexion;
import logica.dao.DaoAlumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dominio.Alumno;

/**
 * En esta clase se implementan todos los metodos del DaoAlumno, nos permite realizar consultas y
 * cambios en la base de datos
 *
 * @author Esmeralda Jimenez Ramos
 * @version 1.0
 */
public class DAOAlumnoImpl extends Conexion implements DaoAlumno {

  /**
   * Obtiene un Alumno. Este método busca todos los Alumnos registrados en la BD y los regresa en
   * una lista
   *
   * @return list de Alumno
   * @throws SQLException
   */
  public List<Alumno> obtenerAlumnos() throws SQLException {
    List<Alumno> alumnos = null;
    try {
      this.connection();
      try (PreparedStatement st = this.conn.prepareStatement("select * from alumno")) {
        alumnos = new ArrayList();
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
          Alumno alumno = new Alumno();
          alumno.setMatricula(rs.getString("matricula"));
          alumno.setNombre(rs.getString("nombre"));
          alumno.setCorreo(rs.getString("correo"));
          alumno.setProgramaEducativo(rs.getString("programaEducativo"));
          alumno.setContactoEmergencia(rs.getString("contactoEmergencia"));
          alumno.setNumeroEmergencia(rs.getString("numeroEmergencia"));
          alumno.setFotografia(rs.getBlob("fotografia"));
          alumnos.add(alumno);
        }
        rs.close();
      }
    } catch (SQLException e) {
      System.out.println("error en la consulta");
    } finally {
      this.close();
    }
    return alumnos;
  }

  /**
   * Obtiene un Alumno. Este método utiliza la clave matricula para obtener un Alumno en especial
   *
   * @param matricula clave para discriminar
   * @return Regresa un objeto tipo Alumno
   * @throws SQLException
   */
  public Alumno obtenerAlumno(String matricula) throws SQLException {
    Alumno alumno = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from alumno where matricula =?");
      st.setString(1, matricula);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        alumno = new Alumno();
        alumno.setMatricula(matricula);
        alumno.setNombre(rs.getString("nombre"));
        alumno.setCorreo(rs.getString("correo"));
        alumno.setProgramaEducativo(rs.getString("programaEducativo"));
        alumno.setContactoEmergencia(rs.getString("contactoEmergencia"));
        alumno.setNumeroEmergencia(rs.getString("numeroEmergencia"));
        alumno.setFotografia(rs.getBlob("fotografia"));
      }
      rs.close();
      st.close();
    } catch (SQLException e) {

    } finally {
      this.close();
    }
    return alumno;
  }

  public boolean insertarAlumno(Alumno Alumno) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

  }

  /**
   * Este metodo nos permite actualizar los datos de un alumno en la base de datos
   *
   * @param alumno Objeto que contiene toda la informacion de un alumno
   * @return True si la actualizacion se realizo con exito
   * @throws SQLException ocurre si se pierde la conexion con la base de datos o hay algun error en
   * los querys
   */
  public boolean actualizarAlumno(Alumno alumno) throws SQLException {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("update Alumno set matricula, "
          + "nombre, correo, programaEducativo, contactoEmergencia, "
          + "numeroEmergencia, fotografia where matricula = ?");
      st.setString(1, alumno.getMatricula());
      ResultSet rs = st.executeQuery();
      rs.close();
      st.close();
    } catch (SQLException e) {
      return false;
    } finally {
      this.close();
    }
    return true;
  }

  /**
   * Este metodo permite eliminar el registro de un alumno de la base de datos
   * @param alumno Objeto que contiene toda la informacion de un alumno
   * @return True si se elimino el registro exitosamente
   */
  @Override
  public boolean eliminarAlumno(Alumno alumno) {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("delete from Alumno where matricula = ?");
      st.setString(1, alumno.getMatricula());
      ResultSet rs = st.executeQuery();
      rs.close();
      st.close();
    } catch (SQLException e) {
      return false;
    } finally {
      this.close();
    }
    return true;
  }

}

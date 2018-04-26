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
  @Override
  public List<Alumno> obtenerAlumnos() throws Exception {
    List<Alumno> alumnos = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from alumno");
      ResultSet rs = st.executeQuery();
      alumnos = new ArrayList();
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
    } catch (SQLException e) {
      System.out.println("error en la consulta");
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
  @Override
  public Alumno obtenerAlumno(String matricula) throws Exception {
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

    }
    return alumno;
  }

  /**
   * Permite insertar un alumno en la base de datos.
   *
   * @param alumno el alumno que se va a insertar.
   * @return true si el alumno se inserto correctamente.
   * @throws Exception Puede lanzar una excepción si hay error de conexión con la BD.
   */
  @Override
  public boolean insertarAlumno(Alumno alumno) throws Exception {
    String query = "INSERT INTO alumno(matricula, nombre, correo, programaEducativo, "
        + "contactoEmergencia, numeroEmergencia, fotografia) values(?,?,?,?,?,?,?);";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setString(1, alumno.getMatricula());
      st.setString(2, alumno.getNombre());
      st.setString(3, alumno.getCorreo());
      st.setString(4, alumno.getProgramaEducativo());
      st.setString(5, alumno.getContactoEmergencia());
      st.setString(6, alumno.getNumeroEmergencia());
      st.setBlob(7, alumno.getFotografia());
      st.executeUpdate();
      return true;
    } catch (SQLException e) {
      throw e;
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

  /**
   * Este metodo nos permite actualizar los datos de un alumno en la base de datos
   *
   * @param alumno Objeto que contiene toda la informacion de un alumno
   * @return True si la actualizacion se realizo con exito
   * @throws SQLException ocurre si se pierde la conexion con la base de datos o hay algun error en
   * los querys
   */
  public boolean actualizarAlumno(Alumno alumno) throws Exception {
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
   *
   * @param alumno Objeto que contiene toda la informacion de un alumno
   * @return True si se elimino el registro exitosamente
   */
  @Override
  public boolean eliminarAlumno(Alumno alumno) throws Exception {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("delete from Alumno where matricula = ?");
      st.setString(1, alumno.getMatricula());
      st.executeUpdate();
      st.close();
    } catch (SQLException e) {
      return false;
    } finally {
      this.close();
    }
    return true;
  }

  /**
   * Este metodo nos permite actualizar los datos de un alumno en la base de datos
   *
   * @param matricula matricula del alumno a modificar
   * @param alumno Objeto que contiene toda la informacion de un alumno
   * @return True si la actualizacion se realizo con exito
   * @throws SQLException ocurre si se pierde la conexion con la base de datos o hay algun error en
   * los querys
   */
  @Override
  public boolean actualizarAlumno(String matricula, Alumno alumno) throws Exception {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("update Alumno set matricula = ?, "
          + "nombre = ?, correo = ?, programaEducativo = ?, contactoEmergencia = ?, "
          + "numeroEmergencia = ? where matricula = ?");
      st.setString(1, alumno.getMatricula());
      st.setString(2, alumno.getNombre());
      st.setString(3, alumno.getCorreo());
      st.setString(4, alumno.getProgramaEducativo());
      st.setString(5, alumno.getContactoEmergencia());
      st.setString(6, alumno.getNumeroEmergencia());
      st.setString(7, matricula);
      st.executeUpdate();
      st.close();
    } catch (SQLException e) {
      System.out.println(e);
    }

    return true;
  }

  /**
   * Este metodo permite eliminar el registro de un alumno de la base de datos
   *
   * @param matricula
   * @return True si se elimino el registro exitosamente
   */
  @Override
  public boolean eliminarAlumno(String matricula) throws Exception {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("delete from Alumno where matricula = ?");
      st.setString(1, matricula);
      st.executeUpdate();
      st.close();
    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * Este metodo permite obtener la lista de alumnos filtrados por nombre o matricula
   *
   * @param caracterBusqueda caracter usado para el filtrado
   * @return Lista de tipo alumnos
   * @throws SQLException
   */
  @Override
  public List<Alumno> obtenerAlumnosFiltrados(String caracterBusqueda) throws Exception {
    List<Alumno> alumnos = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from "
          + "alumno where matricula LIKE '%" + caracterBusqueda + "%' "
          + "or nombre like '%" + caracterBusqueda + "%' ");

      ResultSet rs = st.executeQuery();
      alumnos = new ArrayList();
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

    } catch (Exception e) {
      System.out.println("error en la consulta");
      System.out.println(e);
    }
    return alumnos;
  }
}

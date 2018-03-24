package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOSeccion;
import logica.dominio.Seccion;

/**
 * Esta clase implementa los métodos definidos en la interfaz DAOSeccion
 * nos permite realizar inserciones, consultas, actualizaciones y eliminar datos de la tabla
 * Seccion en la base de datos.
 * @author Alan Yoset García Cruz
 */
public class DAOSeccionImpl extends Conexion implements DAOSeccion {

  /**
   * Obtiene de la base de datos las secciones asociadas a un asesor.
   * @param noPersonal identificador del asesor.
   * @return lista de secciones asociadas. 
   * @throws Exception 
   */
  @Override
  public List<Seccion> obtenerSecciones(int noPersonal) throws Exception {
    List<Seccion> secciones = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from seccion where noPersonal=?");
      st.setInt(1, noPersonal);
      secciones = new ArrayList();
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
    } catch (Exception e) {
      throw e;
    } finally {
      this.close();
    }
    return secciones;
  }

  @Override
  public Seccion obtenerSeccion(String nrc) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean insertarSeccion(Seccion seccion) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Permite actualizar los datos de una sección en la base de datos.
   * @param seccion la sección que se quiere actualizar. 
   * @return true si los datos se actualizaron correctamente.
   * @throws Exception Puede lanzar una excepción si hay error de conexión con la BD. 
   */
  @Override
  public boolean actualizarSeccion(Seccion seccion) throws Exception {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("update seccion set cupo = ? where nrc = ?");
      st.setInt(1, seccion.getCupo() - 1);
      st.setInt(2, seccion.getNrc());
      st.executeUpdate();
      st.close();
      return true;
    } catch (Exception e) {
      throw e; 
    }
  }

  @Override
  public boolean eliminarSeccion(String nrc) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Permite obtener todas las secciones de la base de datos. 
   * @return List cargada de las secciones recuperadas.
   * @throws Exception Puede lanzar una excepción si hay error de conexión con la BD. 
   */
  public List<Seccion> obtenerSecciones() throws Exception {
    List<Seccion> secciones = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from seccion");
      secciones = new ArrayList();
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
    } catch (Exception e) {
      throw e;
    } finally {
      this.close();
    }
    return secciones;
  }

}

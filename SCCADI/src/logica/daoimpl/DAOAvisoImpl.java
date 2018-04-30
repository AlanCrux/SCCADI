package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOAviso;
import logica.dominio.Aviso;
import utilerias.DateConvertUtils;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOAvisoImpl extends Conexion implements DAOAviso {

  /**
   * Obtiene todos los avisos registrados en la base de datos
   * @return Lista con los avisos obtenidos. 
   * @throws Exception si hay error de conexión con la BD.
   */
  @Override
  public List<Aviso> obtenerAvisos() throws Exception {
    List<Aviso> avisos;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from aviso");
      ResultSet rs = st.executeQuery();
      avisos = new ArrayList();
      while (rs.next()) {
        Aviso aviso = new Aviso();
        aviso.setIdAviso(rs.getInt("idAviso"));
        aviso.setFechaInicio(rs.getDate("fechaInicio"));
        aviso.setFechaFin(rs.getDate("fechaFin"));
        aviso.setAsunto(rs.getString("asunto"));
        aviso.setContenido(rs.getString("contenido"));
        avisos.add(aviso);
      }
    } catch (Exception e) {
      throw e;
    }
    return avisos;
  }

  @Override
  public Aviso obtenerAviso(int idAviso) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Inserta un aviso en la base de datos del sistema. 
   * @param aviso el aviso que se quiere insertar. 
   * @return true si se inserto correctamente. 
   * @throws Exception si hay error de conexión con la BD.
   */
  @Override
  public boolean insertarAviso(Aviso aviso) throws Exception {
    String query = "INSERT INTO aviso(asunto,contenido,fechaInicio,fechaFin) values(?,?,?,?)";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setString(1, aviso.getAsunto());
      st.setString(2, aviso.getContenido());
      st.setDate(3, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(aviso.getFechaInicio())));
      st.setDate(4, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(aviso.getFechaFin())));
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

  /**
   * Actualiza los datos de un aviso en especifico en la base de datos del sistema.
   * @param aviso el aviso que se quiere actualizar
   * @return true si se actualizo correctamente.
   * @throws Exception si hay error de conexión con la BD.
   */
  @Override
  public boolean actualizarAviso(Aviso aviso) throws Exception {
    String query = "UPDATE aviso SET asunto = ?, contenido = ?, fechaInicio = ?, fechaFin = ?"
        + "  where idAviso = ?";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setString(1, aviso.getAsunto());
      st.setString(2, aviso.getContenido());
      st.setDate(3, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(aviso.getFechaInicio())));
      st.setDate(4, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(aviso.getFechaFin())));
      st.setInt(5, aviso.getIdAviso());
      st.executeUpdate();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
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
   * Elimina un aviso de la base de datos
   * @param idAviso el id del aviso que se quiere eliminar
   * @return true si se elimino correctamente.
   * @throws Exception si hay error de conexión con la BD.
   */
  @Override
  public boolean eliminarAviso(int idAviso) throws Exception {
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("DELETE FROM aviso where idAviso = ?");
      st.setInt(1, idAviso);
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

}

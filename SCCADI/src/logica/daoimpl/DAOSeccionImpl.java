package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Seccion seccion = new Seccion();
        seccion.setNrc(rs.getInt("nrc"));
        seccion.setIdExperiencia(rs.getInt("idExperiencia"));
        seccion.setNoPersonal(rs.getInt("noPersonal"));
        seccion.setPeriodo(rs.getString("periodo"));
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

  @Override
  public boolean actualizarSeccion(String nrc) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarSeccion(String nrc) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

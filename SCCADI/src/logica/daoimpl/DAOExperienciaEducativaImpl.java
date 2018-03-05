package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import logica.dao.DAOExperienciaEducativa;
import logica.dominio.ExperienciaEducativa;

/**
 * Esta clase implementa los métodos definidos en la interfaz DAOExperienciaEducativa
 * nos permite realizar inserciones, consultas, actualizaciones y eliminar datos de la tabla
 * ExperienciaEducativa en la base de datos.
 * @author Alan Yoset Garcia Curz
 */
public class DAOExperienciaEducativaImpl extends Conexion implements DAOExperienciaEducativa {

  @Override
  public List<ExperienciaEducativa> obtenerExperiencias() throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * 
   * @param idExperiencia el identificador de la experiencia que se quiere recuperar.
   * @return devuelve un objeto de tipo ExperienciaEducativa.
   * @throws SQLException 
   */
  @Override
  public ExperienciaEducativa obtenerExperiencia(int idExperiencia) throws Exception {
    ExperienciaEducativa experiencia = null;
    this.connection();
    try {
      PreparedStatement st = this.conn.prepareStatement("select * from ExperienciaEducativa where idExperiencia =?");
      st.setInt(1, idExperiencia);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        experiencia = new ExperienciaEducativa();
        experiencia.setIdExperiencia(idExperiencia);
        experiencia.setNivel(rs.getString("nivel"));
        experiencia.setNombre(rs.getString("nombre"));
      }
      rs.close();
      st.close();
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.close();
    }
    return experiencia;
  }

  @Override
  public boolean insertarExperiencia(ExperienciaEducativa experienciaEducativa) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizarExperiencia(int idExperiencia) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarExperiencia(int idExperiencia) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

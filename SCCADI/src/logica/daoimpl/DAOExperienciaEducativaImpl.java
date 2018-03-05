package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import logica.dao.DAOExperienciaEducativa;
import logica.dominio.ExperienciaEducativa;

/**
 *
 * @author alancrux
 */
public class DAOExperienciaEducativaImpl extends Conexion implements DAOExperienciaEducativa {

  @Override
  public List<ExperienciaEducativa> obtenerExperiencias() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public ExperienciaEducativa obtenerExperiencia(int idExperiencia) throws SQLException {
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
    } catch (SQLException sqlEx) {
      throw sqlEx;
    } finally {
      this.close();
    }
    return experiencia;
  }

  @Override
  public boolean insertarExperiencia(ExperienciaEducativa experienciaEducativa) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizarExperiencia(int idExperiencia) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarExperiencia(int idExperiencia) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

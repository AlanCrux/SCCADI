package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOObservacion;
import logica.dominio.Observacion;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOObservacionImpl extends Conexion implements DAOObservacion {

  /**
   * Recupera de la base de datos todas las observaciones que coincidan con un folio de inscripción espefico. 
   * @param folioInscripcion identificador de la inscripción.
   * @return Lista de observaciones. 
   * @throws Exception Si hay un error al conectar con la base de datos.
   */
  @Override
  public List<Observacion> obtenerObservaciones(int folioInscripcion) throws Exception {
    List<Observacion> observaciones = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from observacion where folioInscripcion=?");
      st.setInt(1, folioInscripcion);
      ResultSet rs = st.executeQuery();
      observaciones = new ArrayList();
      while (rs.next()) {
        Observacion observacion = new Observacion();
        observacion.setFolioInscripcion(rs.getInt("folioInscripcion"));
        observacion.setIdObservacion(rs.getInt("idObservacion"));
        observacion.setNoPersonal(rs.getInt("noPersonal"));
        observacion.setObservacion(rs.getString("observacion"));
        observaciones.add(observacion);
      }
    } catch (Exception e) {
      throw e;
    }
    return observaciones;
  }

  /**
   * Inserta una observación en la base de datos asociada a un alumn en especifico. 
   * @param observacion
   * @return
   * @throws Exception 
   */
  @Override
  public boolean insertarObservacion(Observacion observacion) throws Exception {
    String query = "INSERT INTO observacion(folioInscripcion, noPersonal, observacion) values(?,?,?);";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setInt(1, observacion.getFolioInscripcion());
      st.setInt(2, observacion.getNoPersonal());
      st.setString(3, observacion.getObservacion());
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

}

package logica.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOExamen;
import logica.dominio.Examen;
import datos.Conexion;

/**
 * Clase que implementa las consultas a la base de datos sobre los examenes registrados
 *
 * @author Esmeralda
 */
public class DAOExamenImpl extends Conexion implements DAOExamen {

  /**
   * Este metodo obtiene de la base de datos todos los examenes correspondientes a una inscripcion
   *
   * @param folioInscripcion identificador de la inscripcion con el examen
   * @return lista de examenes
   * @throws Exception
   */
  @Override
  public List<Examen> obtenerExamenes(int folioInscripcion) throws Exception {
    List<Examen> examenes;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select examen.idExamen, "
          + "examen.tipo, examen.fecha, examen.descripcion, "
          + "calificacion.calificacion from examen join calificacion "
          + "join inscripcion where calificacion.idExamen = examen.idExamen "
          + "and calificacion.folioInscripcion = inscripcion.folioInscripcion "
          + "and calificacion.folioInscripcion = ?;");
      st.setInt(1, folioInscripcion);
      ResultSet rs = st.executeQuery();
      examenes = new ArrayList();
      while (rs.next()) {
        Examen examen = new Examen();
        examen.setIdExamen(rs.getInt("idExamen"));
        //examen.setCalificacion(rs.getInt("calificacion"));
        examen.setDescripcion(rs.getString("descripcion"));
        examen.setFecha(rs.getDate("fecha"));
        examen.setTipo(rs.getString("tipo"));
        //examen.setFolioInscripcion(folioInscripcion);

        examenes.add(examen);
      }
    } catch (Exception e) {
      throw e;
    }

    return examenes;
  }

  public List<Examen> obtenerExamenesPorSeccion(int nrc) throws Exception {
    List<Examen> examenes;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from examen where seccion_nrc=?");
      st.setInt(1, nrc);
      ResultSet rs = st.executeQuery();
      examenes = new ArrayList();
      while (rs.next()) {
        Examen examen = new Examen();
        examen.setIdExamen(rs.getInt("idExamen"));
        examen.setDescripcion(rs.getString("descripcion"));
        examen.setFecha(rs.getDate("fecha"));
        examen.setTipo(rs.getString("tipo"));
        examen.setNrc(rs.getInt("seccion_nrc"));
        examenes.add(examen);
      }
    } catch (Exception e) {
      throw e;
    }

    return examenes;
  }

  @Override
  public Examen obtenerExamen(int idExamen) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean insertarExamen(Examen examen) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizarExamen(int noPersonal) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarExamen(int noPersonal) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

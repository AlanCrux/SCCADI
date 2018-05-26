package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.dao.DAOExperienciaEducativa;
import logica.dominio.ExperienciaEducativa;

/**
 * Esta clase implementa los métodos definidos en la interfaz DAOExperienciaEducativa nos permite
 * realizar inserciones, consultas, actualizaciones y eliminar datos de la tabla
 * ExperienciaEducativa en la base de datos.
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOExperienciaEducativaImpl extends Conexion implements DAOExperienciaEducativa {

  /**
   * Metodo que obtiene todas las experiencias educativas en las que el asesor tiene actividades
   * asignadas
   *
   * @param noPersonal Clave unica del asesor
   * @return Lista de tipo experiencia educativa las experiencias educativas en las que el asesor
   * tiene actividades asignadas
   * @throws Exception
   */
  public List<ExperienciaEducativa> obtenerExperienciasPorAsesor(int noPersonal) throws Exception {
    List<ExperienciaEducativa> expEdu = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select experienciaeducativa.idExperiencia, experienciaeducativa.nombre,"
          + " experienciaeducativa.nivel from experienciaeducativa join "
          + "actividadasignada join asesor join actividadprogramada where "
          + "actividadasignada.noPersonal = asesor.noPersonal and "
          + "actividadprogramada.idExperiencia = experienciaeducativa.idExperiencia "
          + "and actividadasignada.noPersonal = ? group by nivel;");
      st.setInt(1, noPersonal);
      ResultSet rs = st.executeQuery();
      expEdu = new ArrayList();
      while (rs.next()) {
        ExperienciaEducativa experienciEdu = new ExperienciaEducativa();
        experienciEdu.setIdExperiencia(rs.getInt("idExperiencia"));
        experienciEdu.setNivel(rs.getString("nivel"));
        experienciEdu.setNombre(rs.getString("nombre"));
        expEdu.add(experienciEdu);
      }
    } catch (Exception e) {
      throw e;
    }
    return expEdu;
  }

  @Override
  public List<ExperienciaEducativa> obtenerExperiencias() throws Exception {
    List<ExperienciaEducativa> experiencias;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select * from experienciaeducativa");
      ResultSet rs = st.executeQuery();
      experiencias = new ArrayList();
      while (rs.next()) {
        ExperienciaEducativa experiencia = new ExperienciaEducativa();
        experiencia.setIdExperiencia(rs.getInt("idExperiencia"));
        experiencia.setNombre(rs.getString("nombre"));
        experiencia.setNivel(rs.getString("nivel"));
        experiencia.setNumModulos(rs.getInt("numModulos"));
        experiencia.setNumUnidades(rs.getInt("numUnidades"));
        experiencia.setNumAutoevaluaciones(rs.getInt("numAutoevaluaciones"));
        experiencia.setNumBitacoras(rs.getInt("numBitacoras"));
        experiencia.setNumExamenes(rs.getInt("numExamenes"));
        experiencia.setNumReflexiones(rs.getInt("numReflexiones"));
        experiencia.setNumSeguimiento(rs.getInt("numSeguimiento"));
        experiencia.setNumTaller(rs.getInt("numTaller"));
        
        experiencia.setPorcentajeAutoevaluaciones(rs.getInt("PorcentajeAutoevaluaciones"));
        experiencia.setPorcentajeBitacoras(rs.getInt("PorcentajeBitacoras"));
        experiencia.setPorcentajeExamenes(rs.getInt("PorcentajeExamenes"));
        experiencia.setPorcentajeReflexiones(rs.getInt("PorcentajeReflexiones"));
        experiencia.setPorcentajeSeguimiento(rs.getInt("PorcentajeSeguimientos"));
        experiencia.setPorcentajeTaller(rs.getInt("PorcentajeTalleres"));
        
        experiencias.add(experiencia);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

    return experiencias;
  }

  /**
   * Permite obtener una experiencia educativa de la base de datos.
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
        experiencia.setNumAutoevaluaciones(rs.getInt("numAutoevaluaciones"));
        experiencia.setNumReflexiones(rs.getInt("numReflexiones"));
        experiencia.setNumBitacoras(rs.getInt("numBitacoras"));
        experiencia.setNumSeguimiento(rs.getInt("numSeguimiento"));
      }
      rs.close();
      st.close();
    } catch (SQLException ex) {
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

  public ExperienciaEducativa obtenerExperienciasPorAlumno(String matricula) throws Exception {
    ExperienciaEducativa experienciEdu = null;
    this.connection();
    try {

      PreparedStatement st = this.conn.prepareStatement("select experienciaeducativa.idExperiencia, experienciaeducativa.nombre, experienciaeducativa.nivel, experienciaeducativa.numAutoevaluaciones,"
          + "experienciaeducativa.numBitacoras, experienciaeducativa.numExamenes, experienciaeducativa.numReflexiones, experienciaeducativa.numSeguimiento,"
          + "experienciaeducativa.numTaller, experienciaeducativa.porcentajeAutoevaluaciones, experienciaeducativa.porcentajeBitacoras, experienciaeducativa.porcentajeExamenes, "
          + "experienciaeducativa.porcentajeReflexiones, experienciaeducativa.porcentajeSeguimientos, experienciaeducativa.porcentajeTalleres, experienciaeducativa.numUnidades,"
          + "experienciaeducativa.numModulos from experienciaeducativa join seccion join inscripcion where experienciaeducativa.idExperiencia = seccion.idExperiencia and "
          + "seccion.nrc = inscripcion.nrc and inscripcion.matricula = ?;");
      st.setString(1, matricula);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        experienciEdu = new ExperienciaEducativa();
        experienciEdu.setIdExperiencia(rs.getInt("idExperiencia"));
        experienciEdu.setNivel(rs.getString("nivel"));
        experienciEdu.setNombre(rs.getString("nombre"));
        experienciEdu.setNumAutoevaluaciones(rs.getInt("numAutoevaluaciones"));
        experienciEdu.setNumBitacoras(rs.getInt("numBitacoras"));
        experienciEdu.setNumExamenes(rs.getInt("numExamenes"));
        experienciEdu.setNumReflexiones(rs.getInt("numReflexiones"));
        experienciEdu.setNumSeguimiento(rs.getInt("numSeguimiento"));
        experienciEdu.setNumTaller(rs.getInt("numTaller"));
        experienciEdu.setPorcentajeAutoevaluaciones(rs.getInt("porcentajeAutoevaluaciones"));
        experienciEdu.setPorcentajeBitacoras(rs.getInt("porcentajeBitacoras"));
        experienciEdu.setPorcentajeExamenes(rs.getInt("porcentajeExamenes"));
        experienciEdu.setPorcentajeReflexiones(rs.getInt("porcentajeReflexiones"));
        experienciEdu.setPorcentajeSeguimiento(rs.getInt("porcentajeSeguimientos"));
        experienciEdu.setPorcentajeTaller(rs.getInt("porcentajeTalleres"));
        experienciEdu.setNumUnidades(rs.getInt("numUnidades"));
        experienciEdu.setNumModulos(rs.getInt("numModulos"));
      }
    } catch (SQLException ex) {
      throw ex;
    } finally {
      this.close();
    }
    return experienciEdu;
  }
}

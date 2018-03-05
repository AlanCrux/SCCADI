package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOInscripcion;
import logica.dominio.Alumno;
import logica.dominio.Inscripcion;
import logica.dominio.Seccion;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOInscripcionImpl extends Conexion implements DAOInscripcion {

  @Override
  public List<Inscripcion> obtenerInscripciones() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Alumno> obtenerAlumnos(int nrc) throws SQLException {
    List<Alumno> alumnos = new ArrayList();
    String consulta = "select * from Alumno as T1 INNER JOIN Inscripcion as T2 ON T1.matricula = T2.matricula where nrc =?;";

    this.connection();
    
    try {
      PreparedStatement st = this.conn.prepareStatement(consulta);
      st.setInt(1, nrc);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        Alumno alumno = new Alumno();
        alumno.setMatricula(rs.getString("matricula"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setCorreo(rs.getString("correo"));
        alumno.setContactoEmergencia(rs.getString("contactoEmergencia"));
        alumno.setProgramaEducativo(rs.getString("programaEducativo"));
        alumno.setNumeroEmergencia(rs.getString("numeroEmergencia"));
        alumno.setFotografia(rs.getBlob("fotografia"));
        alumnos.add(alumno);
      }
      rs.close();
      st.close();
    } catch (Exception ex) {
      throw ex; 
    }
    return alumnos;
  }

  @Override
  public List<Seccion> obtenerSecciones(String matricula) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Inscripcion obtenerInscripciones(int folio) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean insertarInscripciones(Inscripcion inscripcion) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizarInscripciones(int folio) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarInscripciones(int folio) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

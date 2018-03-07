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
 * Esta clase implementa los métodos definidos en la interfaz DAOInscripcion
 * nos permite realizar inserciones, consultas, actualizaciones y eliminar datos de la tabla
 * Inscripcion en la base de datos.
 * @author Alan Yoset Garcia Cruz
 */
public class DAOInscripcionImpl extends Conexion implements DAOInscripcion {

  /**
   * Obtiene de la base de datos los alumnos inscritos a una sección. 
   * @param nrc el identificador de la sección.
   * @return la lista de alumnos inscritos. 
   * @throws Exception 
   */
  @Override
  public List<Alumno> obtenerAlumnos(int nrc) throws Exception {
    List<Alumno> alumnos = new ArrayList();
    String consulta = "select * from Alumno as T1 INNER JOIN Inscripcion as T2 ON T1.matricula = T2.matricula where nrc =?;";
    
    try {
      this.connection();
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
  public List<Inscripcion> obtenerInscripciones() throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Seccion> obtenerSecciones(String matricula) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Inscripcion obtenerInscripciones(int folio) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean insertarInscripciones(Inscripcion inscripcion) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizarInscripciones(int folio) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminarInscripciones(int folio) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  
    @Override
    public boolean eliminarInscripcionesPorMatricula(Alumno alumno) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("delete from Inscripcion where matricula = ?");
            st.setString(1, alumno.getMatricula());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            this.close();
        }
        return true;
    }



}

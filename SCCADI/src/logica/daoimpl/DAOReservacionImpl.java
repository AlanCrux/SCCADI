package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOReservacion;
import logica.dominio.Reservacion;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOReservacionImpl extends Conexion implements DAOReservacion {

  /**
   * Obtiene las reservaciones asociadas a un alumno en especifico en la BD.
   * @param matricula identificador del alumno 
   * @return lista con las reservaciones asociadas al alumno.
   * @throws Exception ocurre si hay un error en la consulta. 
   */
  @Override
  public List<Reservacion> obtenerReservacionesPorAlumno(String matricula) throws Exception {
    String query = "select * from reservacion where matricula = ?";
    List<Reservacion> reservaciones;

    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setString(1, matricula);
      ResultSet rs = st.executeQuery();
      reservaciones = new ArrayList();
      while (rs.next()) {
        Reservacion reservacion = new Reservacion();
        reservacion.setIdReservacion(rs.getInt("idReservacion"));
        reservacion.setMatricula(rs.getString("matricula"));
        reservacion.setIdActividadAsignada(rs.getInt("idActividadAsignada"));
        reservaciones.add(reservacion);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return reservaciones;
  }

  /**
   * Inserta una reservación en la base de datos. 
   * @param reservacion la reservación que se quiere insertar.
   * @return true si se inserto correctamente.
   * @throws Exception ocurre si hay un error en la consulta. 
   */
  @Override
  public boolean insertarReservacion(Reservacion reservacion) throws Exception {
    String query = "INSERT INTO reservacion(matricula, idActividadAsignada) values(?,?);";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setString(1, reservacion.getMatricula());
      st.setInt(2, reservacion.getIdActividadAsignada());
      st.executeUpdate();
      return true;
    } catch (SQLException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        this.close();
      } catch (Exception e) {
        e.printStackTrace();
        throw e;
      }
    }
  }

}

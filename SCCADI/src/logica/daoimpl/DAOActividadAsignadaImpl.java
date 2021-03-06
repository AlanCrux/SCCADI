package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.dao.DAOActividadAsignada;
import logica.dominio.ActividadAsignada;
import utilerias.DateConvertUtils;

/**
 *
 * @author Esmeralda Jiménez Ramos
 */
public class DAOActividadAsignadaImpl extends Conexion implements DAOActividadAsignada {

  /**
   * Metodo que obtiene las actividades asignadas a un asesor
   *
   * @param noPersonal clave unica del asesor del tipo entero
   * @return Una lista de todas las actividades asignadas al asesor
   * @throws Exception
   */
  public List<ActividadAsignada> obtenerActividadAsignadaAlAsesor(int noPersonal) throws Exception {
    List<ActividadAsignada> actividades = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select actividadprogramada.nombre, sala.nombreSala, actividadasignada.fecha, "
          + "actividadasignada.hora from sala join asesor join actividadprogramada "
          + "join actividadasignada where actividadasignada.noPersonal = ? "
          + "and actividadasignada.idActividadProgramada = actividadprogramada.idActividadProgramada "
          + "and asesor.noPersonal = actividadasignada.noPersonal and sala.idSala = actividadasignada.idSala;");
      st.setInt(1, noPersonal);
      ResultSet rs = st.executeQuery();
      actividades = new ArrayList();
      while (rs.next()) {
        ActividadAsignada actividad = new ActividadAsignada();
        actividad.setNombre(rs.getString("nombre"));
        actividad.setNombreSala(rs.getString("nombreSala"));
        actividad.setFecha(rs.getDate("fecha"));
        actividad.setHora(rs.getTime("hora"));
        actividades.add(actividad);
      }
    } catch (Exception e) {
      throw e;
    }
    return actividades;
  }

  /**
   * Metodo que obtiene de la base de datos las actividades asignadas a un asesor de una experiencia
   * educativa
   *
   * @param noPersonal clave unica del asesor del tipo entero
   * @param idExperiencia clave unica de la experiencia educativa de tipo entero
   * @return Una lista de todas las actividades asignadas al asesor
   * @throws Exception
   */
  public List<ActividadAsignada> obtenerActividadesPorExperiencia(int noPersonal, int idExperiencia) throws Exception {
    List<ActividadAsignada> actividades = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select actividadprogramada.nombre, sala.nombreSala, actividadasignada.fecha, "
          + "actividadasignada.hora, actividadasignada.idActividadAsignada from experienciaeducativa join sala join "
          + "asesor join actividadprogramada join actividadasignada where "
          + "actividadasignada.noPersonal = ? and "
          + "actividadasignada.idActividadProgramada = "
          + "actividadprogramada.idActividadProgramada and asesor.noPersonal "
          + "= actividadasignada.noPersonal and sala.idSala = "
          + "actividadasignada.idSala and experienciaeducativa.idExperiencia = "
          + "actividadprogramada.idExperiencia and actividadprogramada.idExperiencia = ?;");
      st.setInt(1, noPersonal);
      st.setInt(2, idExperiencia);
      ResultSet rs = st.executeQuery();
      actividades = new ArrayList();
      while (rs.next()) {
        ActividadAsignada actividad = new ActividadAsignada();
        actividad.setIdActividadAsignada(rs.getInt("idActividadAsignada"));
        actividad.setNombre(rs.getString("nombre"));
        actividad.setNombreSala(rs.getString("nombreSala"));
        actividad.setFecha(rs.getDate("fecha"));
        actividad.setHora(rs.getTime("hora"));
        actividades.add(actividad);
      }
    } catch (Exception e) {
      throw e;
    }
    return actividades;

  }

  /**
   * Metodo que obtiene una lista de las actividades asignadas a un asesor dentro de un rango de
   * fechas
   *
   * @param noPersonal clave unica del asesor del tipo entero
   * @param idExperiencia clave unica de la experiencia educativa de tipo entero
   * @param fechaMin fecha minima del rango
   * @param fechaMax fecha maxima del rango
   * @return lista de las actividades asignadas a un asesor dentro de un rango de fechas
   * @throws Exception
   */
  public List<ActividadAsignada> obtenerActividadesPorFecha(int noPersonal, int idExperiencia, Date fechaMin, Date fechaMax) throws Exception {
    List<ActividadAsignada> actividades = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select actividadprogramada.nombre, sala.nombreSala, "
          + "actividadasignada.fecha, actividadasignada.hora from"
          + " experienciaeducativa join sala join asesor join"
          + " actividadprogramada join actividadasignada where"
          + " actividadasignada.noPersonal = ? and "
          + "actividadasignada.idActividadProgramada = "
          + "actividadprogramada.idActividadProgramada and"
          + " asesor.noPersonal = actividadasignada.noPersonal and "
          + "sala.idSala = actividadasignada.idSala and "
          + "experienciaeducativa.idExperiencia = "
          + "actividadprogramada.idExperiencia and "
          + "actividadprogramada.idExperiencia = ? and "
          + "actividadasignada.fecha between '" + fechaMin + "' and '" + fechaMax + "';");
      st.setInt(1, noPersonal);
      st.setInt(2, idExperiencia);
      ResultSet rs = st.executeQuery();
      actividades = new ArrayList();
      while (rs.next()) {
        ActividadAsignada actividad = new ActividadAsignada();
        actividad.setNombre(rs.getString("nombre"));
        actividad.setNombreSala(rs.getString("nombreSala"));
        actividad.setFecha(rs.getDate("fecha"));
        actividad.setHora(rs.getTime("hora"));
        actividades.add(actividad);
      }
    } catch (Exception e) {
      throw e;
    }

    return actividades;

  }

  /**
   * Obtiene todas las actividades asignadas de la base de datos
   *
   * @return Lista de todas las actividades registradas en la base de datos
   * @throws Exception
   */
  @Override
  public List<ActividadAsignada> obtenerActividadesAsignadas() throws Exception {
    List<ActividadAsignada> actividades = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select actividadprogramada.nombre 'Actividad', asesor.nombre, "
          + "actividadasignada.fecha, actividadasignada.hora, "
          + "sala.nombreSala, actividadasignada.cupoMaximo from "
          + "actividadprogramada join asesor join actividadasignada join "
          + "sala where  actividadasignada.noPersonal = asesor.noPersonal "
          + "and actividadasignada.idActividadProgramada = "
          + "actividadprogramada.idActividadProgramada and sala.idSala = "
          + "actividadasignada.idSala order by actividadasignada.fecha ASC;");
      ResultSet rs = st.executeQuery();
      actividades = new ArrayList();
      while (rs.next()) {
        ActividadAsignada actividad = new ActividadAsignada();
        actividad.setNombre(rs.getString("Actividad"));
        actividad.setNombreSala(rs.getString("nombreSala"));
        actividad.setNombreAsesor(rs.getString("nombre"));
        actividad.setFecha(rs.getDate("fecha"));
        actividad.setHora(rs.getTime("hora"));
        actividad.setCupoMaximo(rs.getInt("cupoMaximo"));
        actividades.add(actividad);
      }
    } catch (Exception e) {
      throw e;
    }
    return actividades;
  }

  /**
   * Metodo que obtiene las actividades asignadas a todos los asesores en una experiencia educativa
   *
   * @param idExperiencia clave unica de la experiencia educativa de tipo entero
   * @return Lista de las actividades asignadas a todos los asesores en una experiencia educativa
   * @throws Exception
   */
  public List<ActividadAsignada> obtenerActividadesPorExperiencia(int idExperiencia) throws Exception {
    List<ActividadAsignada> actividades = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select "
          + "actividadprogramada.nombre 'Actividad', asesor.nombre, "
          + "actividadasignada.fecha, actividadasignada.hora, "
          + "sala.nombreSala, actividadasignada.cupoMaximo, actividadasignada.idActividadAsignada from "
          + "actividadprogramada join asesor join actividadasignada"
          + " join sala where  actividadasignada.noPersonal ="
          + " asesor.noPersonal and actividadasignada.idActividadProgramada "
          + "= actividadprogramada.idActividadProgramada and "
          + "sala.idSala = actividadasignada.idSala and "
          + "actividadprogramada.idExperiencia = ? order "
          + "by actividadasignada.fecha ASC;");
      st.setInt(1, idExperiencia);

      ResultSet rs = st.executeQuery();
      actividades = new ArrayList();
      while (rs.next()) {
        ActividadAsignada actividad = new ActividadAsignada();
        actividad.setIdActividadAsignada(rs.getInt("idActividadAsignada"));
        actividad.setNombre(rs.getString("Actividad"));
        actividad.setNombreSala(rs.getString("nombreSala"));
        actividad.setNombreAsesor(rs.getString("nombre"));
        actividad.setFecha(rs.getDate("fecha"));
        actividad.setHora(rs.getTime("hora"));
        actividad.setCupoMaximo(rs.getInt("cupoMaximo"));
        actividades.add(actividad);
      }
    } catch (Exception e) {
      throw e;
    }
    return actividades;

  }

  /**
   * Obtiene las actividades asignadas a todos lo asesores en un rango de fechas
   *
   * @param idExperiencia clave unica de la experiencia educativa de tipo entero
   * @param fechaMin fecha minima del rango
   * @param fechaMax fecha maxima del rango
   * @return Lista con las actividades asignadas a todos lo asesores en un rango de fechas
   * @throws Exception
   */
  public List<ActividadAsignada> obtenerActividadesPorFecha(int idExperiencia, Date fechaMin, Date fechaMax) throws Exception {
    List<ActividadAsignada> actividades = null;
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement("select "
          + "actividadprogramada.nombre 'Actividad', asesor.nombre, "
          + "actividadasignada.fecha, actividadasignada.hora, "
          + "sala.nombreSala, actividadasignada.cupoMaximo from "
          + "actividadprogramada join asesor join actividadasignada "
          + "join sala where  actividadasignada.noPersonal = "
          + "asesor.noPersonal and actividadasignada.idActividadProgramada"
          + " = actividadprogramada.idActividadProgramada and "
          + "sala.idSala = actividadasignada.idSala and "
          + "actividadprogramada.idExperiencia = ? and "
          + "actividadasignada.fecha between '" + fechaMin + "' and "
          + "'" + fechaMax + "' order by actividadasignada.fecha ASC;");
      st.setInt(1, idExperiencia);
      ResultSet rs = st.executeQuery();
      actividades = new ArrayList();
      while (rs.next()) {
        ActividadAsignada actividad = new ActividadAsignada();
        actividad.setNombre(rs.getString("Actividad"));
        actividad.setNombreSala(rs.getString("nombreSala"));
        actividad.setNombreAsesor(rs.getString("nombre"));
        actividad.setFecha(rs.getDate("fecha"));
        actividad.setHora(rs.getTime("hora"));
        actividad.setCupoMaximo(rs.getInt("cupoMaximo"));
        actividades.add(actividad);
      }
    } catch (Exception e) {
      throw e;
    }

    return actividades;

  }

  @Override
  public ActividadAsignada obtenerActividadAsignada(int idActividadAsignada) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Metodo que guarda un registro nuevo de un actividad asignada en la base 
   * de datos
   * @param actividadAsignada Objeto del tipo Actividad asignada
   * @return True si el registro fue correcto
   * @throws Exception 
   */
  @Override
  public boolean insertarActividadAsignada(ActividadAsignada actividadAsignada) throws Exception {
        String query = " insert into actividadAsignada (idActividadProgramada, "
                + "noPersonal, idSala, cupoMaximo, fecha, hora) values "
                + "(?, ?, ?, ?, ?,?);";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setInt(1, actividadAsignada.getIdActividadProgramda());
      
      st.setInt(2, actividadAsignada.getNoPersonal());
      st.setInt(3, actividadAsignada.getIdSala());
      st.setInt(4, actividadAsignada.getCupoMaximo());
      st.setDate(5, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(actividadAsignada.getFecha())));
      st.setTime(6, actividadAsignada.getHora());
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

  @Override
  public boolean actualizarActividadAsignada(ActividadAsignada actividad) throws Exception {
    String query = "UPDATE ActividadAsignada set cupoMaximo = ? where idActividadAsignada = ?";
    try {
      this.connection();
      PreparedStatement st = this.conn.prepareStatement(query);
      st.setInt(1, actividad.getCupoMaximo());
      st.setInt(2, actividad.getIdActividadAsignada());
      st.executeUpdate();
      st.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false; 
  }

  @Override
  public boolean eliminarActividadAsignada(int idActividadAsignada) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}

package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOActividadProgramada;
import logica.dominio.ActividadProgramada;
import utilerias.DateConvertUtils;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class DAOActividadProgramadaImpl extends Conexion implements DAOActividadProgramada {

    /**
     * Obtiene todas las actividades programadas registradas en la base de
     * datos.
     *
     * @return List con las actividades recuperadas de la base de datos.
     * @throws Exception Puede lanzar una excepción si hay error de conexión con
     * la BD.
     */
    @Override
    public List<ActividadProgramada> obtenerActividadesProgramadas() throws Exception {
        List<ActividadProgramada> actividades;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from actividadprogramada");
            ResultSet rs = st.executeQuery();
            actividades = new ArrayList();
            while (rs.next()) {
                ActividadProgramada actividad = new ActividadProgramada();
                actividad.setIdActividadProgramada(rs.getInt("idActividadProgramada"));
                actividad.setNombre(rs.getString("nombre"));
                actividad.setFechaInicio(rs.getDate("fechaInicio"));
                actividad.setFechaFin(rs.getDate("fechaFin"));
                actividad.setModulo(rs.getInt("modulo"));
                actividad.setUnidad(rs.getInt("unidad"));
                actividad.setIdExperiencia(rs.getInt("idExperiencia"));
                actividades.add(actividad);
            }
        } catch (Exception e) {
            throw e;
        }
        return actividades;
    }

    @Override
    public ActividadProgramada obtenerActividadProgramada(int idActividadProgramada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Permite insertar una actividadprogramada en la base de datos.
     *
     * @param actividadProgramada La actividad a insertar.
     * @return true si la actividad se inserto correctamente.
     * @throws Exception Puede lanzar una excepción si hay error de conexión con
     * la BD.
     */
    @Override
    public boolean insertarActividadProgramada(ActividadProgramada actividadProgramada) throws Exception {
        String query = "INSERT INTO actividadprogramada(nombre,fechaInicio,fechaFin,modulo,unidad,idExperiencia) values(?,?,?,?,?,?)";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, actividadProgramada.getNombre());
            st.setDate(2, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(actividadProgramada.getFechaInicio())));
            st.setDate(3, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(actividadProgramada.getFechaFin())));
            st.setInt(4, actividadProgramada.getModulo());
            st.setInt(5, actividadProgramada.getUnidad());
            st.setInt(6, actividadProgramada.getIdExperiencia());
            st.executeUpdate();
            return true;
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

    /**
     * Permite actualizar los datos de una actividadprogramada en la base de
     * datos.
     *
     * @param actividadProgramada la actividad programada que se quiere
     * actualizar.
     * @return true si los datos de la actividad se actualizaron correctamente.
     * @throws Exception Puede lanzar una excepción si hay error de conexión con
     * la BD.
     */
    @Override
    public boolean actualizarActividadProgramada(ActividadProgramada actividadProgramada) throws Exception {
        String query = "UPDATE actividadprogramada SET nombre = ?, fechaInicio=?, fechaFin = ?,"
                + " modulo = ?, unidad = ?, idExperiencia = ? where idActividadProgramada = ?";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, actividadProgramada.getNombre());
            st.setDate(2, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(actividadProgramada.getFechaInicio())));
            st.setDate(3, java.sql.Date.valueOf(DateConvertUtils.asLocalDate(actividadProgramada.getFechaFin())));
            st.setInt(4, actividadProgramada.getModulo());
            st.setInt(5, actividadProgramada.getUnidad());
            st.setInt(6, actividadProgramada.getIdExperiencia());
            st.setInt(7, actividadProgramada.getIdActividadProgramada());
            st.executeUpdate();
            return true;
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

    /**
     * Permite eliminar una actividadprogramada de la base de datos.
     *
     * @param idActividadProgramada el id de la actividad por eliminar.
     * @return true si la actividad se elimino correctamente.
     * @throws Exception Puede lanzar una excepción si hay error de conexión con
     * la BD.
     */
    @Override
    public boolean eliminarActividadProgramada(int idActividadProgramada) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM actividadprogramada where idActividadProgramada = ?");
            st.setInt(1, idActividadProgramada);
            st.executeUpdate();
            return true;
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

    /**
     * Metodo que obtiene las actividaddes programadas relacionadas a una
     * experiencia educativa
     *
     * @param idExperiencia identificador de la experiencia educativa
     * @return Lista de actividades programadas
     * @throws Exception
     */
    public List<ActividadProgramada> obtenerActividadesProgramadasPorEE(int idExperiencia) throws Exception {
        List<ActividadProgramada> actividades;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from actividadprogramada where idExperiencia = ?");
            st.setInt(1, idExperiencia);
            ResultSet rs = st.executeQuery();
            actividades = new ArrayList();
            while (rs.next()) {
                ActividadProgramada actividad = new ActividadProgramada();
                actividad.setIdActividadProgramada(rs.getInt("idActividadProgramada"));
                actividad.setNombre(rs.getString("nombre"));
                actividad.setFechaInicio(rs.getDate("fechaInicio"));
                actividad.setFechaFin(rs.getDate("fechaFin"));
                actividad.setModulo(rs.getInt("modulo"));
                actividad.setUnidad(rs.getInt("unidad"));
                actividad.setIdExperiencia(rs.getInt("idExperiencia"));
                actividades.add(actividad);
            }
        } catch (Exception e) {
            throw e;
        }
        return actividades;
    }

    public List<ActividadProgramada> obtenerActividadesProgramadasPorMes(int mes) throws Exception {
        List<ActividadProgramada> actividades;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select actividadprogramada.idActividadProgramada, actividadprogramada.nombre, actividadprogramada.fechaInicio, "
                    + "actividadprogramada.fechaFin, actividadprogramada.modulo, actividadprogramada.unidad, experienciaeducativa.nombre nombreExp "
                    + "from actividadprogramada join experienciaeducativa where actividadprogramada.idExperiencia = experienciaeducativa.idExperiencia "
                    + "and Month(fechaInicio)= ? ;");
            st.setInt(1, mes);
            ResultSet rs = st.executeQuery();
            actividades = new ArrayList();
            while (rs.next()) {
                
                ActividadProgramada actividad = new ActividadProgramada();
                actividad.setIdActividadProgramada(rs.getInt("idActividadProgramada"));
                actividad.setNombre(rs.getString("nombre"));
                actividad.setFechaInicio(rs.getDate("fechaInicio"));
                actividad.setFechaFin(rs.getDate("fechaFin"));
                actividad.setModulo(rs.getInt("modulo"));
                actividad.setUnidad(rs.getInt("unidad"));
                actividad.setExperienciaEducativa(rs.getString("nombreExp"));
                actividades.add(actividad);
            }
        } catch (Exception e) {
            throw e;
        }
        return actividades;
    }
}

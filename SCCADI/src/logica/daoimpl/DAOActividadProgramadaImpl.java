package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOActividadProgramada;
import logica.dominio.ActividadProgramada;

/**
 *
 * @author Esmeralda
 */
public class DAOActividadProgramadaImpl extends Conexion implements DAOActividadProgramada {

    /**
     * Metodo que obtiene una lista con las actividades programadas
     * @return Lista de tipo actividad programada
     * @throws Exception 
     */
    @Override
    public List<ActividadProgramada> obtenerActividadProgramada() throws Exception {
        List<ActividadProgramada> actividades = null;
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
        } catch (SQLException e) {
            throw e;
        }
        return actividades;
    }

    @Override
    public ActividadProgramada obtenerActividadProgramada(int idActividadProgramada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarActividadProgramada(ActividadProgramada actividadprogramada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarActividadProgramada(int idActividadProgramada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarActividadProgramada(int idActividadProgramada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

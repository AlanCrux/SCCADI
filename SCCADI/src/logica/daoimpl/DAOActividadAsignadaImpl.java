/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOActividadAsignada;
import logica.dominio.ActividadAsignada;

/**
 *
 * @author Esmeralda
 */
public class DAOActividadAsignadaImpl extends Conexion implements DAOActividadAsignada{

    @Override
    public List<ActividadAsignada> obtenerActividadAsignada() throws Exception {
        List<ActividadAsignada> actividades = null;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from actividadasignada");
            ResultSet rs = st.executeQuery();
            actividades = new ArrayList();
            while (rs.next()) {
                ActividadAsignada actividad = new ActividadAsignada();
                    actividad.setIdActividadAsignada(rs.getInt("idActividadAsignada"));
                    actividad.setIdActividadProgramda(rs.getInt("idActividadProgramada"));
                    actividad.setNoPersonal(rs.getInt("noPersonal"));
                    actividad.setCupoMaximo(rs.getInt("cupoMaximo"));
                    actividad.setFecha(rs.getDate("fecha"));
                    actividad.setHora(rs.getTime("hora"));
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            throw e;
        }
        return actividades;
    }

    @Override
    public ActividadAsignada obtenerActividadAsignada(int idActividadAsignada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarActividadAsignada(ActividadAsignada actividadAsignada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarActividadAsignada(int idActividadAsignada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarActividadAsignada(int idActividadAsignada) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
}

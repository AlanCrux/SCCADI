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
import java.util.List;
import logica.dao.DAOAsesor;


import logica.dominio.Asesor;

/**
 *
 * @author Esmeralda
 */
public class DAOAsesorImpl extends Conexion implements DAOAsesor {

    @Override
    public List<Asesor> obtenerAsesores() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Asesor obtenerAsesor(int noPersonal) throws Exception {
        Asesor asesor = null;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from asesor where noPersonal =?");
            st.setInt(1, noPersonal);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                asesor = new Asesor();
                asesor.setNoPersonal(noPersonal);
                asesor.setNombre(rs.getString("nombre"));
                asesor.setCorreo(rs.getString("correo"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {

        }
        return asesor;
    }

    @Override
    public boolean insertarAsesor(Asesor asesor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarAsesor(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarAsesor(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

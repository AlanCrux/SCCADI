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
import logica.dao.DAOCoordinador;
import logica.dominio.Coordinador;

/**
 *
 * @author Esmeralda
 */
public class DAOCoordinadorImpl extends Conexion implements DAOCoordinador{

    @Override
    public List<Coordinador> obtenerCoordinadores() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coordinador obtenerCoordinador(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarCoordinador(Coordinador coordinador) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarCoordinador(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarCoordinador(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coordinador obtenerCoordinador(int noPersonal, String contrasena) throws Exception {
                Coordinador coordinador = null;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from coordinador where noPersonal =? and contrasena = ?");
            st.setInt(1, noPersonal);
            st.setString(2, contrasena);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                coordinador = new Coordinador();
                coordinador.setNoPersonal(noPersonal);
                coordinador.setNombre(rs.getString("nombre"));
                coordinador.setCorreo(rs.getString("correo"));
                coordinador.setContrasena(rs.getString("contrasena"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {

        }
        return coordinador;
    }
    
}

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
import logica.dao.DAORecepcionista;
import logica.dominio.Recepcionista;

/**
 *
 * @author Esmeralda
 */
public class DAORecepcionistaImpl extends Conexion implements DAORecepcionista {

    @Override
    public List<Recepcionista> obtenerRecepcionistas() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recepcionista obtenerRecepcionista(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarRecepcionista(Recepcionista recepcionista) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarRecepcionista(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarRecepcionista(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recepcionista obtenerRecepcionista(int noPersonal, String contrasena) throws Exception {
        Recepcionista recepcionista = null;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from recepcionista where noPersonal =? and contrasena = ?");
            st.setInt(1, noPersonal);
            st.setString(2, contrasena);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                recepcionista = new Recepcionista();
                recepcionista.setNoPersonal(noPersonal);
                recepcionista.setNombre(rs.getString("nombre"));
                recepcionista.setCorreo(rs.getString("correo"));
                recepcionista.setContrasena(rs.getString("contrasena"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {

        }
        return recepcionista;
    }
}



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
import logica.dao.DAORecepcionista;
import logica.dominio.Recepcionista;

/**
 *
 * @author Esmeralda
 */
public class DAORecepcionistaImpl extends Conexion implements DAORecepcionista {

    @Override
    public List<Recepcionista> obtenerRecepcionistas() throws Exception {
        List<Recepcionista> listaRecepcionista = null;

        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from recepcionista");
            ResultSet rs = st.executeQuery();
            listaRecepcionista = new ArrayList();
            while (rs.next()) {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setNoPersonal(rs.getInt("noPersonal"));
                recepcionista.setNombre(rs.getString("nombre"));
                recepcionista.setCorreo(rs.getString("correo"));
                recepcionista.setContrasena(rs.getString("contrasena"));
                recepcionista.setTipo("recepcionista");
                listaRecepcionista.add(recepcionista);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaRecepcionista;
    }

    @Override
    public Recepcionista obtenerRecepcionista(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarRecepcionista(Recepcionista recepcionista) throws Exception {
        String query = "INSERT INTO recepcionista(noPersonal, nombre, correo, contrasena) "
                + "values(?,?,?,?);";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, recepcionista.getNoPersonal());
            st.setString(2, recepcionista.getNombre());
            st.setString(3, recepcionista.getCorreo());
            st.setString(4, recepcionista.getContrasena());
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

    @Override
    public boolean actualizarRecepcionista(Recepcionista recepcionista) throws Exception {
        
        String query = "UPDATE recepcionista SET nombre = ?, correo =?, contrasena = ?"
                + "WHERE noPersonal = ?";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, recepcionista.getNombre());
            st.setString(2, recepcionista.getCorreo());
            st.setString(3, recepcionista.getContrasena());
            st.setInt(4, recepcionista.getNoPersonal());
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

    @Override
    public boolean eliminarRecepcionista(int noPersonal) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM recepcionista where noPersonal = ?");
            st.setInt(1, noPersonal);
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

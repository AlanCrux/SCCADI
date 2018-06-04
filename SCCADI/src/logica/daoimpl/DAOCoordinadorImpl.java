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
import logica.dao.DAOCoordinador;
import logica.dominio.Coordinador;

/**
 *
 * @author Esmeralda
 */
public class DAOCoordinadorImpl extends Conexion implements DAOCoordinador {

    @Override
    public List<Coordinador> obtenerCoordinadores() throws Exception {
        List<Coordinador> listaCoordinador = null;

        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from coordinador");
            ResultSet rs = st.executeQuery();
            listaCoordinador = new ArrayList();
            while (rs.next()) {
                Coordinador coordinador = new Coordinador();
                coordinador.setNoPersonal(rs.getInt("noPersonal"));
                coordinador.setNombre(rs.getString("nombre"));
                coordinador.setCorreo(rs.getString("correo"));
                coordinador.setContrasena(rs.getString("contrasena"));
                coordinador.setTipo("coordinador");
                listaCoordinador.add(coordinador);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaCoordinador;
    }

    @Override
    public Coordinador obtenerCoordinador(int noPersonal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarCoordinador(Coordinador coordinador) throws Exception {
        String query = "INSERT INTO coordinador (noPersonal, nombre, correo, contrasena) "
                + "values(?,?,?,?);";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, coordinador.getNoPersonal());
            st.setString(2, coordinador.getNombre());
            st.setString(3, coordinador.getCorreo());
            st.setString(4, coordinador.getContrasena());
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
    public boolean actualizarCoordinador(Coordinador coordinador) throws Exception {
                String query = "UPDATE coordinador SET nombre = ?, correo =?, contrasena = ?"
                + "WHERE noPersonal = ?";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, coordinador.getNombre());
            st.setString(2, coordinador.getCorreo());
            st.setString(3, coordinador.getContrasena());
            st.setInt(4, coordinador.getNoPersonal());
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
    public boolean eliminarCoordinador(int noPersonal) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM coordinador where noPersonal = ?");
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

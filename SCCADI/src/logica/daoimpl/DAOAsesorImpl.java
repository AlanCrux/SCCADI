package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOAsesor;

import logica.dominio.Asesor;

/**
 *
 * @author Esmeralda Jiménez Ramos
 */
public class DAOAsesorImpl extends Conexion implements DAOAsesor {

    /**
     * Metodo que obtiene una lista de todos los asesores registrados
     *
     * @return Lista de tipo Asesores
     * @throws Exception
     */
    @Override
    public List<Asesor> obtenerAsesores() throws Exception {
        List<Asesor> listaAsesor = null;

        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from asesor");
            ResultSet rs = st.executeQuery();
            listaAsesor = new ArrayList();
            while (rs.next()) {
                Asesor asesor = new Asesor();
                asesor.setNoPersonal(rs.getInt("noPersonal"));
                asesor.setNombre(rs.getString("nombre"));
                asesor.setCorreo(rs.getString("correo"));
                asesor.setContrasena(rs.getString("contrasena"));
                asesor.setTipo("asesor");
                listaAsesor.add(asesor);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaAsesor;
    }

    /**
     * Recupera un asesor de la base de datos.
     *
     * @param noPersonal número personal del asesor que se quiere recuperar.
     * @return regresa un objeto de tipo Asesor.
     * @throws Exception Puede lanzar una excepción si hay error de conexión con
     * la BD.
     */
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
        String query = "INSERT INTO asesor(noPersonal, nombre, correo, contrasena) "
                + "values(?,?,?,?);";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, asesor.getNoPersonal());
            st.setString(2, asesor.getNombre());
            st.setString(3, asesor.getCorreo());
            st.setString(4, asesor.getContrasena());
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
    public boolean actualizarAsesor(Asesor asesor) throws Exception {
        String query = "UPDATE asesor SET nombre = ?, correo =?, contrasena = ?"
                + "WHERE noPersonal = ?";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, asesor.getNombre());
            st.setString(2, asesor.getCorreo());
            st.setString(3, asesor.getContrasena());
            st.setInt(4, asesor.getNoPersonal());
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
    public boolean eliminarAsesor(int noPersonal) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM asesor where noPersonal = ?");
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

    public Asesor obtenerAsesor(int noPersonal, String contrasena) throws Exception {
        Asesor asesor = null;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from asesor where noPersonal =? and contrasena = ?");
            st.setInt(1, noPersonal);
            st.setString(2, contrasena);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                asesor = new Asesor();
                asesor.setNoPersonal(noPersonal);
                asesor.setNombre(rs.getString("nombre"));
                asesor.setCorreo(rs.getString("correo"));
                asesor.setContrasena(rs.getString("contrasena"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {

        }
        return asesor;
    }

}

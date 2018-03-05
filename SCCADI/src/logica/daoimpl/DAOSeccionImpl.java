package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOSeccion;
import logica.dominio.Seccion;

/**
 *
 * @author Alan Yoset García Cruz
 */
public class DAOSeccionImpl extends Conexion implements DAOSeccion{

    @Override
    public List<Seccion> obtenerSecciones(int noPersonal) throws SQLException {
        List<Seccion> secciones = null;
        this.connection();
        try {
            PreparedStatement st = this.conn.prepareStatement("select * from seccion where noPersonal=?");
            st.setInt(1, noPersonal);
            secciones = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Seccion seccion = new Seccion();
                seccion.setNrc(rs.getInt("nrc"));
                seccion.setIdExperiencia(rs.getInt("idExperiencia"));
                seccion.setNoPersonal(rs.getInt("noPersonal"));
                seccion.setPeriodo(rs.getString("periodo"));
                secciones.add(seccion);
            }
            rs.close();
        } catch (SQLException e) {
            throw e; 
        } finally {
            this.close();
        }
        return secciones;
    }

    @Override
    public Seccion obtenerSeccion(String nrc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarSeccion(Seccion seccion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarSeccion(String nrc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarSeccion(String nrc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

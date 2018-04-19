package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.dao.DAOSala;
import logica.dominio.Sala;

/**
 *
 * @author Esmeralda
 */
public class DAOSalaImpl extends Conexion implements DAOSala {

    /**
     * Metodo que obtiene una lista de todas las salas registradas 
     * @return Lista de tipo Sala
     * @throws Exception 
     */
    @Override
    public List<Sala> obtenerSalas() throws Exception {
        List<Sala> salas = null;

        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from sala");
            ResultSet rs = st.executeQuery();
            salas = new ArrayList();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setIdSala(rs.getInt("idSala"));
                sala.setCupo(rs.getInt("cupo"));
                sala.setNombre(rs.getString("nombre"));
                salas.add(sala);
            }
        } catch (SQLException e) {
            throw e;
        }
        return salas;
    }

    @Override
    public Sala obtenerSala(int idSala) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarSala(Sala sala) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarSala(int Sala) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarSala(int idSala) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

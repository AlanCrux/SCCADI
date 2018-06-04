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
 * @author Esmeralda Jiménez Ramos
 */
public class DAOSalaImpl extends Conexion implements DAOSala {

    /**
     * Metodo que obtiene una lista de todas las salas registradas
     *
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
                sala.setNombre(rs.getString("nombreSala"));
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
        String query = "INSERT INTO sala (nombreSala, cupo) VALUES (?,?)";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, sala.getNombre());
            st.setInt(2, sala.getCupo());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
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
    public boolean actualizarSala(Sala sala) throws Exception {
        String query = "UPDATE sala SET nombreSala = ?, cupo = ? WHERE idSala = ?";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, sala.getNombre());
            st.setInt(2, sala.getCupo());
            st.setInt(3, sala.getIdSala());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
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
    public boolean eliminarSala(int idSala) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM sala where idSala = ?");
            st.setInt(1, idSala);
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

}

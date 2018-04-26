package logica.daoimpl;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.dao.DAOProgreso;
import logica.dominio.Progreso;

/**
 *
 * @author Alan Yoset García Cruz
 */
public class DAOProgresoImpl extends Conexion implements DAOProgreso {

    /**
     * Obtiene de la base de datos el progreso de un alumno para una inscripción
     * en especifico. 
     * @param folioInscripcion identificador de la inscripción
     * @return el progreso del alumno 
     * @throws Exception si se pierde la conexión con la base de datos
     */
    @Override
    public Progreso obtenerProgreso(int folioInscripcion) throws Exception {
        Progreso progreso = null;
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("select * from Progreso where folioInscripcion =?");
            st.setInt(1, folioInscripcion);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                progreso = new Progreso();
                progreso.setIdProgreso(rs.getInt("idProgreso"));
                progreso.setNumBitacoras(rs.getInt("numBitacoras"));
                progreso.setNumReflexiones(rs.getInt("numReflexiones"));
                progreso.setNumSeguimiento(rs.getInt("numSeguimiento"));
                progreso.setNumAutoevaluaciones(rs.getInt("numAutoevaluaciones"));
                progreso.setFolioInscripcion(rs.getInt("folioInscripcion"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        }
        return progreso;
    }

    /**
     * Inserta en la BD el progreso de un alumno para una inscripción en especifico. 
     * @param progreso el progreso que se quiere insertar.
     * @return true si se inserta correctamente
     * @throws Exception si se pierde la conexión con la BD.
     */
    @Override
    public boolean insertarProgreso(Progreso progreso) throws Exception {
        String query = "INSERT INTO Progreso(numBitacoras, numAutoevaluaciones, numReflexiones, numSeguimiento, "
                + "folioInscripcion) values(?,?,?,?,?);";
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, progreso.getNumBitacoras());
            st.setInt(2, progreso.getNumAutoevaluaciones());
            st.setInt(3, progreso.getNumReflexiones());
            st.setInt(4, progreso.getNumSeguimiento());
            st.setInt(5, progreso.getFolioInscripcion());
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

    /**
     * Actualiza un progreso en especifico en la BD
     * @param progreso progreso que se quiere actualizar
     * @return true si se actualiza correctamente
     * @throws Exception si se pierde la conexión con la BD
     */
    @Override
    public boolean actualizarProgreso(Progreso progreso) throws Exception {
        try {
            this.connection();
            PreparedStatement st = this.conn.prepareStatement("update Progreso set numBitacoras = ?, "
                    + "numAutoevaluaciones = ?, numReflexiones = ?, numSeguimiento = ? "
                    + "where idProgreso = ?");
            st.setInt(1, progreso.getNumBitacoras());
            st.setInt(2, progreso.getNumAutoevaluaciones());
            st.setInt(3, progreso.getNumReflexiones());
            st.setInt(4, progreso.getNumSeguimiento());
            st.setInt(5, progreso.getIdProgreso());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return true;
    }

}

package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Asesor;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public interface DAOAsesor {
    public List<Asesor> obtenerAsesores() throws SQLException;
    public Asesor obtenerAsesor(int noPersonal) throws SQLException;
    public boolean insertarAsesor(Asesor asesor) throws SQLException;
    public boolean actualizarAsesor(int noPersonal) throws SQLException;
    public boolean eliminarAsesor(int noPersonal) throws SQLException;
}

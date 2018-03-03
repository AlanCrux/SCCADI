package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Seccion;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public interface DAOSeccion {
    public List<Seccion> obtenerSecciones() throws SQLException;
    public Seccion obtenerSeccion(String nrc) throws SQLException;
    public boolean insertarSeccion(Seccion seccion) throws SQLException;
    public boolean actualizarSeccion(String nrc) throws SQLException;
    public boolean eliminarSeccion(String nrc) throws SQLException;
}

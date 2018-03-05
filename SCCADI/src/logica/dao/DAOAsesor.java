package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Asesor;

/**
 * Define los métodos a implementar en el DAOAsesorImpl.
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public interface DAOAsesor {
    public List<Asesor> obtenerAsesores() throws Exception;
    public Asesor obtenerAsesor(int noPersonal) throws Exception;
    public boolean insertarAsesor(Asesor asesor) throws Exception;
    public boolean actualizarAsesor(int noPersonal) throws Exception;
    public boolean eliminarAsesor(int noPersonal) throws Exception;
}

package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Seccion;

/**
 * Define los métodos a implementar en el DAOSeccionImpl.
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 * 
 */
public interface DAOSeccion {
    public List<Seccion> obtenerSecciones(int noPersonal) throws Exception;
    public Seccion obtenerSeccion(String nrc) throws Exception;
    public boolean insertarSeccion(Seccion seccion) throws Exception;
    public boolean actualizarSeccion(String nrc) throws Exception;
    public boolean eliminarSeccion(String nrc) throws Exception;
}

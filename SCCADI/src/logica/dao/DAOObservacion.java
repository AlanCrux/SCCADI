package logica.dao;

import java.util.List;
import logica.dominio.Observacion;

/**
 *
 * @author alan
 */
public interface DAOObservacion {
    public List<Observacion> obtenerObservaciones(int folioInscripcion) throws Exception;
    public boolean insertarObservacion(Observacion observacion) throws Exception;
}

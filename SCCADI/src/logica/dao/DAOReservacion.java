package logica.dao;

import java.util.List;
import logica.dominio.Reservacion;

/**
 *
 * @author Alan
 */
public interface DAOReservacion {
   public List<Reservacion> obtenerReservacionesPorAlumno(String matricula) throws Exception;
   public boolean insertarReservacion(Reservacion reservacion) throws Exception;
}

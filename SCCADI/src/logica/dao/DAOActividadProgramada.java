package logica.dao;

import java.util.List;
import logica.dominio.ActividadProgramada;

/**
 *
 * @author Alan Yoset García Cruz
 */
public interface DAOActividadProgramada {
  public List<ActividadProgramada> obtenerActividadesProgramadas() throws Exception;
  public ActividadProgramada obtenerActividadProgramada(int idActividadProgramada) throws Exception;
  public boolean insertarActividadProgramada(ActividadProgramada actividadProgramada) throws Exception;
  public boolean actualizarActividadProgramada(ActividadProgramada actividadProgramada) throws Exception;
  public boolean eliminarActividadProgramada(int idActividadProgramada) throws Exception;
}

package logica.dao;

import java.util.List;
import logica.dominio.ExperienciaEducativa;

/**
 * Define los métodos a implementar en el DAOExperienciaEducativaImpl
 * @author Alan Yoset Garcia Cruz
 * @version 1.0
 */
public interface DAOExperienciaEducativa {
  public List<ExperienciaEducativa> obtenerExperiencias() throws Exception;
  public ExperienciaEducativa obtenerExperiencia(int idExperiencia) throws Exception;
  public boolean insertarExperiencia(ExperienciaEducativa experienciaEducativa) throws Exception;
  public boolean actualizarExperiencia(int idExperiencia) throws Exception;
  public boolean eliminarExperiencia(int idExperiencia) throws Exception;
}

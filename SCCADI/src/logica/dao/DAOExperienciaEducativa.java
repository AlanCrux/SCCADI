package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.ExperienciaEducativa;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public interface DAOExperienciaEducativa {
    public List<ExperienciaEducativa> obtenerExperiencias() throws SQLException;
    public ExperienciaEducativa obtenerExperiencia(int idExperiencia) throws SQLException;
    public boolean insertarExperiencia(ExperienciaEducativa experienciaEducativa) throws SQLException;
    public boolean actualizarExperiencia(int idExperiencia) throws SQLException;
    public boolean eliminarExperiencia(int idExperiencia) throws SQLException;
}

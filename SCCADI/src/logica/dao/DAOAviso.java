package logica.dao;

import java.util.List;
import logica.dominio.Aviso;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public interface DAOAviso {
  public List<Aviso> obtenerAvisos() throws Exception;
    public Aviso obtenerAviso(int idAviso) throws Exception;
    public boolean insertarAviso(Aviso aviso) throws Exception;
    public boolean actualizarAviso(Aviso aviso) throws Exception;
    public boolean eliminarAviso(int idAviso) throws Exception;
}

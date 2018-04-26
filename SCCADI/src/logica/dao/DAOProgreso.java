package logica.dao;

import logica.dominio.Progreso;

/**
 *
 * @author alancrux
 */
public interface DAOProgreso {
    public Progreso obtenerProgreso(int folioInscripcion) throws Exception;
    public boolean insertarProgreso(Progreso progreso) throws Exception;
    public boolean actualizarProgreso(Progreso progreso) throws Exception;
}

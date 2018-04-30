/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.Progreso;

/**
 *
 * @author Esmeralda
 */
public interface DAOProgreso {
    public List<Progreso> obtenerProgresos(int folioInscripcion) throws Exception;
    public Progreso obtenerExamen(int idProgreso) throws Exception;
    public boolean insertarExamen(Progreso progreso) throws Exception;
    public boolean actualizarExamen(int idProgreso) throws Exception;
    public boolean eliminarExamen(int idProgreso) throws Exception;
}

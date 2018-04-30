
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
    public Progreso obtenerProgreso(int folioInscripcion) throws Exception;
    public boolean insertarProgreso(Progreso progreso) throws Exception;
    public boolean actualizarProgreso(Progreso progreso) throws Exception;

}

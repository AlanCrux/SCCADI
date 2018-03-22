/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.ActividadProgramada;

/**
 *
 * @author Esmeralda
 */
public interface DAOActividadProgramada {
    public List<ActividadProgramada> obtenerActividadProgramada() throws Exception;
    public ActividadProgramada obtenerActividadProgramada(int idActividadProgramada) throws Exception;
    public boolean insertarActividadProgramada(ActividadProgramada actividadprogramada) throws Exception;
    public boolean actualizarActividadProgramada(int idActividadProgramada) throws Exception;
    public boolean eliminarActividadProgramada(int idActividadProgramada) throws Exception;
}

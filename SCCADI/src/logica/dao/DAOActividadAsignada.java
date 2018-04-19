/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.ActividadAsignada;

/**
 *
 * @author Esmeralda
 */
public interface DAOActividadAsignada {
    public List<ActividadAsignada> obtenerActividadAsignada() throws Exception;
    public ActividadAsignada obtenerActividadAsignada(int idActividadAsignada) throws Exception;
    public boolean insertarActividadAsignada(ActividadAsignada actividadAsignada) throws Exception;
    public boolean actualizarActividadAsignada(ActividadAsignada actividad) throws Exception;
    public boolean eliminarActividadAsignada(int idActividadAsignada) throws Exception;
}

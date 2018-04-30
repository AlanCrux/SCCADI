/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.Examen;

/**
 *
 * @author Esmeralda
 */
public interface DAOExamen {
    
    public List<Examen> obtenerExamenes(int folioInscripcion) throws Exception;
    public Examen obtenerExamen(int idExamen) throws Exception;
    public boolean insertarExamen(Examen examen) throws Exception;
    public boolean actualizarExamen(int noPersonal) throws Exception;
    public boolean eliminarExamen(int noPersonal) throws Exception;
}

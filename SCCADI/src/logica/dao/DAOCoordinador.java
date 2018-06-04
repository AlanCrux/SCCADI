/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.Coordinador;

/**
 *
 * @author Esmeralda
 */
public interface DAOCoordinador {

    public List<Coordinador> obtenerCoordinadores() throws Exception;

    public Coordinador obtenerCoordinador(int noPersonal) throws Exception;

    public boolean insertarCoordinador(Coordinador coordinador) throws Exception;

    public boolean actualizarCoordinador(Coordinador coordinador) throws Exception;

    public boolean eliminarCoordinador(int noPersonal) throws Exception;
    
    public Coordinador obtenerCoordinador(int noPersonal, String contrasena) throws Exception;
}

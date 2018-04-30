/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.Recepcionista;

/**
 *
 * @author Esmeralda
 */
public interface DAORecepcionista {
    
    public List<Recepcionista> obtenerRecepcionistas() throws Exception;

    public Recepcionista obtenerRecepcionista(int noPersonal) throws Exception;

    public boolean insertarRecepcionista(Recepcionista recepcionista) throws Exception;

    public boolean actualizarRecepcionista(int noPersonal) throws Exception;

    public boolean eliminarRecepcionista(int noPersonal) throws Exception;
    
    public Recepcionista obtenerRecepcionista(int noPersonal, String contrasena) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dao;

import java.util.List;
import logica.dominio.Sala;

/**
 *
 * @author Esmeralda
 */
public interface DAOSala {
    public List<Sala> obtenerSala() throws Exception;
    public Sala obtenerSala(int idSala) throws Exception;
    public boolean insertarSala(Sala sala) throws Exception;
    public boolean actualizarSala(int Sala) throws Exception;
    public boolean eliminarSala(int idSala) throws Exception;
}

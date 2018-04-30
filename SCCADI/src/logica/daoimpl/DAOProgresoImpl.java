/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.daoimpl;

import datos.Conexion;
import java.util.List;
import logica.dao.DAOProgreso;
import logica.dominio.Progreso;

/**
 *
 * @author Esmeralda
 */
public class DAOProgresoImpl extends Conexion implements DAOProgreso{

    @Override
    public List<Progreso> obtenerProgresos(int folioInscripcion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Progreso obtenerExamen(int idProgreso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarExamen(Progreso progreso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarExamen(int idProgreso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarExamen(int idProgreso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

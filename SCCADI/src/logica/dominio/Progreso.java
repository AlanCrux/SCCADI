/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dominio;

/**
 *
 * @author Esmeralda
 */
public class Progreso {
    private int idProgreso;
    private int numBitacoras;
    private int numAutoevaluaciones;
    private int numReflexiones;
    private int numSeguimiento;
    private int folioInscripcion;

    public Progreso() {
    }

    public int getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(int idProgreso) {
        this.idProgreso = idProgreso;
    }

    public int getNumBitacoras() {
        return numBitacoras;
    }

    public void setNumBitacoras(int numBitacoras) {
        this.numBitacoras = numBitacoras;
    }

    public int getNumAutoevaluaciones() {
        return numAutoevaluaciones;
    }

    public void setNumAutoevaluaciones(int numAutoevaluaciones) {
        this.numAutoevaluaciones = numAutoevaluaciones;
    }

    public int getNumReflexiones() {
        return numReflexiones;
    }

    public void setNumReflexiones(int numReflexiones) {
        this.numReflexiones = numReflexiones;
    }

    public int getNumSeguimiento() {
        return numSeguimiento;
    }

    public void setNumSeguimiento(int numSeguimiento) {
        this.numSeguimiento = numSeguimiento;
    }

    public int getFolioInscripcion() {
        return folioInscripcion;
    }

    public void setFolioInscripcion(int folioInscripcion) {
        this.folioInscripcion = folioInscripcion;
    }
    
    
    
}

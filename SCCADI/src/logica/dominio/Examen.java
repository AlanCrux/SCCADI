/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dominio;

import java.util.Date;

/**
 *
 * @author Esmeralda
 */
public class Examen {
    private int idExamen;
    private String tipo;
    private Date fecha;
    private float calificacion;
    private int porcentaje;
    private String descripcion;
    private int folioInscripcion;

    public Examen() {
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFolioInscripcion() {
        return folioInscripcion;
    }

    public void setFolioInscripcion(int folioInscripcion) {
        this.folioInscripcion = folioInscripcion;
    }
    
    
    
}

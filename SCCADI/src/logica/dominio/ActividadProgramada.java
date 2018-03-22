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
public class ActividadProgramada {
    private int idActividadProgramada;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private int modulo;
    private int unidad;
    private int idExperiencia;

    public ActividadProgramada() {
    }

    public ActividadProgramada( String nombre, Date fechaInicio, Date fechaFin, int modulo, int unidad, int idExperiencia) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.modulo = modulo;
        this.unidad = unidad;
        this.idExperiencia = idExperiencia;
    }

    public int getIdActividadProgramada() {
        return idActividadProgramada;
    }

    public void setIdActividadProgramada(int idActividadProgramada) {
        this.idActividadProgramada = idActividadProgramada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }
    
    
}

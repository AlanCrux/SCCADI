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
public class Reservacion {
    
    private int idReservacion;
    private String matricula;
    private int idActividadAsignada;
    private boolean asistio;

    public Reservacion() {
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdActividadAsignada() {
        return idActividadAsignada;
    }

    public void setIdActividadAsignada(int idActividadAsignada) {
        this.idActividadAsignada = idActividadAsignada;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }


    
}

package logica.dominio;

import java.util.Date;

/**
 *
 * @author Alan Yoset Garcia Cruz
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

  public ActividadProgramada(String nombre, Date fechaInicio, Date fechaFin, int modulo, int unidad, int idExperiencia) {
    this.nombre = nombre;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.modulo = modulo;
    this.unidad = unidad;
    this.idExperiencia = idExperiencia;
  }

  public ActividadProgramada(int idActividadProgramada, String nombre, Date fechaInicio, Date fechaFin, int modulo, int unidad, int idExperiencia) {
    this.idActividadProgramada = idActividadProgramada;
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

  @Override
  public String toString() {
    return nombre + "- " + fechaInicio + " - " + fechaFin;
  }
}

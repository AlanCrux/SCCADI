package logica.dominio;

import java.util.Date;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class Aviso {

  private int idAviso;
  private Date fechaInicio;
  private Date fechaFin;
  private String asunto;
  private String contenido;

  public Aviso() {
  }

  public Aviso(int idAviso, Date fechaInicio, Date fechaFin, String asunto, String contenido) {
    this.idAviso = idAviso;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.asunto = asunto;
    this.contenido = contenido;
  }

  public int getIdAviso() {
    return idAviso;
  }

  public void setIdAviso(int idAviso) {
    this.idAviso = idAviso;
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

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  @Override
  public String toString() {
    return asunto;
  }

}

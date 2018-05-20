
package logica.dominio;

import java.util.Date;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class Reservacion {

  private int idReservacion;
  private String matricula;
  private int idActividadAsignada;
  private boolean asistio;
  private String nombre;
  private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  public Reservacion(int idReservacion, String matricula, int idActividadAsignada) {
    this.idReservacion = idReservacion;
    this.matricula = matricula;
    this.idActividadAsignada = idActividadAsignada;
  }

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
}

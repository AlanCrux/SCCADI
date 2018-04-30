
package logica.dominio;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class Reservacion {

  private int idReservacion;
  private String matricula;
  private int idActividadAsignada;

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

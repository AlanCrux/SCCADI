package logica.dominio;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Esmeralda
 */
public class ActividadAsignada {

  private int idActividadAsignada;
  private int idActividadProgramda;
  private int noPersonal;
  private int idSala;
  private int cupoMaximo;
  private Date fecha;
  private Time hora;
  private String nombre;
  private String nombreSala;
  private String nombreAsesor;

  public ActividadAsignada(int idActividadAsignada, int idActividadProgramda, int noPersonal, int idSala, int cupoMaximo, Date fecha, Time hora) {
    this.idActividadAsignada = idActividadAsignada;
    this.idActividadProgramda = idActividadProgramda;
    this.noPersonal = noPersonal;
    this.idSala = idSala;
    this.cupoMaximo = cupoMaximo;
    this.fecha = fecha;
    this.hora = hora;
  }

  public ActividadAsignada() {

  }

  public String getNombreAsesor() {
    return nombreAsesor;
  }

  public void setNombreAsesor(String nombreAsesor) {
    this.nombreAsesor = nombreAsesor;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombreSala() {
    return nombreSala;
  }

  public void setNombreSala(String nombreSala) {
    this.nombreSala = nombreSala;
  }

  public int getIdActividadAsignada() {
    return idActividadAsignada;
  }

  public void setIdActividadAsignada(int idActividadAsignada) {
    this.idActividadAsignada = idActividadAsignada;
  }

  public int getIdActividadProgramda() {
    return idActividadProgramda;
  }

  public void setIdActividadProgramda(int idActividadProgramda) {
    this.idActividadProgramda = idActividadProgramda;
  }

  public int getNoPersonal() {
    return noPersonal;
  }

  public void setNoPersonal(int noPersonal) {
    this.noPersonal = noPersonal;
  }

  public int getIdSala() {
    return idSala;
  }

  public void setIdSala(int idSala) {
    this.idSala = idSala;
  }

  public int getCupoMaximo() {
    return cupoMaximo;
  }

  public void setCupoMaximo(int cupoMaximo) {
    this.cupoMaximo = cupoMaximo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Time getHora() {
    return hora;
  }

  public void setHora(Time hora) {
    this.hora = hora;
  }

}

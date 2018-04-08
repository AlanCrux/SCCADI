package logica.dominio;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class Coordinador {

  private int noPersonal;
  private String nombre;
  private String correo;

  public Coordinador() {
  }

  public Coordinador(int noPersonal, String nombre, String correo) {
    this.noPersonal = noPersonal;
    this.nombre = nombre;
    this.correo = correo;
  }


  public int getNoPersonal() {
    return noPersonal;
  }

  public void setNoPersonal(int noPersonal) {
    this.noPersonal = noPersonal;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

}

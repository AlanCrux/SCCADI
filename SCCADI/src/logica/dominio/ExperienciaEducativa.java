package logica.dominio;

/**
 *
 * @author Alan Yoset García Cruz
 * @version 1.0
 */
public class ExperienciaEducativa {

  private int idExperiencia;
  private String nombre;
  private String nivel;
  private int numModulos;
  private int numUnidades;

  public ExperienciaEducativa() {
  }

  public ExperienciaEducativa(int idExperiencia, String nombre, String nivel) {
    this.idExperiencia = idExperiencia;
    this.nombre = nombre;
    this.nivel = nivel;
  }

  public int getIdExperiencia() {
    return idExperiencia;
  }

  public void setIdExperiencia(int idExperiencia) {
    this.idExperiencia = idExperiencia;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public int getNumModulos() {
    return numModulos;
  }

  public void setNumModulos(int numModulos) {
    this.numModulos = numModulos;
  }

  public int getNumUnidades() {
    return numUnidades;
  }

  public void setNumUnidades(int numUnidades) {
    this.numUnidades = numUnidades;
  }
  
  

  @Override
  public String toString() {
    return nombre + " " + nivel;
  }

}

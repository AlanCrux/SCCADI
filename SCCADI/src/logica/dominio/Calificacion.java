package logica.dominio;

/**
 *
 * @author alanc
 */
public class Calificacion {
  int idExamen; 
  int folioInscripcion; 
  float calificacion; 

  public Calificacion() {
  }

  public Calificacion(int idExamen, int folioInscripcion, float calificacion) {
    this.idExamen = idExamen;
    this.folioInscripcion = folioInscripcion;
    this.calificacion = calificacion;
  }

  public int getIdExamen() {
    return idExamen;
  }

  public void setIdExamen(int idExamen) {
    this.idExamen = idExamen;
  }

  public int getFolioInscripcion() {
    return folioInscripcion;
  }

  public void setFolioInscripcion(int folioInscripcion) {
    this.folioInscripcion = folioInscripcion;
  }

  public float getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(float calificacion) {
    this.calificacion = calificacion;
  }
  
  
}

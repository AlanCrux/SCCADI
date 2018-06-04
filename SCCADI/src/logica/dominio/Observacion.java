package logica.dominio;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public class Observacion {
    private int idObservacion;
    private int folioInscripcion; 
    private int noPersonal; 
    private String observacion; 

    public Observacion(int idObservacion, int folioInscripcion, int noPersonal, String observacion) {
        this.idObservacion = idObservacion;
        this.folioInscripcion = folioInscripcion;
        this.noPersonal = noPersonal;
        this.observacion = observacion;
    }

    public Observacion() {
    }

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public int getFolioInscripcion() {
        return folioInscripcion;
    }

    public void setFolioInscripcion(int folioInscripcion) {
        this.folioInscripcion = folioInscripcion;
    }

    public int getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(int noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}

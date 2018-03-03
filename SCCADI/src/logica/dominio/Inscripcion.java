package logica.dominio;

/**
 *
 * @author Alan Yoset García Cruz
 * @version 1.0
 */
public class Inscripcion {
    private int calificacion; 
    private int nrc; 
    private String matricula; 

    public Inscripcion() {
    }

    public Inscripcion(int calificacion, int nrc, String matricula) {
        this.calificacion = calificacion;
        this.nrc = nrc;
        this.matricula = matricula;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
}

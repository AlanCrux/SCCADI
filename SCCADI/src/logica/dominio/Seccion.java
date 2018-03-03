package logica.dominio;

/**
 *
 * @author Alan Yoset García Cruz
 * @version 1.0
 */
public class Seccion {
    private int nrc; 
    private int idExperiencia; 
    private int noPersonal; 
    private String periodo; 

    public Seccion() {
    }

    public Seccion(int nrc, int idExperiencia, int noPersonal, String periodo) {
        this.nrc = nrc;
        this.idExperiencia = idExperiencia;
        this.noPersonal = noPersonal;
        this.periodo = periodo;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public int getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(int noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
}

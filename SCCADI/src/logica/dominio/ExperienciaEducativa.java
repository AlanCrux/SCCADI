package logica.dominio;

/**
 * 
 * @author Alan Yoset García Cruz
 * @version 1.0
 */
public class ExperienciaEducativa {
    private int idExperiencia; 
    private String nombre;
    private int nivel;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(int idExperiencia, String nombre, int nivel) {
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}

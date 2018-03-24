package logica.dominio;

/**
 *
 * @author Esmeralda Jiménez Ramos
 */
public class Sala {
    private int idSala;
    private int cupo;
    private String nombre;

    public Sala(int idSala, int cupo, String nombre) {
        this.idSala = idSala;
        this.cupo = cupo;
        this.nombre = nombre;
    }

    public Sala() {
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

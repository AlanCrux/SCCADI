package logica.dominio;

/**
 *
 * @author Alan Yoset García Cruz
 * @version 1.0
 */
public class Asesor {
    private int noPersonal; 
    private String nombre; 
    private String apPaterno;
    private String apMaterno; 
    private String correo; 

    public Asesor() {
    }

    public Asesor(int noPersonal, String nombre, String apPaterno, String apMaterno, String correo) {
        this.noPersonal = noPersonal;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
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

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}

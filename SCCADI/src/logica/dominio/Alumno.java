package logica.dominio;

import java.sql.Blob;

/**
 *
 * @author Alan Yoset García Cruz
 */
public class Alumno {

  private String matricula;
  private String nombre;
  private String correo;
  private String programaEducativo;
  private String contactoEmergencia;
  private String numeroEmergencia;
  private Blob fotografia;

  public Alumno() {
  }

  public Alumno(String matricula, String nombre, String correo, String programaEducativo, String contactoEmergencia, String numeroEmergencia, Blob fotografia) {
    this.matricula = matricula;
    this.nombre = nombre;
    this.correo = correo;
    this.programaEducativo = programaEducativo;
    this.contactoEmergencia = contactoEmergencia;
    this.numeroEmergencia = numeroEmergencia;
    this.fotografia = fotografia;
  }

  public Alumno(String matricula, String nombre, String correo, String programaEducativo, String contactoEmergencia, String numeroEmergencia) {
    this.matricula = matricula;
    this.nombre = nombre;
    this.correo = correo;
    this.programaEducativo = programaEducativo;
    this.contactoEmergencia = contactoEmergencia;
    this.numeroEmergencia = numeroEmergencia;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
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

  public String getProgramaEducativo() {
    return programaEducativo;
  }

  public void setProgramaEducativo(String programaEducativo) {
    this.programaEducativo = programaEducativo;
  }

  public String getContactoEmergencia() {
    return contactoEmergencia;
  }

  public void setContactoEmergencia(String contactoEmergencia) {
    this.contactoEmergencia = contactoEmergencia;
  }

  public String getNumeroEmergencia() {
    return numeroEmergencia;
  }

  public void setNumeroEmergencia(String numeroEmergencia) {
    this.numeroEmergencia = numeroEmergencia;
  }

  public Blob getFotografia() {
    return fotografia;
  }

  public void setFotografia(Blob fotografia) {
    this.fotografia = fotografia;
  }

    @Override
    public String toString() {
        return nombre;
    }
  
  

}

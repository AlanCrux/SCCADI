package logica.dominio;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.daoimpl.DAOExperienciaEducativaImpl;

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
  private int cupo; 
  
  //Atributos que no pertenecen al pojo 
  private ExperienciaEducativa exp;  
  private String asesor; 
  
  public Seccion() {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    asesor = "Juanito Pérez";
  }

  public Seccion(int nrc, int idExperiencia, int noPersonal, String periodo, int cupo) {
    this.nrc = nrc;
    this.idExperiencia = idExperiencia;
    this.noPersonal = noPersonal;
    this.periodo = periodo;
    this.cupo = cupo; 
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
  
  public int getCupo() {
    return cupo;
  }

  public void setCupo(int cupo) {
    this.cupo = cupo;
  }
  
  public String getExperiencia() throws Exception {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl(); 
    return daoExperiencia.obtenerExperiencia(idExperiencia).getNombre();
  }
  
  public String getNivel() throws Exception {
    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl(); 
    return daoExperiencia.obtenerExperiencia(idExperiencia).getNivel();
  }
  
  public String getAsesor(){
    return asesor; 
  }

  
  
  

}

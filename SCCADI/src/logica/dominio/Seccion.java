package logica.dominio;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.daoimpl.DAOAsesorImpl;
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
  private ExperienciaEducativa exp;
  private String asesor = "";

  public Seccion() {
  }

  public Seccion(int nrc, int idExperiencia, int noPersonal, String periodo, int cupo) {
    this.nrc = nrc;
    this.idExperiencia = idExperiencia;
    this.noPersonal = noPersonal;
    this.periodo = periodo;
    this.cupo = cupo;

    DAOExperienciaEducativaImpl daoExperiencia = new DAOExperienciaEducativaImpl();
    DAOAsesorImpl daoAsesor = new DAOAsesorImpl();
    try {
      exp = daoExperiencia.obtenerExperiencia(idExperiencia);
      asesor = daoAsesor.obtenerAsesor(noPersonal).getNombre();
    } catch (Exception ex) {
      Logger.getLogger(Seccion.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    return exp.getNombre();
  }

  public String getNivel() throws Exception {
    return exp.getNivel();
  }

  public String getAsesor() throws Exception {
    return asesor;
  }

    @Override
    public String toString() {
        return  exp.getNombre() +" "+ exp.getNivel();
    }

  
}

package logica.dominio;

/**
 *
 * @author Alan Yoset García Cruz
 * @version 1.0
 */
public class ExperienciaEducativa {

    private int idExperiencia;
    private String nombre;
    private String nivel;
    private int numBitacoras;
    private int numAutoevaluaciones;
    private int numSeguimiento;
    private int numReflexiones;
    private int numTaller;
    private int numExamenes;
    private int porcentajeBitacoras;
    private int porcentajeAutoevaluaciones;
    private int porcentajeSeguimiento;
    private int porcentajeReflexiones;
    private int porcentajeTaller;
    private int porcentajeExamenes;
    private int numModulos;
    private int numUnidades;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(int idExperiencia, String nombre, String nivel) {
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getNumBitacoras() {
        return numBitacoras;
    }

    public void setNumBitacoras(int numBitacoras) {
        this.numBitacoras = numBitacoras;
    }

    public int getNumAutoevaluaciones() {
        return numAutoevaluaciones;
    }

    public void setNumAutoevaluaciones(int numAutoevaluaciones) {
        this.numAutoevaluaciones = numAutoevaluaciones;
    }

    public int getNumSeguimiento() {
        return numSeguimiento;
    }

    public void setNumSeguimiento(int numSeguimiento) {
        this.numSeguimiento = numSeguimiento;
    }

    public int getNumReflexiones() {
        return numReflexiones;
    }

    public void setNumReflexiones(int numReflexiones) {
        this.numReflexiones = numReflexiones;
    }

    public int getNumTaller() {
        return numTaller;
    }

    public void setNumTaller(int numTaller) {
        this.numTaller = numTaller;
    }

    public int getNumExamenes() {
        return numExamenes;
    }

    public void setNumExamenes(int numExamenes) {
        this.numExamenes = numExamenes;
    }

    public int getPorcentajeBitacoras() {
        return porcentajeBitacoras;
    }

    public void setPorcentajeBitacoras(int porcentajeBitacoras) {
        this.porcentajeBitacoras = porcentajeBitacoras;
    }

    public int getPorcentajeAutoevaluaciones() {
        return porcentajeAutoevaluaciones;
    }

    public void setPorcentajeAutoevaluaciones(int porcentajeAutoevaluaciones) {
        this.porcentajeAutoevaluaciones = porcentajeAutoevaluaciones;
    }

    public int getPorcentajeSeguimiento() {
        return porcentajeSeguimiento;
    }

    public void setPorcentajeSeguimiento(int porcentajeSeguimiento) {
        this.porcentajeSeguimiento = porcentajeSeguimiento;
    }

    public int getPorcentajeReflexiones() {
        return porcentajeReflexiones;
    }

    public void setPorcentajeReflexiones(int porcentajeReflexiones) {
        this.porcentajeReflexiones = porcentajeReflexiones;
    }

    public int getPorcentajeTaller() {
        return porcentajeTaller;
    }

    public void setPorcentajeTaller(int porcentajeTaller) {
        this.porcentajeTaller = porcentajeTaller;
    }

    public int getPorcentajeExamenes() {
        return porcentajeExamenes;
    }

    public void setPorcentajeExamenes(int porcentajeExamenes) {
        this.porcentajeExamenes = porcentajeExamenes;
    }

    public int getNumModulos() {
        return numModulos;
    }

    public void setNumModulos(int numModulos) {
        this.numModulos = numModulos;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(int numUnidades) {
        this.numUnidades = numUnidades;
    }

    @Override
    public String toString() {
        return nombre + " " + nivel;
    }

}

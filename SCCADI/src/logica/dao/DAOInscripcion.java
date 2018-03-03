package logica.dao;

import java.sql.SQLException;
import java.util.List;
import logica.dominio.Inscripcion;

/**
 *
 * @author Alan Yoset Garcia Cruz
 */
public interface DAOInscripcion {
    public List<Inscripcion> obtenerInscripciones() throws SQLException;
    public Inscripcion obtenerInscripciones(int folio) throws SQLException;
    public boolean insertarInscripciones(Inscripcion inscripcion) throws SQLException;
    public boolean actualizarInscripciones(int folio) throws SQLException;
    public boolean eliminarInscripciones(int folio) throws SQLException;
}

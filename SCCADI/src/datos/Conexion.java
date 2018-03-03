package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programa SCCADI 1.0 01 Marzo de 2018
 *
 * @author Alan Yoset García Cruz
 */
public class Conexion {

    public Connection conn;
    private String HOST = "localhost";
    private String BD = "sccadi";
    private String USER_NAME = "sccadiAdmin";
    private String PASSWORD = "RoarOmegaRoar";
    private String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private static Conexion connect;

    public Conexion() {
        try {
            Class.forName(DRIVER_CLASS).newInstance();
            String url = "jdbc:mysql://" + HOST + "/" + BD;
            conn = DriverManager.getConnection(url, USER_NAME, PASSWORD);
            connect = this;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection connection() {
        try {
            return conn;
        } finally {

        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

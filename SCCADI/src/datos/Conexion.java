package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programa SCCADI 1.0 01 Marzo de 2018
 * Esta clase nos permite establecer la conexión con la base de de datos.
 * @author Alan Yoset García Cruz
 */
public class Conexion {

  public Connection conn;
  private final String HOST = "localhost";
  private final String BD = "sccadi";
  private final String USER_NAME = "sccadiAdmin";
  private final String PASSWORD = "RoarOmegaRoar";
  private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

  private static Conexion connect;

  /**
   * Construye el objeto conexión. 
   */
  public Conexion() {
   
  }

  /**
   * Establece la conexión con la base de datos
   * @return el objeto conexión.
   * @throws java.lang.Exception   
   */
  public Connection connection() throws Exception {
     try {
      Class.forName(DRIVER_CLASS).newInstance();
      String url = "jdbc:mysql://" + HOST + "/" + BD;
      conn = DriverManager.getConnection(url, USER_NAME, PASSWORD);
      connect = this;
    } catch (Exception ex) {
      throw ex; 
    } 
     return conn;
  }

  /**
   * Cierra la conexión con la base de datos. 
   */
  public void close() {
    try {
      conn.close();
    } catch (SQLException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

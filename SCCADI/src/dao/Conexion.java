package dao;

import java.sql.*;

public class Conexion {

  public Connection conn;
  private String host;
  private String bd;
  private String username;
  private String password;

  private static Conexion connect;

  public Conexion() {
    host = "localhost";
    bd = "sccadi";
    username = "CADIAdm";
    password = "Adm17";
    try //regresa un objeto del tipo especificado, com.mysql.jdbc.Driver es la clase que implementa java.sql.Driver
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      //Intentamos conectarnos a la base de Datos 
      System.out.println("Conectando a la base...");
      String url = "jdbc:mysql://" + host + "/" + bd;
      //jdbc:mysql://localhost/expendio

      conn = DriverManager.getConnection(url, username, password); //obtenemos la conexion
      System.out.println("Conexion a base de datos establecida");

    } catch (SQLException ex) {
      System.out.println("Error de mysql");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Se produjo un error inesperado: " + e.getMessage());
    }

    connect = this;

    /*
        }
        catch (Exception e)
        {
            System.err.println ("No se pudo establecer la conexi�n con la base de datos");
        }    	
     */
  }

  public Conexion(String host, String bd, String username, String password) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    this.host = host;
    this.bd = bd;
    this.username = username;
    this.password = password;
    conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd, username, password);
    connect = this;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getBd() {
    return bd;
  }

  public void setBd(String bd) {
    this.bd = bd;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage() + "\n");
    }
  }

  public static Conexion getConnect() {
    return connect;
  }

  public static void setConnect(Conexion connect) {
    Conexion.connect = connect;
  }
}


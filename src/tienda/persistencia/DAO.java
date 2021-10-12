/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tienda.excepcion.MiExcepcion;

/**
 *
 * @author chachoadmin
 */
public abstract class DAO {

    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;

    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";

    protected void conectarBase() throws MiExcepcion {

        try {

            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new MiExcepcion("ERROR AL CONECTARSE");
        }

    }

    protected void desconectarBase() throws Exception {
        try {

            if (resultado != null) {
                resultado.close();
            }

            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }

        } catch (Exception e) {
            throw e;
        }

    }

    protected void insertarModificarEliminar(String sql) throws SQLException, ClassNotFoundException, Exception {

        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);

        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                // ex.printStackTrace();
                throw new MiExcepcion("ERROR AL REALIZAR ROLLBACK");
            }
            // e.printStackTrace();
            throw new MiExcepcion("ERROR AL EJECUTAR SENTENCIA");
        } finally {
            desconectarBase();
        }

    }

    protected void consultarBase(String sql) throws MiExcepcion {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
           /// System.out.println("query: " + sql);
            resultado = sentencia.executeQuery(sql);
            
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new MiExcepcion("ERROR AL CONSULTAR");
        }
    }

}

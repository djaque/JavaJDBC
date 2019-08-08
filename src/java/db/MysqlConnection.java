/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dany
 */
public class MysqlConnection {

    private static Connection con;
    private static final String DB = "sistema";
    private static final String USR = "admin";
    private static final String PASS = "admin";
    private static final String URL = "jdbc:mysql://localhost/" + DB +"?useSSL=false";

    /**
     * Open new connection to mysql
     * @return Connection
     * @throws SQLException 
     */
    public static Connection open() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USR, PASS);
        } catch (ClassNotFoundException ce) {
            System.out.println("Class not found Check jdbc Driver");
        }  catch (SQLException e) {
            System.out.println("Connection error..."+e.getMessage());
            throw e;
        }
        return con;
    }
    
    /**
     * Close this connection to mysql
     */
    public static void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: No se logro cerrar la conexion: "+e);
        }
 
    }
 
}

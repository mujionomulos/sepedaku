/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mulos.sepedaku;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author SA GSSI
 */
public class Config {
    private static Connection mysqlconfig;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static Connection configDB()throws SQLException{
        try {
            Class.forName(JDBC_DRIVER);
            String url = "jdbc:mysql://localhost/sepedaku";
//            String url="jdbc:mysql://localhost:3306/purchasing"; //url database
            String user="root"; //user database
            String pass=""; //password database
//            DriverManager.registerDriver(new Driver());
            
            mysqlconfig=DriverManager.getConnection(url, user, pass);            
        } catch (Exception e) {
            System.err.println("koneksi gagal "+e.getMessage()); //perintah menampilkan error pada koneksi
        }
        return mysqlconfig;
    }    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class ConnectionDB {
    public static Connection getConnection(){
        Connection c = null;
        
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            
            String url = "jdbc:mysql://localhost:3306/qlchta?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = ""; 
            
            c = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return c;
    }
    
    public static void closeConnection(Connection c){
        try{
            if (c!=null){
                c.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

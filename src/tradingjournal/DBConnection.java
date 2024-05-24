/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tradingjournal;


import java.sql.*;
/**
 *
 * @author princy
 */
public class DBConnection {
    static Connection con;
    
    public static Connection getConnection(){
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3307/trading_journal?useSSL=false","brighton","bri123");
        
         }catch (Exception e){
         System.out.println(e);    
         }
        return con;
    }
    
}
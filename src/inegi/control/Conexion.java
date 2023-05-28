/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inegi.control;

import java.sql.*; 

public class Conexion {
     static String bd = "inegi"; 
   static String login = "root"; 
   static String password = "Abisai1456"; 
   static String url = "jdbc:mysql://localhost/"+bd; 
 
   Connection conn = null; 
    
   public Conexion() { 
      try{          
         Class.forName("com.mysql.jdbc.Driver");          
         conn = DriverManager.getConnection(url,login,password); 
         if (conn!=null){ 
            System.out.println("Conexion a base de datos "+bd+". listo"); 
         } 
      }catch(SQLException e){ 
         System.out.println(e); 
      }catch(ClassNotFoundException e){ 
         System.out.println(e); 
      } 
   } 
    
   public Connection getConnection(){ 
      return conn; 
   } 
 
   public void desconectar(){ 
      conn = null; 
   } 
}

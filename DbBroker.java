/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author nikica1
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbBroker {
    
    
     private String address, user, pass;
    Connection conn;
  
    
    public DbBroker(){
        this.address = "localhost:3306/articles_db";
        this.user = "root";
        this.pass = "";
    }
    
    public DbBroker(String address, String user, String pass){
        this.address = address;
        this.user = user;
        this.pass = pass;
    }
    
    private void conn() {
        
         try {
             try {
                 Class.forName("com.mysql.jdbc.Driver").newInstance();
             } catch (InstantiationException ex) {
                
             } catch (IllegalAccessException ex) {
               
             }
         } catch (ClassNotFoundException ex) {
            System.out.println(ex);
         }
         
         try {
             conn = DriverManager.getConnection("jdbc:mysql://"+address,user,pass);
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    private void close() {
        
         
         try {
             conn.close();
         } catch (SQLException ex) {
          
         }   
    }
    
    public int scalar(String query){
        
        try {
            conn();
            int res = -1;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
                res=rs.getInt(1);
            close();
            return res;
        } catch (SQLException ex) {
            close();
            return -1;
           
        }
        
    }
    public int scalarUpdate(String query){
        
        try {
            conn();
            ResultSet rs;
            int res = -1;
            
            Statement st = conn.createStatement();
            st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            
            while(rs.next()) 
                res= rs.getInt(1);
            
            st.close();
            close();
            
            return res;
            
        } catch (SQLException ex) {
            close();
            return -2;
          
        }   
    }
    
    public String obicnaKomanda(String query){
    
        try {
            conn();
            Statement st = conn.createStatement();
            st.execute(query);
            close();
            
            return "0";
            
        } catch (SQLException ex) {
            close();
            return ex.toString();
        }
    }
    public String string(String query) {
    
        try {
            conn();
            String res = "";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
                res = rs.getString(1);
            close();
            return res;
        } catch (SQLException ex) {
            close();
            return "";
            
            
        }
    
    }
    public ArrayList<String> stringArr(String query){
        
        try {
            conn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<String> resultList = new ArrayList<String>();
            
            while(rs.next())
                resultList.add(rs.getString(1));
            close();
            return resultList;
        } catch (SQLException ex) {
            ArrayList<String> r = new ArrayList<String>();
            r.add("");
            close();
            return r;
        }
    } 
    public ArrayList<String[]> getArr(String query) {
        
        try {
            conn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsMData = rs.getMetaData();
            
            ArrayList<String[]> resultList = new ArrayList<String[]>();
            int colNo = rsMData.getColumnCount();
            
            while(rs.next()){
                String[] rw = new String[colNo];
                for(int i=1; i<=colNo; i++)
                    rw[i-1]= rs.getString(i);
                resultList.add(rw);
            }
            close();
            return resultList;
            
        } catch (SQLException ex) {
          
            ArrayList<String[]> r = new ArrayList<String[]>();
            String[] s = new String[1];
            s[0] = ex.getMessage();
            r.add(s);
            close();
            return r;     
        }
             
    }
    
    
    
    
    


    
}

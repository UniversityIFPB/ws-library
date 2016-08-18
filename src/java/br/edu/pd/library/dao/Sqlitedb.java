/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pd.library.dao;

import br.edu.pd.model.Livro;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EDER
 */
public class Sqlitedb {
    
    private Connection c = null;
    
    public Connection createdb(){    
    Statement stmt = null;
        
    try {
        File file = new File("C:\\xampp\\htdocs\\Library\\library.db");

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:library.db");
        System.out.println("DB create with success");
            
        if(!file.exists()){
            this.createTables();           
        }
        
        return this.c;        
        
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      //System.exit(0);
    }
    System.out.println("Table created successfully");
    return null;
  }
    
    private void createTables(){
        Statement stmt;
        try {
            
            stmt = this.instance().createStatement();
            
            String sql = "CREATE TABLE library " +
                     "(ID INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL," +
                     " titulo           VARCHAR(222)    NOT NULL, " + 
                     " autor            VARCHAR(222)     NOT NULL, " + 
                     " editora        VARCHAR(130), " + 
                     " edicao         INT, "+ 
                     " isbn         DOUBLE)"; 
        stmt.executeUpdate(sql);
        stmt.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(Sqlitedb.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
  public Connection instance(){
      if(this.c == null)
          this.c = this.createdb();
      
      return this.c;
  }
  
  public String create(Livro l){
        try {
            Statement stmt = this.instance().createStatement();
            
            String sql = "insert into library (titulo, autor, editora, edicao, isbn)"+
                    " values ('"+l.getTitulo()+"', '"+l.getAutor()+"', '"+l.getEditora()+"', '"+l.getEdicao()+"', '"+l.getISBN()+"')";
            
            System.out.printf(sql);
            stmt.executeQuery(sql);
            
            stmt.close();
            
            return sql;
        } catch (SQLException ex) {
            Logger.getLogger(Sqlitedb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  }
    
  public static void main(){
      Sqlitedb s = new Sqlitedb();
  }
}
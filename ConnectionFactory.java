/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Elton Sola
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/prova?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "";

    public void createDatabase() {
        try {
            Class.forName(DRIVER);
            
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = con.createStatement();
            con.setCatalog("prova"); // Selecionando o banco
        } 
        catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao: ", ex);
        }
    }
    
    public void CriaTabela(){
        Connection con = null;
        con = ConnectionFactory.getConnection();
        
        try {
            String SQLAluno = "CREATE TABLE IF NOT EXISTS `aluno` (\n" +
                                    "  `codigo` int NOT NULL AUTO_INCREMENT,\n" +
                                    "  `nome` varchar(50) NOT NULL,\n" +
                                    "  PRIMARY KEY (`codigo`)\n" +
                                    ") ;\n";
            
            String SQLCurso = "CREATE TABLE IF NOT EXISTS `curso` (\n" +
                                    "  `codigo` int NOT NULL AUTO_INCREMENT,\n" +
                                    "  `descricao` varchar(50) NOT NULL,\n" +
                                    "  `ementa` varchar(50) NOT NULL,\n" +
                                    "  PRIMARY KEY (`codigo`)\n" +
                                    ") ;\n" +
                                    "\n";
                                  
            String SQLCursoAluno = "CREATE TABLE IF NOT EXISTS `curso_aluno` (\n" +
                                    "  `codigo` int NOT NULL AUTO_INCREMENT,\n" +
                                    "  `codigo_curso` int NOT NULL,\n" +
                                    "  `codigo_aluno` int NOT NULL,\n" +
                                    "  PRIMARY KEY (`codigo`)\n" +
                                    ");";
            
            Statement stmt = con.createStatement();
            //Executa as Strings de criação das tabelas
            stmt.execute(SQLAluno); 
            stmt.execute(SQLCurso);
            stmt.execute(SQLCursoAluno);
           
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro na conexao: ", ex);
        }
    }
    
    
    public static Connection getConnection() {
        try {
            
            Class.forName(DRIVER);           
            
            return DriverManager.getConnection(URL, USER, PASS);
           
        }   catch (SQLException | ClassNotFoundException ex ) {
            throw new RuntimeException("Erro na conexao: ", ex);
        }       
        
    }
    
    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: "+ ex);
            }
        }
    }
   
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: "+ ex);
            }
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: "+ ex);
            }
        }
        closeConnection(con,stmt);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Curso;
/**
 *
 * @author Elton Sola
 */
public class CursoDAO {
    
    private Connection con = null;

    public CursoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Curso curso){
        
        String sql = "INSERT INTO curso (descricao,ementa) VALUES (?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, curso.getDescricao());
            stmt.setString(2, curso.getEmenta());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
            }finally{
        ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }
    
    public List<Curso> findAll(){
        
        String sql = "SELECT * FROM curso";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Curso> cursos = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setEmenta(rs.getString("Ementa"));
                cursos.add(curso);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return cursos;
    }
    
    public Curso procuraPorId(int codigo_curso){
        
        String sql = "SELECT * FROM curso WHERE codigo = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
                
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo_curso);
            rs = stmt.executeQuery();
            if(rs.next()){
                
                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setDescricao(rs.getString("descricao"));
                return curso;
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return null;
    }
    
    public List<Curso> readForDesc(String descricao){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Curso> cursos = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("SELECT * FROM curso WHERE descricao LIKE ?");
            stmt.setString(1, "%"+descricao+"%");
            rs = stmt.executeQuery();
            
            if(rs.next()){ //Enquanto tiver valor no result set faça;
            } do {
                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setDescricao(rs.getString("descricao"));
                cursos.add(curso);
            }while(rs.next());{
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Curso não encontrado");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return cursos;
    }
    
     public boolean update(Curso curso){
        
        String sql = "UPDATE curso SET descricao = ?, ementa = ? WHERE codigo = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, curso.getDescricao());
            stmt.setString(2, curso.getEmenta());
            stmt.setInt(3, curso.getCodigo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
            }finally{
        ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }
     
     public boolean delete(Curso curso){
        
        String sql = "DELETE FROM curso WHERE codigo = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, curso.getCodigo());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
            }finally{
        ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import model.bean.Aluno;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Elton Sola
 */
public class AlunoDAO {
    
    private Connection con = null;

    public AlunoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Aluno aluno){ 
        
        String sql = "INSERT INTO aluno (nome) VALUES (?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
            }finally{
        ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }
    
    public List<Aluno> findAll(){ 
        
        String sql = "SELECT * FROM aluno";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Aluno aluno = new Aluno();
                aluno.setCodigo(rs.getInt("codigo"));
                aluno.setNome(rs.getString("nome"));
                alunos.add(aluno);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return alunos;
    }
    
    public Aluno procuraPorId(int codigo_aluno){
        
        String sql = "SELECT * FROM aluno WHERE codigo = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
                
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo_aluno);
            rs = stmt.executeQuery();
            if(rs.next()){
                
                Aluno aluno = new Aluno();
                aluno.setCodigo(rs.getInt("codigo"));
                aluno.setNome(rs.getString("nome"));
                return aluno;
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return null;
    }
    
    public List<Aluno> readForNome(String nome){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
              
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            
         if(rs.next()){ 
         }  do {
            Aluno aluno = new Aluno();
            aluno.setCodigo(rs.getInt("codigo"));
            aluno.setNome(rs.getString("nome"));
            alunos.add(aluno);
         } while(rs.next());{
         }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return alunos;
    }
    
     public boolean update(Aluno aluno){
        
        String sql = "UPDATE aluno SET nome = ? WHERE codigo = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCodigo());
            stmt.executeUpdate();
            
           JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }
     
     public boolean delete(Aluno aluno){
        
        String sql = "DELETE FROM aluno WHERE codigo = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, aluno.getCodigo());
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

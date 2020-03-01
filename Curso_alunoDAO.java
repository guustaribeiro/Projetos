/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Curso_aluno;

/**
 *
 * @author Elton Sola
 */
public class Curso_alunoDAO {

    private Connection con = null;

    public Curso_alunoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Curso_aluno curso_aluno) {

        String sql = "INSERT INTO curso_aluno (codigo_aluno,codigo_curso) VALUES (?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, curso_aluno.getAluno().getCodigo());
            stmt.setInt(2, curso_aluno.getCurso().getCodigo());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }

    public List<Curso_aluno> findAll() {

        String sql = "SELECT * FROM curso_aluno";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Curso_aluno> cursos_alunos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                CursoDAO curso = new CursoDAO();
                AlunoDAO aluno = new AlunoDAO();

                Curso_aluno ca = new Curso_aluno();
                ca.setCodigo(rs.getInt("codigo"));
                ca.setAluno(aluno.procuraPorId(rs.getInt("codigo_aluno")));
                ca.setCurso(curso.procuraPorId(rs.getInt("codigo_curso")));

                cursos_alunos.add(ca);

            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cursos_alunos;
    }

    public Curso_aluno findId(int codigo) {

        String sql = "SELECT * FROM curso_aluno WHERE codigo = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);

            rs = stmt.executeQuery();

            if (rs.next()) {

                CursoDAO curso = new CursoDAO();
                AlunoDAO aluno = new AlunoDAO();

                Curso_aluno ca = new Curso_aluno();
                ca.setCodigo(rs.getInt("codigo"));
                ca.setAluno(aluno.procuraPorId(rs.getInt("codigo_aluno")));
                ca.setCurso(curso.procuraPorId(rs.getInt("codigo_curso")));

                return ca;
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);       
        }        
        return null;

    }

    public boolean deleteAluno(Curso_aluno curso_aluno) {
        
        String sql = "DELETE FROM curso_aluno WHERE codigo_aluno = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, curso_aluno.getAluno().getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean deleteCurso(Curso_aluno curso_aluno) {

        String sql = "DELETE FROM curso_aluno WHERE codigo_curso = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, curso_aluno.getCurso().getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}

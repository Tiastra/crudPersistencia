/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import JDBC.ConectionFactory;
import dominio.Alunos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author C.Tiago
 */
public class AlunoDao {
    
    private Connection conecta;
    
    public AlunoDao(){
        this.conecta = new ConectionFactory().conecta();
    }
    
    public void cadastraAluno (Alunos obj){
    
        this.conecta = new ConectionFactory().conecta();
        try {
            String cmdsql = "insert into aluno(nome, email, telefone, celular, obs) values (?,?,?,?,?)";
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setInt(3, obj.getTelefone());
            stmt.setInt(4, obj.getCelular());
            stmt.setString(5, obj.getObs());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public List<Alunos>listarAlunos(){
    
        try {
            List<Alunos> lista = new ArrayList<>();
            String sql = "SELECT * FROM aluno";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Alunos a = new Alunos();
                a.setMatricula(rs.getInt("matricula"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                a.setTelefone(rs.getInt("telefone"));
                a.setCelular(rs.getInt("celular"));
                a.setObs(rs.getString("obs"));
                lista.add(a);
            }
            return lista;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void excluirAluno(Alunos obj){
        
        try {
            String cmdsql = "DELETE FROM aluno WHERE matricula=?";
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            stmt.setInt(1, obj.getMatricula());
            stmt.execute();
            
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void alterarAluno(Alunos obj){
        try {
            String cmdsql = "Update aluno set nome=?, email=?, telefone=?, celular=?, obs=? WHERE matricula=?";
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setInt(3, obj.getTelefone());
            stmt.setInt(4, obj.getCelular());
            stmt.setString(5, obj.getObs());
            stmt.setInt(6, obj.getMatricula());
            stmt.execute();
            
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

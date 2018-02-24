/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iniciando.dao;

import br.com.iniciando.dominio.Cadastro;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Weverton
 */
public class CadastroDAO {

    private Connection conexao = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

//  EntityManager responsavel por fazer as manipulações no banco de dados
    public EntityManager getEM() {
//        O nome(paramentro) da EntityManange dever ser igual o nome da unidade de persistência(persistence.xml)
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("IniciandoComSpringPU");
        return factory.createEntityManager();
    }

    public CadastroDAO() {
        this.conexao = Conexao.getInstance().criaConexao();
    }

    public void adicionar(Cadastro cadastro) {

        EntityManager em = getEM();
        try {
//        inicializando uma transação
            em.getTransaction().begin();
//        add um cadastro no BD
            em.persist(cadastro);//executa o insert
//        atualizando o BD
            em.getTransaction().commit();
//        finilizando transação
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

//        String sql = "INSERT INTO cadastro (nome,email,endereco,telefone) VALUES (?,?,?,?)";
////        try(this.conexao = FabricaFactory.getConection()) {
//        try {
//            this.ps = conexao.prepareStatement(sql);
//            this.ps.setString(1, cadastro.getNome());
//            this.ps.setString(2, cadastro.getEmail());
//            this.ps.setString(3, cadastro.getEndereco());
//            this.ps.setInt(4, cadastro.getTelefone());
////            this.ps.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
//            this.ps.execute();
//            this.ps.close();
//            //this.conexao.close();
//            System.out.println("cadastro gravado com sucesso !!!");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void remover(Cadastro cadastro) throws SQLException {
        String sql = "DELETE FROM cadastro WHERE "
                + "id = ? ";
        try {
            this.ps = conexao.prepareStatement(sql);
//            this.ps.setDouble(1, cadastro.getId());
            this.ps.execute();
            this.ps.close();
            //this.conexao.close();
            System.out.println("cadastro removido com sucesso !!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cadastro> buscar(String nome) throws SQLException {
        Cadastro cadastro = new Cadastro();
        ArrayList<Cadastro> cadastros = new ArrayList<>();
        String sql = "SELECT * FROM cadastro WHERE nome = ? ";

        this.ps = conexao.prepareStatement(sql);
        this.ps.setString(1, nome);
        this.rs = this.ps.executeQuery();
        while (rs.next()) {
            cadastro.setId(rs.getInt("id"));
            cadastro.setNome(nome);
            cadastro.setEndereco(rs.getString("endereco"));
            cadastro.setEmail(rs.getString("email"));
            cadastro.setTelefone(rs.getInt("telefone"));
            cadastros.add(cadastro);
            cadastro.limpar();
        }
        return cadastros;
    }

    public List<Cadastro> busca() throws SQLException {
        List<Cadastro> lista;
        EntityManager em = getEM();
        CriteriaQuery criteriaQuery = em.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(Cadastro.class));

        lista = em.createQuery(criteriaQuery).getResultList();
        return lista;
//        String sql = "SELECT * FROM cadastro ";
//        ps = conexao.prepareStatement(sql);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            Cadastro cadastro = new Cadastro();
//            cadastro.setNome(rs.getString("nome"));
//            cadastro.setEndereco(rs.getString("endereco"));
//            cadastro.setEmail(rs.getString("email"));
//            cadastro.setTelefone(rs.getInt("telefone"));
//            cadastro.setId(rs.getInt("id"));
//            lista.add(cadastro);
//        }
    }

    public void altera(Cadastro cadastro) throws SQLException {
        String sql = "UPDATE cadastro SET "
                + "nome = ? , "
                + "email = ? , "
                + "endereco = ? , "
                + "telefone = ? "
                + "WHERE id = ? ";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cadastro.getNome());
        stmt.setString(2, cadastro.getEmail());
        stmt.setString(3, cadastro.getEndereco());
        stmt.setInt(4, cadastro.getTelefone());
        stmt.setInt(5, cadastro.getId());
        stmt.execute();
        stmt.close();

    }
}

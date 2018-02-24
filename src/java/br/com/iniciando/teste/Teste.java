/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iniciando.teste;

import br.com.iniciando.dao.CadastroDAO;
import br.com.iniciando.dominio.Cadastro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Weverton
 */
public class Teste {
    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();
        CadastroDAO dao = new CadastroDAO();
        
        cadastro.setNome("yrmaos");
        cadastro.setEndereco("vp");
        cadastro.setEmail("yrmaos@vp");
        cadastro.setTelefone(208);
        
        dao.adicionar(cadastro);
    }
    
}

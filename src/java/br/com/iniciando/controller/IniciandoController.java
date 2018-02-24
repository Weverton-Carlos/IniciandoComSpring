/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iniciando.controller;

import br.com.iniciando.dao.CadastroDAO;
import br.com.iniciando.dominio.Cadastro;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Weverton
 */
@Controller
public class IniciandoController {
    
    @RequestMapping("/olaMundo")
    public String iniciando(Model model, Cadastro cadastro){
        
        CadastroDAO dao = new CadastroDAO();
        
        try {
            dao.adicionar(cadastro);
            model.addAttribute("retorno",cadastro.getNome());
        } catch (Exception e) {

        }

        return "index";

    }
    
    
    @RequestMapping("/cadastro")
    public String cadastro(){
        return "cadastro";
    }
    
    @RequestMapping("/lista")
    public String exibir(Model model){
        CadastroDAO dao = new CadastroDAO();
        try {
            model.addAttribute("lista",dao.busca());
        } catch (SQLException ex) {
            Logger.getLogger(IniciandoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "exibir";
    }
    @RequestMapping("/retorno")
    public @ResponseBody String retorno() throws SQLException{
        CadastroDAO dao = new CadastroDAO();
        Gson gson = new Gson();
        
        String retorno = gson.toJson(dao.busca());
        
        return retorno;
    }
    
}


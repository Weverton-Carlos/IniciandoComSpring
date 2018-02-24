/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iniciando.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Weverton
 */
@Entity
public class Cadastro {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String endereco;
    private String email;
    private int telefone;

    public Cadastro() {
    }

    public Cadastro(String nome, String enderco, String email, int telefone) {
        this.nome = nome;
        this.endereco = enderco;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cadastro{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", email=" + email + ", telefone=" + telefone + '}';
    }
    
    public void limpar(){
        this.nome = "";
        this.endereco = "";
        this.email = "";
        this.id = 0;
        this.telefone = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String enderco) {
        this.endereco = enderco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}

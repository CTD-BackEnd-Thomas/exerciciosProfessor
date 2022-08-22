package com.dh.cao.model;

import java.io.Serializable;

public class Cao implements Serializable {
    private String nome;
    private int idade;

    private Usuario usuario;

    public Cao(String nome, int idade, Usuario usuario) {
        this.nome = nome;
        this.idade = idade;
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

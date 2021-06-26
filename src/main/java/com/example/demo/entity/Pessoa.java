package com.example.demo.entity;

public class Pessoa {
    private Long codigo;
    private String nome;
    private Integer idade;

    public Pessoa(Long codigo, String nome, Integer idade) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}

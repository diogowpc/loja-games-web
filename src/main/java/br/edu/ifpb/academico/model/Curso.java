package br.edu.ifpb.academico.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Curso {
    
    private int id;
    private String nome;
    private String sigla;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFundacao;
    private String emailCoordenacao;
    private String telefoneCoordenacao;

    public Curso(){

    }
    
    public Curso(int id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getEmailCoordenacao() {
        return emailCoordenacao;
    }

    public void setEmailCoordenacao(String emailCoordenacao) {
        this.emailCoordenacao = emailCoordenacao;
    }

    public String getTelefoneCoordenacao() {
        return telefoneCoordenacao;
    }

    public void setTelefoneCoordenacao(String telefoneCoordenacao) {
        this.telefoneCoordenacao = telefoneCoordenacao;
    }

}

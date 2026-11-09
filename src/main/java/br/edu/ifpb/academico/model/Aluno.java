package br.edu.ifpb.academico.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Aluno {

    private Integer id;
    private String nome;
    private String matricula;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private Boolean possuiNecessidadeEspecifica;
    private String descricaoNecessidadeEspecifica;
    private Integer idCurso;

    public Aluno() {

    }

    public Aluno(Integer id, String nome, String matricula, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public Aluno(int id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getPossuiNecessidadeEspecifica() {
        return possuiNecessidadeEspecifica;
    }

    public void setPossuiNecessidadeEspecifica(Boolean possuiNecessidadeEspecifica) {
        this.possuiNecessidadeEspecifica = possuiNecessidadeEspecifica;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescricaoNecessidadeEspecifica() {
        return descricaoNecessidadeEspecifica;
    }

    public void setDescricaoNecessidadeEspecifica(String descricaoNecessidadeEspecifica) {
        this.descricaoNecessidadeEspecifica = descricaoNecessidadeEspecifica;
    }
    
}

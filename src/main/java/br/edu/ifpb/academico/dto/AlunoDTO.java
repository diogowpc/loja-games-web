package br.edu.ifpb.academico.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlunoDTO {
    
    private int id;
    private String nome;
    private String matricula;
    private LocalDate dataNascimento;
    private String nomeCurso;

    public AlunoDTO(){

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

    public String getDataNascimentoFormatada() {
         // Define o padrao brasileiro de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Convertendo para String
        String dataNascimentoFormatada = this.dataNascimento.format(formatter);
        return dataNascimentoFormatada;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

}

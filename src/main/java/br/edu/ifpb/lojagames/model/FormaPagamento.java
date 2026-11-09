package br.edu.ifpb.lojagames.model;

public class FormaPagamento {

    private Integer idPagamento;
    private String tipo;

    public FormaPagamento() {
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
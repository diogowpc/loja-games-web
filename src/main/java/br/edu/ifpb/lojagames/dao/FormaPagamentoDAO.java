package br.edu.ifpb.lojagames.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.lojagames.model.FormaPagamento;

@Repository
public class FormaPagamentoDAO {

    private final JdbcClient jdbcClient;

    public FormaPagamentoDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<FormaPagamento> listar() {
        return jdbcClient.sql("SELECT * FROM forma_pagamento ORDER BY id_pagamento")
                .query(FormaPagamento.class).list();
    }
}
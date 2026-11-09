package br.edu.ifpb.academico.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.academico.model.Curso;

@Repository 
public class CursoDAO {

    private final JdbcClient jdbcClient;

    public CursoDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Curso> listar() {
        return this.jdbcClient.sql("SELECT id, nome, sigla FROM curso")
                    .query(Curso.class).list();
  }

      // o retorno inteiro corresponde ao numero de linhas afetadas
    public Integer salvar(Curso curso) {
        return jdbcClient
                .sql("INSERT INTO curso (nome, sigla, data_fundacao, email_coordenacao, telefone_coordenacao) VALUES " 
                + "(?, ? , ?, ?, ?)")
                .param(curso.getNome())
                .param(curso.getSigla())
                .param(Date.valueOf(curso.getDataFundacao()))
                .param(curso.getEmailCoordenacao())
                .param(curso.getTelefoneCoordenacao())
                .update();
    }
    
}

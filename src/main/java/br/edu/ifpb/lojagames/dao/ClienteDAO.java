package br.edu.ifpb.lojagames.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.lojagames.model.Cliente;

@Repository
public class ClienteDAO {

    private final JdbcClient jdbcClient;

    public ClienteDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Cliente> listar() {
        return jdbcClient.sql("SELECT * FROM cliente ORDER BY nome")
                .query(Cliente.class).list();
    }

    // o retorno inteiro é o número de linhas afetadas
    public Integer salvar(Cliente cliente) {
        return jdbcClient
                .sql("INSERT INTO cliente (nome, cpf, telefone) VALUES (:nome, :cpf, :telefone)")
                .param("nome", cliente.getNome())
                .param("cpf", cliente.getCpf())
                .param("telefone", cliente.getTelefone())
                .update();
    }

    public Integer atualizar(Cliente cliente) {
        return jdbcClient
                .sql("UPDATE cliente SET nome = :nome, cpf = :cpf, telefone = :telefone "
                   + "WHERE id_cliente = :id")
                .param("nome", cliente.getNome())
                .param("cpf", cliente.getCpf())
                .param("telefone", cliente.getTelefone())
                .param("id", cliente.getIdCliente())
                .update();
    }

    public Cliente buscar(Integer id) {
        return jdbcClient
                .sql("SELECT * FROM cliente WHERE id_cliente = :id")
                .param("id", id)
                .query(Cliente.class)
                .optional().orElse(null);
    }

    public Integer excluir(Integer id) {
        return jdbcClient
                .sql("DELETE FROM cliente WHERE id_cliente = :id")
                .param("id", id)
                .update();
    }
}
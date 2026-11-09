package br.edu.ifpb.lojagames.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.lojagames.dto.CompraDTO;
import br.edu.ifpb.lojagames.model.Compra;

@Repository
public class CompraDAO {

    private final JdbcClient jdbcClient;

    public CompraDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // lista com o nome do cliente e o tipo de pagamento (JOIN)
    public List<CompraDTO> listarComDetalhes() {
        String consulta = "SELECT c.id_compra, c.data_venda, c.valor_total, c.status, "
                + "cl.nome AS nome_cliente, fp.tipo AS tipo_pagamento "
                + "FROM compra c "
                + "INNER JOIN cliente cl ON c.id_cliente = cl.id_cliente "
                + "INNER JOIN forma_pagamento fp ON c.id_pagamento = fp.id_pagamento "
                + "ORDER BY c.data_venda DESC, c.id_compra DESC";

        return jdbcClient.sql(consulta).query(rowMapper).list();
    }

    // o retorno inteiro é o número de linhas afetadas
    public Integer salvar(Compra compra) {
        return jdbcClient
                .sql("INSERT INTO compra (data_venda, valor_total, status, id_cliente, id_pagamento) "
                   + "VALUES (:dataVenda, :valorTotal, :status, :idCliente, :idPagamento)")
                .param("dataVenda", Date.valueOf(compra.getDataVenda()))
                .param("valorTotal", compra.getValorTotal())
                .param("status", compra.getStatus())
                .param("idCliente", compra.getIdCliente())
                .param("idPagamento", compra.getIdPagamento())
                .update();
    }

    public Integer atualizar(Compra compra) {
        return jdbcClient
                .sql("UPDATE compra SET data_venda = :dataVenda, valor_total = :valorTotal, "
                   + "status = :status, id_cliente = :idCliente, id_pagamento = :idPagamento "
                   + "WHERE id_compra = :id")
                .param("dataVenda", Date.valueOf(compra.getDataVenda()))
                .param("valorTotal", compra.getValorTotal())
                .param("status", compra.getStatus())
                .param("idCliente", compra.getIdCliente())
                .param("idPagamento", compra.getIdPagamento())
                .param("id", compra.getIdCompra())
                .update();
    }

    public Compra buscar(Integer id) {
        return jdbcClient
                .sql("SELECT * FROM compra WHERE id_compra = :id")
                .param("id", id)
                .query(Compra.class)
                .optional().orElse(null);
    }

    public Integer excluir(Integer id) {
        return jdbcClient
                .sql("DELETE FROM compra WHERE id_compra = :id")
                .param("id", id)
                .update();
    }

    // transforma cada linha do resultado do JOIN em um CompraDTO
    private final RowMapper<CompraDTO> rowMapper = (rs, rowNum) -> {
        CompraDTO dto = new CompraDTO();
        dto.setIdCompra(rs.getInt("id_compra"));
        dto.setDataVenda(rs.getObject("data_venda", LocalDate.class));
        dto.setValorTotal(rs.getBigDecimal("valor_total"));
        dto.setStatus(rs.getString("status"));
        dto.setNomeCliente(rs.getString("nome_cliente"));
        dto.setTipoPagamento(rs.getString("tipo_pagamento"));
        return dto;
    };
}
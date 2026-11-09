package br.edu.ifpb.academico.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.academico.dto.AlunoDTO;
import br.edu.ifpb.academico.model.Aluno;

@Repository 
public class AlunoDAO {

    private final JdbcClient jdbcClient;

    public AlunoDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Aluno> listar() {
        return this.jdbcClient.sql("SELECT * FROM aluno")
                    .query(Aluno.class).list();
    }

    // o retorno inteiro corresponde ao numero de linhas afetadas
    public Integer salvar(Aluno aluno) {
        return jdbcClient
                .sql("INSERT INTO aluno (nome, matricula, data_nascimento, possui_necessidade_especifica, " 
                + "descricao_necessidade_especifica, id_curso)  VALUES " 
                + "(:nome, :matricula, :data_nascimento, :possui_necessidade_especifica, :descricao_necessidade_especifica, :idCurso)")
                .param("nome", aluno.getNome())
                .param("matricula", aluno.getMatricula())
                .param("data_nascimento", Date.valueOf(aluno.getDataNascimento()))
                .param("possui_necessidade_especifica", aluno.getPossuiNecessidadeEspecifica())
                .param("descricao_necessidade_especifica", aluno.getDescricaoNecessidadeEspecifica())
                .param("idCurso", aluno.getIdCurso())
                .update();
    }

    // o retorno inteiro corresponde ao numero de linhas afetadas
    public Integer atualizar(Aluno aluno) {
        return jdbcClient
                .sql("UPDATE aluno SET nome= ?, matricula = ?, data_nascimento = ?, possui_necessidade_especifica = ?, " + 
                " descricao_necessidade_especifica = ?, id_curso = ?  " + 
                " WHERE id = ? ")
                .param(aluno.getNome())
                .param(aluno.getMatricula())
                .param(Date.valueOf(aluno.getDataNascimento()))
                .param(aluno.getPossuiNecessidadeEspecifica())
                .param(aluno.getDescricaoNecessidadeEspecifica())
                .param(aluno.getIdCurso())
                .param(aluno.getId())
                .update();
    }

    public Aluno buscar(Integer id) {
        return jdbcClient
            .sql("SELECT * FROM aluno WHERE id = :id")
            .param("id", id)
            .query(Aluno.class)
            .optional().get();
    }

    public Integer excluir(Integer id) {
        return jdbcClient.sql("DELETE FROM aluno WHERE id = :id")
                .param("id", id)
                .update();
    }

    public List<AlunoDTO> listarAlunosComCurso() throws Exception {
        String consulta = "SELECT a.id, a.nome AS nome_aluno, a.matricula, a.data_nascimento, c.nome AS nome_curso " +
                                " FROM aluno a INNER JOIN curso c ON a.id_curso = c.id";

        return this.jdbcClient.sql(consulta)
                    .query(rowMapper).list();
    }

     // RowMapper para mapear ResultSet para um objeto AlunoDTO
    private final RowMapper<AlunoDTO> rowMapper = (rs, rowNum) -> {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(rs.getInt("id"));
        alunoDTO.setNome(rs.getString("nome_aluno"));
        alunoDTO.setMatricula(rs.getString("matricula"));
        alunoDTO.setDataNascimento(rs.getObject("data_nascimento", LocalDate.class));
        alunoDTO.setNomeCurso(rs.getString("nome_curso"));
        return alunoDTO;
    };
    
}

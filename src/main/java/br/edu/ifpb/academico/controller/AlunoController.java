package br.edu.ifpb.academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.academico.dao.AlunoDAO;
import br.edu.ifpb.academico.dao.CursoDAO;
import br.edu.ifpb.academico.model.Aluno;

@Controller 
@RequestMapping ("/alunos")
public class AlunoController {

    private final AlunoDAO alunoDAO;
    private final CursoDAO cursoDAO;

    // Mensagens de erro e sucesso para exibição ao usuário
    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";
    private static final String NOME_VARIAVEL_MENSAGEM_SUCESSO = "mensagemSucesso";
    private static final String NOME_VARIAVEL_MENSAGEM_ERRO = "mensagemErro";

    public AlunoController(AlunoDAO alunoDAO, CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
        this.alunoDAO = alunoDAO;
    }

    @GetMapping
    public String listar(Model model) throws Exception {
        model.addAttribute("alunos", alunoDAO.listar());
        return "listAlunos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        try {
            model.addAttribute("cursos", cursoDAO.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cadastrarAluno";
    }

    @PostMapping
    public String salvar(@ModelAttribute Aluno aluno, RedirectAttributes redirectAttributes) {
        try {
            if (aluno.getId() == null) {
                alunoDAO.salvar(aluno);
            } else {
                alunoDAO.atualizar(aluno);
            }
            redirectAttributes.
                addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.
                addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
       
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{id}")
    public String encaminhaParaEdicao(@PathVariable Integer id, Model model) throws Exception {
        model.addAttribute("aluno", alunoDAO.buscar(id));
        try {
            model.addAttribute("cursos", cursoDAO.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "editarAluno";
    }

    @GetMapping("/excluir/{id}")
    public String encaminhaParaExclusao(@PathVariable Integer id, Model model) throws Exception {
        model.addAttribute("aluno", alunoDAO.buscar(id));

        return "excluirAluno";
    }

    @PostMapping("/excluir")
    public String excluir(@ModelAttribute Aluno aluno, RedirectAttributes redirectAttributes) {
        try {
            alunoDAO.excluir(aluno.getId());
            redirectAttributes.
                addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.
                addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
        
        return "redirect:/alunos";
    }

    @GetMapping("/listarAlunosComCurso")
    public String listarAlunosComCurso(Model model) throws Exception {
        model.addAttribute("alunosComCurso", alunoDAO.listarAlunosComCurso());
        return "listAlunosComCurso";
    }
    
}

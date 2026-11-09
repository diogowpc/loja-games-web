package br.edu.ifpb.academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.academico.dao.CursoDAO;
import br.edu.ifpb.academico.model.Curso;

@Controller 
@RequestMapping ("/cursos")
public class CursoController {

    private final CursoDAO cursoDAO;

    // Mensagens de erro e sucesso para exibição ao usuário
    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";
    private static final String NOME_VARIAVEL_MENSAGEM_SUCESSO = "mensagemSucesso";
    private static final String NOME_VARIAVEL_MENSAGEM_ERRO = "mensagemErro";

    public CursoController(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    @GetMapping
    public String listar(Model model) throws Exception {
        model.addAttribute("cursos", cursoDAO.listar());
        return "listCursos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("curso", new Curso());
     
        return "cadastrarCurso";
    }

    @PostMapping
    public String salvar(@ModelAttribute Curso curso, RedirectAttributes redirectAttributes) {
        try {
            cursoDAO.salvar(curso);
            redirectAttributes.
                addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.
                addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
       
        return "redirect:/cursos";
    }
    
}

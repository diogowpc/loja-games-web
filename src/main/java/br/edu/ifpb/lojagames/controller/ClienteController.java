package br.edu.ifpb.lojagames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.lojagames.dao.ClienteDAO;
import br.edu.ifpb.lojagames.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteDAO clienteDAO;

    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";
    private static final String NOME_VARIAVEL_MENSAGEM_SUCESSO = "mensagemSucesso";
    private static final String NOME_VARIAVEL_MENSAGEM_ERRO = "mensagemErro";

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteDAO.listar());
        return "listClientes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastrarCliente";
    }

    @PostMapping
    public String salvar(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            if (cliente.getIdCliente() == null) {
                clienteDAO.salvar(cliente);
            } else {
                clienteDAO.atualizar(cliente);
            }
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteDAO.buscar(id));
        return "editarCliente";
    }

    @GetMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteDAO.buscar(id));
        return "excluirCliente";
    }

    @PostMapping("/excluir")
    public String excluir(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteDAO.excluir(cliente.getIdCliente());
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/clientes";
    }
}
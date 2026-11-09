package br.edu.ifpb.lojagames.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.lojagames.dao.ClienteDAO;
import br.edu.ifpb.lojagames.dao.CompraDAO;
import br.edu.ifpb.lojagames.dao.FormaPagamentoDAO;
import br.edu.ifpb.lojagames.model.Compra;

@Controller
@RequestMapping("/compras")
public class CompraController {

    private final CompraDAO compraDAO;
    private final ClienteDAO clienteDAO;
    private final FormaPagamentoDAO formaPagamentoDAO;

    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";
    private static final String NOME_VARIAVEL_MENSAGEM_SUCESSO = "mensagemSucesso";
    private static final String NOME_VARIAVEL_MENSAGEM_ERRO = "mensagemErro";

    public CompraController(CompraDAO compraDAO, ClienteDAO clienteDAO,
                            FormaPagamentoDAO formaPagamentoDAO) {
        this.compraDAO = compraDAO;
        this.clienteDAO = clienteDAO;
        this.formaPagamentoDAO = formaPagamentoDAO;
    }

    // envia para a página as listas usadas nos dropdowns
    private void carregarListas(Model model) {
        model.addAttribute("clientes", clienteDAO.listar());
        model.addAttribute("formasPagamento", formaPagamentoDAO.listar());
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("compras", compraDAO.listarComDetalhes());
        return "listCompras";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        Compra compra = new Compra();
        compra.setDataVenda(LocalDate.now());
        model.addAttribute("compra", compra);
        carregarListas(model);
        return "cadastrarCompra";
    }

    @PostMapping
    public String salvar(@ModelAttribute Compra compra, RedirectAttributes redirectAttributes) {
        try {
            if (compra.getIdCompra() == null) {
                compraDAO.salvar(compra);
            } else {
                compraDAO.atualizar(compra);
            }
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/compras";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("compra", compraDAO.buscar(id));
        carregarListas(model);
        return "editarCompra";
    }

    @GetMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable Integer id, Model model) {
        model.addAttribute("compra", compraDAO.buscar(id));
        return "excluirCompra";
    }

    @PostMapping("/excluir")
    public String excluir(@ModelAttribute Compra compra, RedirectAttributes redirectAttributes) {
        try {
            compraDAO.excluir(compra.getIdCompra());
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_SUCESSO, MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(NOME_VARIAVEL_MENSAGEM_ERRO, MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/compras";
    }
}
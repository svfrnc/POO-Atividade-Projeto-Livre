package view;

import model.dao.ClienteDAO;
import model.dao.ProjetoDAO;
import model.dao.ServicoDAO;
import model.entities.Cliente;
import model.entities.Projeto;
import model.entities.Servico;
import model.enums.StatusProjeto;

import java.time.LocalDate;
import java.util.List;

public class AdminView {
    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private ServicoDAO servicoDAO = new ServicoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    // --- CLIENTES ---
    public void cadastrarCliente(String login, String senha, String nome) {
        Cliente novoCliente = new Cliente(0, login, senha, nome);
        clienteDAO.inserir(novoCliente);
    }

    // --- PROJETOS ---
    public void cadastrarProjeto(String titulo, int idCliente, LocalDate dataPrevisao) {
        Projeto novoProjeto = new Projeto(0, titulo, idCliente, StatusProjeto.PLANEJAMENTO, dataPrevisao);
        projetoDAO.inserir(novoProjeto);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoDAO.listar();
    }

    // --- CATÁLOGO DE SERVIÇOS ---
    public void cadastrarServicoBase(String descricao, double valor) {
        Servico novoServico = new Servico(0, descricao, valor);
        servicoDAO.inserir(novoServico);
    }

    public List<Servico> listarCatalogoServicos() {
        return servicoDAO.listar();
    }
}
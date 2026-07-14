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

    // ==========================================
    // --- GERENCIAMENTO DE CLIENTES (CRUD) ---
    // ==========================================
    public void cadastrarCliente(String login, String senha, String nome) {
        Cliente novoCliente = new Cliente(0, login, senha, nome);
        clienteDAO.inserir(novoCliente);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDAO.listar();
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.listar().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void atualizarCliente(Cliente cliente) {
        clienteDAO.atualizar(cliente);
    }

    public void excluirCliente(int id) {
        clienteDAO.excluir(id);
    }

    // ==========================================
    // --- GERENCIAMENTO DE PROJETOS (CRUD) ---
    // ==========================================
    public void cadastrarProjeto(String titulo, int idCliente, LocalDate dataPrevisao) {
        Projeto novoProjeto = new Projeto(0, titulo, idCliente, StatusProjeto.PLANEJAMENTO, dataPrevisao);
        projetoDAO.inserir(novoProjeto);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoDAO.listar();
    }

    public Projeto buscarProjetoPorId(int id) {
        return projetoDAO.listar().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void atualizarProjeto(Projeto projeto) {
        projetoDAO.atualizar(projeto);
    }

    public void excluirProjeto(int id) {
        projetoDAO.excluir(id);
    }

    // ==========================================
    // --- GERENCIAMENTO DE SERVIÇOS (CRUD) ---
    // ==========================================
    public void cadastrarServicoBase(String descricao, double valor) {
        Servico novoServico = new Servico(0, descricao, valor);
        servicoDAO.inserir(novoServico);
    }

    public List<Servico> listarCatalogoServicos() {
        return servicoDAO.listar();
    }

    public Servico buscarServicoPorId(int id) {
        return servicoDAO.listar().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public void atualizarServico(Servico servico) {
        servicoDAO.atualizar(servico);
    }

    public void excluirServico(int id) {
        servicoDAO.excluir(id);
    }
}
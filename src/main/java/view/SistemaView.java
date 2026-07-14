package view;

import model.dao.*;
import model.entities.*;
import model.enums.StatusProjeto;
import java.time.LocalDate;
import java.util.List;

public class SistemaView {
    // A View é a única que tem acesso aos DAOs
    private ArquitetoDAO arquitetoDAO = new ArquitetoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private EtapaDAO etapaDAO = new EtapaDAO();
    private ServicoDAO servicoDAO = new ServicoDAO();
    private EtapaServicoDAO etapaServicoDAO = new EtapaServicoDAO();

    // Semeador de dados movido para a View
    public void inicializarBancoSeVazio() {
        if (arquitetoDAO.listar().isEmpty()) {
            arquitetoDAO.inserir(new Arquiteto(0, "admin", "admin", "CAU-0000"));
            clienteDAO.inserir(new Cliente(0, "maxwell", "123", "Maxwell Dantas"));
            servicoDAO.inserir(new Servico(0, "Projeto arquitetônico", 20.00));
            servicoDAO.inserir(new Servico(0, "Reforma pedreiro", 10.00));
        }
    }

    public Usuario autenticarUsuario(String login, String senha) {
        for (Arquiteto a : arquitetoDAO.listar()) {
            if (a.getLogin().equals(login) && a.getSenha().equals(senha)) return a;
        }
        for (Cliente c : clienteDAO.listar()) {
            if (c.getLogin().equals(login) && c.getSenha().equals(senha)) return c;
        }
        return null;
    }

    // --- OPERAÇÕES DE PROJETO ---
    public void cadastrarProjeto(String titulo, int idCliente, LocalDate dataPrevisao) {
        projetoDAO.inserir(new Projeto(0, titulo, idCliente, StatusProjeto.PLANEJAMENTO, dataPrevisao));
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoDAO.listar();
    }

    public List<Projeto> listarProjetosDoCliente(int idCliente) {
        return projetoDAO.listar().stream().filter(p -> p.getIdCliente() == idCliente).toList();
    }

    public boolean aprovarProjeto(int idProjeto, int idClienteLogado) {
        Projeto p = projetoDAO.listar().stream().filter(proj -> proj.getId() == idProjeto).findFirst().orElse(null);
        if (p != null && p.getIdCliente() == idClienteLogado && p.getStatus() == StatusProjeto.PLANEJAMENTO) {
            p.setStatus(StatusProjeto.EM_EXECUCAO);
            projetoDAO.atualizar(p);
            return true;
        }
        return false;
    }

    // --- OPERAÇÕES DE ETAPA ---
    public void cadastrarEtapa(int idProjeto, String descricao, LocalDate dataLimite) {
        etapaDAO.inserir(new Etapa(0, idProjeto, descricao, dataLimite));
    }

    // --- OPERAÇÕES DE SERVIÇO (Catálogo) ---
    public void cadastrarServico(String descricao, double valor) {
        servicoDAO.inserir(new Servico(0, descricao, valor));
    }

    public List<Servico> listarCatalogoServicos() {
        return servicoDAO.listar();
    }
}
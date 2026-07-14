package view;

import model.dao.*;
import model.entities.*;
import model.enums.StatusProjeto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AdminView {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private ServicoDAO servicoDAO = new ServicoDAO();
    private EtapaDAO etapaDAO = new EtapaDAO();
    private EtapaServicoDAO etapaServicoDAO = new EtapaServicoDAO();
    private MaterialDAO materialDAO = new MaterialDAO();
    private EmpreiteiroDAO empreiteiroDAO = new EmpreiteiroDAO();

    // ==========================================
    // --- CLIENTES ---
    // ==========================================
    public void cadastrarCliente(String login, String senha, String nome) { clienteDAO.inserir(new Cliente(0, login, senha, nome)); }
    public List<Cliente> listarTodosClientes() { return clienteDAO.listar(); }
    public Cliente buscarClientePorId(int id) { return clienteDAO.listar().stream().filter(c -> c.getId() == id).findFirst().orElse(null); }
    public void atualizarCliente(Cliente cliente) { clienteDAO.atualizar(cliente); }
    public void excluirCliente(int id) { clienteDAO.excluir(id); }
    // REQUISITO: PESQUISA PARCIAL
    public List<Cliente> buscarClientePorNome(String termo) {
        return clienteDAO.listar().stream().filter(c -> c.getNome().toLowerCase().contains(termo.toLowerCase())).collect(Collectors.toList());
    }

    // ==========================================
    // --- PROJETOS ---
    // ==========================================
    public void cadastrarProjeto(String titulo, int idCliente, LocalDate dataPrevisao) { projetoDAO.inserir(new Projeto(0, titulo, idCliente, StatusProjeto.PLANEJAMENTO, dataPrevisao)); }
    public List<Projeto> listarTodosProjetos() { return projetoDAO.listar(); }
    public Projeto buscarProjetoPorId(int id) { return projetoDAO.listar().stream().filter(p -> p.getId() == id).findFirst().orElse(null); }
    public void atualizarProjeto(Projeto projeto) { projetoDAO.atualizar(projeto); }
    public void excluirProjeto(int id) { projetoDAO.excluir(id); }
    // REQUISITO: PESQUISA PARCIAL
    public List<Projeto> buscarProjetoPorTitulo(String termo) {
        return projetoDAO.listar().stream().filter(p -> p.getTitulo().toLowerCase().contains(termo.toLowerCase())).collect(Collectors.toList());
    }

    // ==========================================
    // --- ETAPAS ---
    // ==========================================
    public void cadastrarEtapa(int idProjeto, String descricao, LocalDate dataLimite) { etapaDAO.inserir(new Etapa(0, idProjeto, descricao, dataLimite)); }
    public List<Etapa> listarTodasEtapas() { return etapaDAO.listar(); }
    public Etapa buscarEtapaPorId(int id) { return etapaDAO.listar().stream().filter(e -> e.getId() == id).findFirst().orElse(null); }
    public void atualizarEtapa(Etapa etapa) { etapaDAO.atualizar(etapa); }
    public void excluirEtapa(int id) { etapaDAO.excluir(id); }

    // ==========================================
    // --- SERVIÇOS (CATÁLOGO) ---
    // ==========================================
    public void cadastrarServicoBase(String descricao, double valor) { servicoDAO.inserir(new Servico(0, descricao, valor)); }
    public List<Servico> listarCatalogoServicos() { return servicoDAO.listar(); }
    public Servico buscarServicoPorId(int id) { return servicoDAO.listar().stream().filter(s -> s.getId() == id).findFirst().orElse(null); }
    public void atualizarServico(Servico servico) { servicoDAO.atualizar(servico); }
    public void excluirServico(int id) { servicoDAO.excluir(id); }
    // REQUISITO: PESQUISA PARCIAL
    public List<Servico> buscarServicoPorDescricao(String termo) {
        return servicoDAO.listar().stream().filter(s -> s.getDescricao().toLowerCase().contains(termo.toLowerCase())).collect(Collectors.toList());
    }

    // ==========================================
    // --- VÍNCULOS (ETAPA X SERVIÇO) ---
    // ==========================================
    public void vincularServico(int idEtapa, int idServico, double quantidade) { etapaServicoDAO.inserir(new EtapaServico(0, idEtapa, idServico, quantidade)); }
    public List<EtapaServico> listarVinculos() { return etapaServicoDAO.listar(); }
    public EtapaServico buscarVinculoPorId(int id) { return etapaServicoDAO.listar().stream().filter(v -> v.getId() == id).findFirst().orElse(null); }
    public void excluirVinculo(int id) { etapaServicoDAO.excluir(id); }

    // ==========================================
    // --- MATERIAIS ---
    // ==========================================
    public void cadastrarMaterial(int idEtapa, String descricao, double quantidade, String unidade) { materialDAO.inserir(new Material(0, idEtapa, descricao, quantidade, unidade)); }
    public List<Material> listarMateriais() { return materialDAO.listar(); }
    public Material buscarMaterialPorId(int id) { return materialDAO.listar().stream().filter(m -> m.getId() == id).findFirst().orElse(null); }
    public void atualizarMaterial(Material m) { materialDAO.atualizar(m); }
    public void excluirMaterial(int id) { materialDAO.excluir(id); }

    // ==========================================
    // --- EMPREITEIROS ---
    // ==========================================
    public void cadastrarEmpreiteiro(int idEtapa, String nome, String cnpj) { empreiteiroDAO.inserir(new Empreiteiro(0, idEtapa, nome, cnpj)); }
    public List<Empreiteiro> listarEmpreiteiros() { return empreiteiroDAO.listar(); }
    public Empreiteiro buscarEmpreiteiroPorId(int id) { return empreiteiroDAO.listar().stream().filter(e -> e.getId() == id).findFirst().orElse(null); }
    public void atualizarEmpreiteiro(Empreiteiro e) { empreiteiroDAO.atualizar(e); }
    public void excluirEmpreiteiro(int id) { empreiteiroDAO.excluir(id); }
}
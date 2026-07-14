package view;

import model.dao.ArquitetoDAO;
import model.dao.ClienteDAO;
import model.dao.ServicoDAO;
import model.entities.Arquiteto;
import model.entities.Cliente;
import model.entities.Servico;
import model.entities.Usuario;

public class AuthView {
    private ArquitetoDAO arquitetoDAO = new ArquitetoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ServicoDAO servicoDAO = new ServicoDAO();

    // Controle de Sessão Global
    private static Usuario usuarioLogado = null;

    public void inicializarBancoSeVazio() {
        if (arquitetoDAO.listar().isEmpty()) {
            arquitetoDAO.inserir(new Arquiteto(0, "admin", "admin", "CAU-0000"));
        }
    }

    public boolean login(String login, String senha) {
        for (Arquiteto a : arquitetoDAO.listar()) {
            if (a.getLogin().equals(login) && a.getSenha().equals(senha)) {
                usuarioLogado = a;
                return true;
            }
        }
        for (Cliente c : clienteDAO.listar()) {
            if (c.getLogin().equals(login) && c.getSenha().equals(senha)) {
                usuarioLogado = c;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
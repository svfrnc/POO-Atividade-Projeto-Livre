package view;

import model.dao.ProjetoDAO;
import model.entities.Projeto;
import model.enums.StatusProjeto;

import java.util.List;

public class ClienteView {
    private ProjetoDAO projetoDAO = new ProjetoDAO();

    public List<Projeto> listarMeusProjetos(int idCliente) {
        return projetoDAO.listar().stream()
                .filter(p -> p.getIdCliente() == idCliente)
                .toList();
    }

    // REGRA DE NEGÓCIO: Só aprova se for o dono e estiver em planejamento
    public boolean aprovarProjeto(int idProjeto, int idClienteLogado) {
        List<Projeto> projetos = projetoDAO.listar();
        for (Projeto p : projetos) {
            if (p.getId() == idProjeto && p.getIdCliente() == idClienteLogado) {
                if (p.getStatus() == StatusProjeto.PLANEJAMENTO) {
                    p.setStatus(StatusProjeto.EM_EXECUCAO);
                    projetoDAO.atualizar(p);
                    return true;
                }
            }
        }
        return false;
    }
}
package view;

import model.dao.EtapaDAO;
import model.dao.ProjetoDAO;
import model.entities.Etapa;
import model.entities.Projeto;
import model.enums.StatusProjeto;

import java.time.LocalDate;
import java.util.List;

public class ClienteView {
    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private EtapaDAO etapaDAO = new EtapaDAO(); // Adicionado para a regra de negócio

    public List<Projeto> listarMeusProjetos(int idCliente) {
        return projetoDAO.listar().stream()
                .filter(p -> p.getIdCliente() == idCliente)
                .toList();
    }

    public boolean aprovarProjeto(int idProjeto, int idClienteLogado) {
        List<Projeto> projetos = projetoDAO.listar();

        for (Projeto p : projetos) {
            // Verifica se o projeto existe, se pertence ao cliente logado e se está pendente
            if (p.getId() == idProjeto && p.getIdCliente() == idClienteLogado) {
                if (p.getStatus() == StatusProjeto.PLANEJAMENTO) {

                    // Ação 1: Atualiza a Entidade Projeto
                    p.setStatus(StatusProjeto.EM_EXECUCAO);
                    projetoDAO.atualizar(p);

                    // Ação 2: Insere na Entidade Etapa (Gerando uma etapa automática para 7 dias após a aprovação)
                    Etapa etapaAutomatica = new Etapa(
                            0,
                            p.getId(),
                            "Reunião de Kick-off e Assinatura de Contratos",
                            LocalDate.now().plusDays(7)
                    );
                    etapaDAO.inserir(etapaAutomatica);

                    return true;
                }
            }
        }
        return false;
    }
}
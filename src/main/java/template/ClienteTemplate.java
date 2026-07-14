package template;

import model.entities.Projeto;
import model.enums.StatusProjeto;
import view.AuthView;
import view.ClienteView;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClienteTemplate {
    private ClienteView clienteView = new ClienteView();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            ConsoleUtil.pularLinha();
            System.out.println("=====================================");
            System.out.println("=== PAINEL DO CLIENTE (" + AuthView.getUsuarioLogado().getLogin() + ") ===");
            System.out.println("=====================================");
            System.out.println("1. Visualizar Meus Projetos");
            System.out.println("2. Aprovar Início de Projeto");
            System.out.println("0. Fazer Logout");
            opcao = ConsoleUtil.lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> listarProjetos();
                    case 2 -> aprovarProjeto();
                    case 0 -> System.out.println("Saindo do painel cliente...");
                    default -> System.out.println("❌ Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }

    private void listarProjetos() {
        System.out.println("\n-- MEUS PROJETOS --");
        int idLogado = AuthView.getUsuarioLogado().getId();
        List<Projeto> meus = clienteView.listarMeusProjetos(idLogado);

        if (meus.isEmpty()) {
            System.out.println("ℹ️ Nenhum projeto vinculado a você no momento.");
        } else {
            for (Projeto p : meus) {
                String rotuloData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Concluído em" : "Entrega Prevista";
                System.out.printf("ID: %d | Título: %s | Status: %s | %s: %s\n",
                        p.getId(), p.getTitulo(), p.getStatus(), rotuloData, p.getDataPrevisaoConclusao().format(dateFormatter));
            }
        }
    }

    private void aprovarProjeto() {
        System.out.println("\n-- APROVAÇÃO DE ESCOPO --");
        int idLogado = AuthView.getUsuarioLogado().getId();

        List<Projeto> pendentes = clienteView.listarMeusProjetos(idLogado).stream()
                .filter(p -> p.getStatus() == StatusProjeto.PLANEJAMENTO)
                .toList();

        if (pendentes.isEmpty()) {
            System.out.println("ℹ️ Você não possui nenhum projeto aguardando aprovação no momento.");
            return;
        }

        System.out.println("Projetos aguardando sua aprovação:");
        pendentes.forEach(p -> System.out.printf("ID: %d | Título: %s\n", p.getId(), p.getTitulo()));
        ConsoleUtil.pularLinha();

        int idProjeto = ConsoleUtil.lerInt("Digite o ID do Projeto que deseja aprovar (0 para cancelar): ");
        if (idProjeto == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (clienteView.aprovarProjeto(idProjeto, idLogado)) {
            System.out.println("✅ Sucesso! O projeto foi APROVADO e a Etapa de Kick-off foi agendada.");
        } else {
            System.out.println("❌ Falha. Verifique se o ID está correto.");
        }
    }
}
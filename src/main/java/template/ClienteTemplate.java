package template;

import model.entities.Projeto;
import view.AuthView;
import view.ClienteView;

public class ClienteTemplate {
    private ClienteView clienteView = new ClienteView();

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            ConsoleUtil.pularLinha();
            System.out.println("=== PAINEL DO CLIENTE (" + AuthView.getUsuarioLogado().getLogin() + ") ===");
            System.out.println("1. Visualizar Meus Projetos");
            System.out.println("2. Aprovar Início de Projeto");
            System.out.println("0. Fazer Logout");
            opcao = ConsoleUtil.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> listarProjetos();
                case 2 -> aprovarProjeto();
                case 0 -> System.out.println("Saindo do painel cliente...");
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    private void listarProjetos() {
        System.out.println("\n-- MEUS PROJETOS --");
        int idLogado = AuthView.getUsuarioLogado().getId();
        for (Projeto p : clienteView.listarMeusProjetos(idLogado)) {
            System.out.printf("ID: %d | Título: %s | Entrega: %s | Status: %s\n",
                    p.getId(), p.getTitulo(), p.getDataPrevisaoConclusao(), p.getStatus());
        }
    }

    private void aprovarProjeto() {
        System.out.println("\n-- APROVAÇÃO DE ESCOPO --");
        int idLogado = AuthView.getUsuarioLogado().getId();
        int idProjeto = ConsoleUtil.lerInt("Digite o ID do Projeto que deseja aprovar: ");

        if (clienteView.aprovarProjeto(idProjeto, idLogado)) {
            System.out.println("✅ Sucesso! O projeto foi APROVADO e as obras podem iniciar.");
        } else {
            System.out.println("❌ Falha. Verifique se o ID está correto ou se o projeto já não foi aprovado.");
        }
    }
}
package template;

import view.AuthView;
import model.enums.TipoPerfil;

public class AuthTemplate {
    private AuthView authView = new AuthView();
    private ArquitetoTemplate arquitetoTemplate = new ArquitetoTemplate();
    private ClienteTemplate clienteTemplate = new ClienteTemplate();

    public void iniciar() {
        authView.inicializarBancoSeVazio();

        int opcao = -1;
        while (opcao != 0) {
            ConsoleUtil.pularLinha();
            System.out.println("=====================================");
            System.out.println("        SISTEMA ARQBUILD v1.0        ");
            System.out.println("=====================================");
            System.out.println("1. Entrar");
            System.out.println("0. Sair");

            opcao = ConsoleUtil.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> realizarLogin();
                case 0 -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }

    private void realizarLogin() {
        ConsoleUtil.pularLinha();
        System.out.println("--- LOGIN ---");
        String login = ConsoleUtil.lerString("Login: ");
        String senha = ConsoleUtil.lerString("Senha: ");

        if (authView.login(login, senha)) {
            System.out.println("✅ Autenticado com sucesso!");
            direcionarMenu();
        } else {
            System.out.println("❌ Login ou senha incorretos. Tente novamente.");
        }
    }

    private void direcionarMenu() {
        TipoPerfil perfil = AuthView.getUsuarioLogado().getTipoPerfil();

        if (perfil == TipoPerfil.ADMIN) {
            arquitetoTemplate.exibirMenu();
        } else if (perfil == TipoPerfil.COMUM) {
            clienteTemplate.exibirMenu();
        }

        authView.logout();
        System.out.println("Logout realizado com sucesso.");
    }
}
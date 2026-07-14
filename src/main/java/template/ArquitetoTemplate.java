package template;

import model.entities.Projeto;
import model.entities.Servico;
import view.AdminView;

public class ArquitetoTemplate {
    private AdminView adminView = new AdminView();

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            ConsoleUtil.pularLinha();
            System.out.println("=== PAINEL DO ARQUITETO (ADMIN) ===");
            System.out.println("1. Cadastrar Novo Cliente");
            System.out.println("2. Cadastrar Novo Projeto");
            System.out.println("3. Listar Todos os Projetos");
            System.out.println("4. Cadastrar Serviço no Catálogo");
            System.out.println("5. Listar Catálogo de Serviços");
            System.out.println("0. Fazer Logout");
            opcao = ConsoleUtil.lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> cadastrarProjeto();
                    case 3 -> listarProjetos();
                    case 4 -> cadastrarServico();
                    case 5 -> listarServicos();
                    case 0 -> System.out.println("Saindo do painel admin...");
                    default -> System.out.println("❌ Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Erro de Validação: " + e.getMessage());
            }
        }
    }

    private void cadastrarCliente() {
        System.out.println("\n-- CADASTRAR NOVO CLIENTE --");
        String login = ConsoleUtil.lerString("Login do Cliente: ");
        String senha = ConsoleUtil.lerString("Senha Provisória: ");
        String nome = ConsoleUtil.lerString("Nome Completo: ");

        adminView.cadastrarCliente(login, senha, nome);
        System.out.println("✅ Cliente registrado com sucesso no sistema!");
    }

    private void cadastrarProjeto() {
        System.out.println("\n-- NOVO PROJETO --");
        String titulo = ConsoleUtil.lerString("Título do Projeto: ");
        int idCliente = ConsoleUtil.lerInt("ID do Cliente Vinculado: ");
        var dataPrev = ConsoleUtil.lerData("Data de Previsão de Entrega (dd/MM/yyyy): ");

        adminView.cadastrarProjeto(titulo, idCliente, dataPrev);
        System.out.println("✅ Projeto cadastrado com sucesso!");
    }

    private void listarProjetos() {
        System.out.println("\n-- LISTA GLOBAL DE PROJETOS --");
        for (Projeto p : adminView.listarTodosProjetos()) {
            System.out.printf("ID: %d | Cliente ID: %d | Título: %s | Status: %s\n",
                    p.getId(), p.getIdCliente(), p.getTitulo(), p.getStatus());
        }
    }

    private void cadastrarServico() {
        System.out.println("\n-- NOVO SERVIÇO NO CATÁLOGO --");
        String desc = ConsoleUtil.lerString("Descrição do Serviço: ");
        double valor = ConsoleUtil.lerDouble("Valor base por medida (R$): ");

        adminView.cadastrarServicoBase(desc, valor);
        System.out.println("✅ Serviço adicionado ao catálogo!");
    }

    private void listarServicos() {
        System.out.println("\n-- CATÁLOGO DE SERVIÇOS --");
        for (Servico s : adminView.listarCatalogoServicos()) {
            System.out.printf("ID: %d | Descrição: %s | Valor M.: R$%.2f\n",
                    s.getId(), s.getDescricao(), s.getValorMedida());
        }
    }
}
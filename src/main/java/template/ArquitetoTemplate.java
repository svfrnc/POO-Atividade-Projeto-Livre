package template;

import model.entities.Cliente;
import model.entities.Projeto;
import model.entities.Servico;
import model.enums.StatusProjeto;
import view.AdminView;

public class ArquitetoTemplate {
    private AdminView adminView = new AdminView();

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            ConsoleUtil.pularLinha();
            System.out.println("=====================================");
            System.out.println("    PAINEL DO ARQUITETO (ADMIN)      ");
            System.out.println("=====================================");
            System.out.println("--- CLIENTES ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("--- PROJETOS ---");
            System.out.println("5. Cadastrar Projeto");
            System.out.println("6. Listar Projetos");
            System.out.println("7. Editar Projeto");
            System.out.println("8. Excluir Projeto");
            System.out.println("--- CATÁLOGO DE SERVIÇOS ---");
            System.out.println("9. Cadastrar Serviço");
            System.out.println("10. Listar Serviços");
            System.out.println("11. Editar Serviço");
            System.out.println("12. Excluir Serviço");
            System.out.println("0. Fazer Logout");

            opcao = ConsoleUtil.lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> listarClientes();
                    case 3 -> editarCliente();
                    case 4 -> excluirCliente();

                    case 5 -> cadastrarProjeto();
                    case 6 -> listarProjetos();
                    case 7 -> editarProjeto();
                    case 8 -> excluirProjeto();

                    case 9 -> cadastrarServico();
                    case 10 -> listarServicos();
                    case 11 -> editarServico();
                    case 12 -> excluirServico();

                    case 0 -> System.out.println("Saindo do painel admin...");
                    default -> System.out.println("❌ Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Erro de Validação: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    // ==========================================
    // MÉTODOS DE TELA: CLIENTES
    // ==========================================
    private void cadastrarCliente() {
        System.out.println("\n-- CADASTRAR NOVO CLIENTE --");
        String login = ConsoleUtil.lerString("Login do Cliente: ");
        String senha = ConsoleUtil.lerString("Senha Provisória: ");
        String nome = ConsoleUtil.lerString("Nome Completo: ");

        adminView.cadastrarCliente(login, senha, nome);
        System.out.println("✅ Cliente registrado com sucesso no sistema!");
    }

    private void listarClientes() {
        System.out.println("\n-- LISTA DE CLIENTES --");
        for (Cliente c : adminView.listarTodosClientes()) {
            System.out.printf("ID: %d | Nome: %s | Login: %s\n", c.getId(), c.getNome(), c.getLogin());
        }
    }

    private void editarCliente() {
        System.out.println("\n-- EDITAR CLIENTE --");
        listarClientes();
        ConsoleUtil.pularLinha();

        int id = ConsoleUtil.lerInt("Digite o ID do Cliente que deseja editar: ");
        Cliente c = adminView.buscarClientePorId(id);

        if (c == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }

        System.out.println("Deixe em branco caso não queira alterar o campo.");
        String novoNome = ConsoleUtil.lerString("Novo Nome [" + c.getNome() + "]: ");
        String novaSenha = ConsoleUtil.lerString("Nova Senha: ");

        if (!novoNome.isEmpty()) c.setNome(novoNome);
        if (!novaSenha.isEmpty()) c.setSenha(novaSenha);

        adminView.atualizarCliente(c);
        System.out.println("✅ Cliente atualizado com sucesso!");
    }

    private void excluirCliente() {
        System.out.println("\n-- EXCLUIR CLIENTE --");
        listarClientes();
        ConsoleUtil.pularLinha();

        int id = ConsoleUtil.lerInt("Digite o ID do Cliente que deseja excluir: ");
        Cliente c = adminView.buscarClientePorId(id);

        if (c == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }

        adminView.excluirCliente(id);
        System.out.println("✅ Cliente excluído com sucesso!");
    }

    // ==========================================
    // MÉTODOS DE TELA: PROJETOS
    // ==========================================
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

    private void editarProjeto() {
        System.out.println("\n-- EDITAR PROJETO --");
        listarProjetos();
        ConsoleUtil.pularLinha();

        int id = ConsoleUtil.lerInt("Digite o ID do Projeto que deseja editar: ");
        Projeto p = adminView.buscarProjetoPorId(id);

        if (p == null) {
            System.out.println("❌ Projeto não encontrado!");
            return;
        }

        String novoTitulo = ConsoleUtil.lerString("Novo Título [" + p.getTitulo() + "] (Ou enter para manter): ");
        if (!novoTitulo.isEmpty()) p.setTitulo(novoTitulo);

        System.out.println("Status atual: " + p.getStatus());
        System.out.println("1. PLANEJAMENTO | 2. APROVADO | 3. EM EXECUCAO | 4. CONCLUIDO | 0. Manter Atual");
        int opStatus = ConsoleUtil.lerInt("Escolha o novo status: ");
        switch (opStatus) {
            case 1 -> p.setStatus(StatusProjeto.PLANEJAMENTO);
            case 2 -> p.setStatus(StatusProjeto.APROVADO);
            case 3 -> p.setStatus(StatusProjeto.EM_EXECUCAO);
            case 4 -> p.setStatus(StatusProjeto.CONCLUIDO);
        }

        adminView.atualizarProjeto(p);
        System.out.println("✅ Projeto atualizado com sucesso!");
    }

    private void excluirProjeto() {
        System.out.println("\n-- EXCLUIR PROJETO --");
        listarProjetos();
        ConsoleUtil.pularLinha();

        int id = ConsoleUtil.lerInt("Digite o ID do Projeto que deseja excluir: ");
        Projeto p = adminView.buscarProjetoPorId(id);

        if (p == null) {
            System.out.println("❌ Projeto não encontrado!");
            return;
        }

        adminView.excluirProjeto(id);
        System.out.println("✅ Projeto excluído com sucesso!");
    }

    // ==========================================
    // MÉTODOS DE TELA: SERVIÇOS
    // ==========================================
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

    private void editarServico() {
        System.out.println("\n-- EDITAR SERVIÇO --");
        listarServicos(); // UX: Mostra o catálogo de serviços antes de pedir o ID
        ConsoleUtil.pularLinha();

        int id = ConsoleUtil.lerInt("Digite o ID do Serviço que deseja editar: ");
        Servico s = adminView.buscarServicoPorId(id);

        if (s == null) {
            System.out.println("❌ Serviço não encontrado!");
            return;
        }

        System.out.println("Deixe em branco caso não queira alterar o campo.");
        String novaDescricao = ConsoleUtil.lerString("Nova Descrição [" + s.getDescricao() + "]: ");

        // Para o double, usamos uma String temporária para saber se o utilizador premiu Enter
        String valorStr = ConsoleUtil.lerString("Novo Valor por Medida [R$" + String.format("%.2f", s.getValorMedida()) + "]: ");

        if (!novaDescricao.isEmpty()) s.setDescricao(novaDescricao);
        if (!valorStr.isEmpty()) {
            try {
                double novoValor = Double.parseDouble(valorStr.replace(",", "."));
                s.setValorMedida(novoValor);
            } catch (NumberFormatException e) {
                System.out.println("❌ Valor inválido introduzido. O valor antigo foi mantido.");
            }
        }

        adminView.atualizarServico(s);
        System.out.println("✅ Serviço atualizado com sucesso!");
    }

    private void excluirServico() {
        System.out.println("\n-- EXCLUIR SERVIÇO --");
        listarServicos(); // UX: Mostra o catálogo antes de pedir o ID
        ConsoleUtil.pularLinha();

        int id = ConsoleUtil.lerInt("Digite o ID do Serviço que deseja remover do catálogo: ");
        Servico s = adminView.buscarServicoPorId(id);

        if (s == null) {
            System.out.println("❌ Serviço não encontrado!");
            return;
        }

        adminView.excluirServico(id);
        System.out.println("✅ Serviço removido do catálogo com sucesso!");
    }
}
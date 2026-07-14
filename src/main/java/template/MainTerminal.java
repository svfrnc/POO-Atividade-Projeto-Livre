package template;

import model.entities.Projeto;
import model.entities.Servico;
import model.entities.Usuario;
import view.SistemaView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainTerminal {
    private static Scanner scanner = new Scanner(System.in);
    // O Template conversa apenas com a View
    private static SistemaView view = new SistemaView();
    private static Usuario usuarioLogado = null;
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        // Delega a verificação do banco para a View
        view.inicializarBancoSeVazio();

        while (true) {
            if (usuarioLogado == null) {
                telaLogin();
            } else {
                switch (usuarioLogado.getTipoPerfil()) {
                    case ADMIN -> menuArquiteto();
                    case COMUM -> menuCliente();
                }
            }
        }
    }

    private static void telaLogin() {
        System.out.println("\n=== ARQBUILD - LOGIN ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Template pede para a View autenticar
        usuarioLogado = view.autenticarUsuario(login, senha);

        if (usuarioLogado == null) {
            System.out.println("[ERRO] Credenciais inválidas.");
        } else {
            System.out.println("✅ Bem-vindo, " + usuarioLogado.getLogin() + "!");
        }
    }

    private static void menuArquiteto() {
        System.out.println("\n=== PAINEL ADMIN (ARQUITETO) ===");
        System.out.println("1. Cadastrar Projeto");
        System.out.println("2. Cadastrar Etapa no Projeto");
        System.out.println("3. Listar Todos os Projetos");
        System.out.println("4. Cadastrar Serviço no Catálogo");
        System.out.println("5. Listar Catálogo de Serviços");
        System.out.println("0. Sair");
        System.out.print("Opção: ");

        try {
            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.print("Título do Projeto: ");
                    String titulo = scanner.nextLine();
                    System.out.print("ID do Cliente: ");
                    int idCli = Integer.parseInt(scanner.nextLine());
                    System.out.print("Data de Previsão de Conclusão (dd/MM/yyyy): ");
                    LocalDate dataPrev = LocalDate.parse(scanner.nextLine(), dateFormatter);

                    // Delega a inserção para a View
                    view.cadastrarProjeto(titulo, idCli, dataPrev);
                    System.out.println("✅ Projeto cadastrado!");
                }
                case "2" -> {
                    System.out.print("ID do Projeto: ");
                    int idProj = Integer.parseInt(scanner.nextLine());
                    System.out.print("Descrição da Etapa: ");
                    String desc = scanner.nextLine();
                    System.out.print("Data Limite da Etapa (dd/MM/yyyy): ");
                    LocalDate dataLimite = LocalDate.parse(scanner.nextLine(), dateFormatter);

                    // Delega para a View
                    view.cadastrarEtapa(idProj, desc, dataLimite);
                    System.out.println("✅ Etapa cadastrada!");
                }
                case "3" -> {
                    System.out.println("\n--- Projetos ---");
                    // Pede a lista para a View
                    for (Projeto p : view.listarTodosProjetos()) {
                        System.out.println("ID: " + p.getId() + " | Título: " + p.getTitulo() + " | Previsão: " + p.getDataPrevisaoConclusao().format(dateFormatter) + " | Status: " + p.getStatus());
                    }
                }
                case "4" -> {
                    System.out.print("Descrição do Serviço: ");
                    String desc = scanner.nextLine();
                    System.out.print("Valor por Medida (Ex: 20.50): ");
                    double valor = Double.parseDouble(scanner.nextLine());

                    view.cadastrarServico(desc, valor);
                    System.out.println("✅ Serviço cadastrado!");
                }
                case "5" -> {
                    System.out.println("\n--- Catálogo de Serviços ---");
                    for (Servico s : view.listarCatalogoServicos()) {
                        System.out.println("ID: " + s.getId() + " | " + s.getDescricao() + " | R$" + s.getValorMedida());
                    }
                }
                case "0" -> usuarioLogado = null;
                default -> System.out.println("Opção inválida.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido! Use dd/MM/yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro de Validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Entrada inválida.");
        }
    }

    private static void menuCliente() {
        System.out.println("\n=== PAINEL DO CLIENTE ===");
        System.out.println("1. Meus Projetos");
        System.out.println("2. Aprovar Projeto");
        System.out.println("0. Sair");
        System.out.print("Opção: ");

        switch (scanner.nextLine()) {
            case "1" -> {
                System.out.println("\n--- Seus Projetos ---");
                for (Projeto p : view.listarProjetosDoCliente(usuarioLogado.getId())) {
                    System.out.println("ID: " + p.getId() + " | " + p.getTitulo() + " | Previsão: " + p.getDataPrevisaoConclusao().format(dateFormatter) + " | Status: " + p.getStatus());
                }
            }
            case "2" -> {
                System.out.print("ID do Projeto para aprovar: ");
                try {
                    int idProj = Integer.parseInt(scanner.nextLine());
                    if (view.aprovarProjeto(idProj, usuarioLogado.getId())) {
                        System.out.println("✅ Projeto APROVADO! Obras podem iniciar.");
                    } else {
                        System.out.println("❌ Falha. Verifique o ID ou se já foi aprovado.");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida.");
                }
            }
            case "0" -> usuarioLogado = null;
        }
    }
}
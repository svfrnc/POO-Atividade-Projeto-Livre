package template;

import model.entities.*;
import model.enums.StatusProjeto;
import view.AdminView;

import java.util.List;

public class ArquitetoTemplate {
    private AdminView adminView = new AdminView();

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            ConsoleUtil.pularLinha();
            System.out.println("=====================================");
            System.out.println("      PAINEL DO ARQUITETO (ADMIN)    ");
            System.out.println("=====================================");
            System.out.println("1. Módulo de Clientes");
            System.out.println("2. Módulo de Projetos");
            System.out.println("3. Módulo de Etapas");
            System.out.println("4. Módulo de Serviços (Catálogo)");
            System.out.println("5. Módulo de Vínculos (Etapa-Serviço)");
            System.out.println("6. Módulo de Materiais");
            System.out.println("7. Módulo de Empreiteiros");
            System.out.println("0. Fazer Logout");

            opcao = ConsoleUtil.lerInt("Escolha um módulo: ");

            try {
                switch (opcao) {
                    case 1 -> menuClientes();
                    case 2 -> menuProjetos();
                    case 3 -> menuEtapas();
                    case 4 -> menuServicos();
                    case 5 -> menuVinculos();
                    case 6 -> menuMateriais();
                    case 7 -> menuEmpreiteiros();
                    case 0 -> System.out.println("Saindo do painel admin...");
                    default -> System.out.println("❌ Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }

    // ==========================================
    // 1. MÓDULO DE CLIENTES
    // ==========================================
    private void menuClientes() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE CLIENTES ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar | 4. Excluir | 5. Buscar por Nome | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    String login = ConsoleUtil.lerString("Login: ");
                    String senha = ConsoleUtil.lerString("Senha: ");
                    String nome = ConsoleUtil.lerString("Nome Completo: ");
                    adminView.cadastrarCliente(login, senha, nome);
                    System.out.println("✅ Salvo com sucesso!");
                }
                case 2 -> listarClientes();
                case 3 -> {
                    listarClientes();
                    Cliente c = adminView.buscarClientePorId(ConsoleUtil.lerInt("ID para Editar: "));
                    if(c != null) {
                        String nNome = ConsoleUtil.lerString("Novo Nome ["+c.getNome()+"]: ");
                        if(!nNome.isEmpty()) c.setNome(nNome);
                        adminView.atualizarCliente(c);
                        System.out.println("✅ Atualizado!");
                    }
                }
                case 4 -> {
                    listarClientes();
                    adminView.excluirCliente(ConsoleUtil.lerInt("ID para Excluir: "));
                    System.out.println("✅ Excluído!");
                }
                case 5 -> {
                    String termo = ConsoleUtil.lerString("Digite parte do nome: ");
                    List<Cliente> res = adminView.buscarClientePorNome(termo);
                    res.forEach(c -> System.out.printf("ID: %d | Nome: %s\n", c.getId(), c.getNome()));
                }
            }
        }
    }
    private void listarClientes() {
        adminView.listarTodosClientes().forEach(c -> System.out.printf("ID: %d | Nome: %s\n", c.getId(), c.getNome()));
    }

    // ==========================================
    // 2. MÓDULO DE PROJETOS
    // ==========================================
    private void menuProjetos() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE PROJETOS ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar | 4. Excluir | 5. Buscar por Título | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    String titulo = ConsoleUtil.lerString("Título: ");
                    int idCli = ConsoleUtil.lerInt("ID do Cliente: ");
                    var data = ConsoleUtil.lerData("Data Previsão (dd/MM/yyyy): ");
                    adminView.cadastrarProjeto(titulo, idCli, data);
                    System.out.println("✅ Salvo!");
                }
                case 2 -> listarProjetos();
                case 3 -> {
                    listarProjetos();
                    Projeto p = adminView.buscarProjetoPorId(ConsoleUtil.lerInt("ID para Editar: "));
                    if(p != null) {
                        String nTit = ConsoleUtil.lerString("Novo Título ["+p.getTitulo()+"]: ");
                        if(!nTit.isEmpty()) p.setTitulo(nTit);
                        adminView.atualizarProjeto(p);
                        System.out.println("✅ Atualizado!");
                    }
                }
                case 4 -> {
                    listarProjetos();
                    adminView.excluirProjeto(ConsoleUtil.lerInt("ID para Excluir: "));
                    System.out.println("✅ Excluído!");
                }
                case 5 -> {
                    String termo = ConsoleUtil.lerString("Digite parte do título: ");
                    List<Projeto> res = adminView.buscarProjetoPorTitulo(termo);
                    res.forEach(p -> System.out.printf("ID: %d | Título: %s\n", p.getId(), p.getTitulo()));
                }
            }
        }
    }
    private void listarProjetos() {
        adminView.listarTodosProjetos().forEach(p -> System.out.printf("ID: %d | Cliente ID: %d | Titulo: %s\n", p.getId(), p.getIdCliente(), p.getTitulo()));
    }

    // ==========================================
    // 3. MÓDULO DE ETAPAS
    // ==========================================
    private void menuEtapas() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE ETAPAS ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar | 4. Excluir | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    int idProj = ConsoleUtil.lerInt("ID do Projeto: ");
                    String desc = ConsoleUtil.lerString("Descrição da Etapa: ");
                    var data = ConsoleUtil.lerData("Data Limite (dd/MM/yyyy): ");
                    adminView.cadastrarEtapa(idProj, desc, data);
                    System.out.println("✅ Salvo!");
                }
                case 2 -> listarEtapas();
                case 3 -> {
                    listarEtapas();
                    Etapa e = adminView.buscarEtapaPorId(ConsoleUtil.lerInt("ID para Editar: "));
                    if(e != null) {
                        String nDesc = ConsoleUtil.lerString("Nova Descrição ["+e.getDescricao()+"]: ");
                        if(!nDesc.isEmpty()) e.setDescricao(nDesc);
                        adminView.atualizarEtapa(e);
                        System.out.println("✅ Atualizado!");
                    }
                }
                case 4 -> {
                    listarEtapas();
                    adminView.excluirEtapa(ConsoleUtil.lerInt("ID para Excluir: "));
                    System.out.println("✅ Excluído!");
                }
            }
        }
    }
    private void listarEtapas() {
        adminView.listarTodasEtapas().forEach(e -> System.out.printf("ID: %d | Proj ID: %d | Desc: %s\n", e.getId(), e.getIdProjeto(), e.getDescricao()));
    }

    // ==========================================
    // 4. MÓDULO DE SERVIÇOS (CATÁLOGO)
    // ==========================================
    private void menuServicos() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE SERVIÇOS ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar | 4. Excluir | 5. Buscar por Nome | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    String desc = ConsoleUtil.lerString("Descrição: ");
                    double val = ConsoleUtil.lerDouble("Valor R$: ");
                    adminView.cadastrarServicoBase(desc, val);
                    System.out.println("✅ Salvo!");
                }
                case 2 -> listarServ();
                case 3 -> {
                    listarServ();
                    Servico s = adminView.buscarServicoPorId(ConsoleUtil.lerInt("ID para Editar: "));
                    if(s != null) {
                        String nDesc = ConsoleUtil.lerString("Nova Descrição ["+s.getDescricao()+"]: ");
                        if(!nDesc.isEmpty()) s.setDescricao(nDesc);
                        adminView.atualizarServico(s);
                        System.out.println("✅ Atualizado!");
                    }
                }
                case 4 -> {
                    listarServ();
                    adminView.excluirServico(ConsoleUtil.lerInt("ID para Excluir: "));
                    System.out.println("✅ Excluído!");
                }
                case 5 -> {
                    String termo = ConsoleUtil.lerString("Digite parte da descrição: ");
                    adminView.buscarServicoPorDescricao(termo).forEach(s -> System.out.printf("ID: %d | Desc: %s\n", s.getId(), s.getDescricao()));
                }
            }
        }
    }
    private void listarServ() {
        adminView.listarCatalogoServicos().forEach(s -> System.out.printf("ID: %d | Desc: %s | R$%.2f\n", s.getId(), s.getDescricao(), s.getValorMedida()));
    }

    // ==========================================
    // 5. MÓDULO DE VÍNCULOS (ETAPA X SERVIÇO)
    // ==========================================
    private void menuVinculos() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- VINCULAR SERVIÇO À ETAPA ---");
            System.out.println("1. Cadastrar Vínculo | 2. Listar Vínculos | 3. Excluir | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    int idEtapa = ConsoleUtil.lerInt("ID da Etapa: ");
                    int idServ = ConsoleUtil.lerInt("ID do Serviço: ");
                    double qtd = ConsoleUtil.lerDouble("Quantidade/Área (Ex: 10.5): ");
                    adminView.vincularServico(idEtapa, idServ, qtd);
                    System.out.println("✅ Vinculado com sucesso!");
                }
                case 2 -> adminView.listarVinculos().forEach(v -> System.out.printf("Vínculo ID: %d | Etapa: %d | Serviço: %d | Qtd: %.2f\n", v.getId(), v.getIdEtapa(), v.getIdServico(), v.getQuantidade()));
                case 3 -> adminView.excluirVinculo(ConsoleUtil.lerInt("ID do Vínculo a Excluir: "));
            }
        }
    }

    // ==========================================
    // 6. MÓDULO DE MATERIAIS
    // ==========================================
    private void menuMateriais() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE MATERIAIS ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar | 4. Excluir | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    int idEtapa = ConsoleUtil.lerInt("ID da Etapa: ");
                    String desc = ConsoleUtil.lerString("Material: ");
                    double qtd = ConsoleUtil.lerDouble("Quantidade: ");
                    String un = ConsoleUtil.lerString("Unidade (Ex: Saco, Litro): ");
                    adminView.cadastrarMaterial(idEtapa, desc, qtd, un);
                    System.out.println("✅ Salvo!");
                }
                case 2 -> adminView.listarMateriais().forEach(m -> System.out.printf("ID: %d | Etapa ID: %d | Material: %s | Qtd: %.2f %s\n", m.getId(), m.getIdEtapa(), m.getDescricao(), m.getQuantidade(), m.getUnidade()));
                case 3 -> {
                    Material m = adminView.buscarMaterialPorId(ConsoleUtil.lerInt("ID para Editar: "));
                    if(m != null) {
                        String nDesc = ConsoleUtil.lerString("Nova Descrição ["+m.getDescricao()+"]: ");
                        if(!nDesc.isEmpty()) m.setDescricao(nDesc);
                        adminView.atualizarMaterial(m);
                        System.out.println("✅ Atualizado!");
                    }
                }
                case 4 -> adminView.excluirMaterial(ConsoleUtil.lerInt("ID para Excluir: "));
            }
        }
    }

    // ==========================================
    // 7. MÓDULO DE EMPREITEIROS
    // ==========================================
    private void menuEmpreiteiros() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE EMPREITEIROS ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar | 4. Excluir | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    int idEtapa = ConsoleUtil.lerInt("ID da Etapa: ");
                    String nome = ConsoleUtil.lerString("Nome da Empresa: ");
                    String cnpj = ConsoleUtil.lerString("CNPJ: ");
                    adminView.cadastrarEmpreiteiro(idEtapa, nome, cnpj);
                    System.out.println("✅ Salvo!");
                }
                case 2 -> adminView.listarEmpreiteiros().forEach(e -> System.out.printf("ID: %d | Etapa ID: %d | Empresa: %s | CNPJ: %s\n", e.getId(), e.getIdEtapa(), e.getNomeEmpresa(), e.getCnpj()));
                case 3 -> {
                    Empreiteiro e = adminView.buscarEmpreiteiroPorId(ConsoleUtil.lerInt("ID para Editar: "));
                    if(e != null) {
                        String nNome = ConsoleUtil.lerString("Nova Empresa ["+e.getNomeEmpresa()+"]: ");
                        if(!nNome.isEmpty()) e.setNomeEmpresa(nNome);
                        adminView.atualizarEmpreiteiro(e);
                        System.out.println("✅ Atualizado!");
                    }
                }
                case 4 -> adminView.excluirEmpreiteiro(ConsoleUtil.lerInt("ID para Excluir: "));
            }
        }
    }
}
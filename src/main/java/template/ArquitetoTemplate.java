package template;

import model.entities.*;
import model.enums.StatusProjeto;
import view.AdminView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ArquitetoTemplate {
    private AdminView adminView = new AdminView();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                    int id = ConsoleUtil.lerInt("ID do Cliente para Editar (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        Cliente c = adminView.buscarClientePorId(id);
                        if(c != null) {
                            String nNome = ConsoleUtil.lerString("Novo Nome ["+c.getNome()+"]: ");
                            if(!nNome.isEmpty()) c.setNome(nNome);
                            adminView.atualizarCliente(c);
                            System.out.println("✅ Atualizado!");
                        } else { System.out.println("❌ Cliente não encontrado!"); }
                    }
                }
                case 4 -> {
                    listarClientes();
                    int id = ConsoleUtil.lerInt("ID do Cliente para Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarClientePorId(id) != null) {
                            adminView.excluirCliente(id);
                            System.out.println("✅ Excluído!");
                        } else { System.out.println("❌ Cliente não encontrado!"); }
                    }
                }
                case 5 -> {
                    String termo = ConsoleUtil.lerString("Digite parte do nome (ou '0' para cancelar): ");
                    if (termo.equals("0")) {
                        System.out.println("Operação cancelada.");
                    } else {
                        List<Cliente> res = adminView.buscarClientePorNome(termo);
                        if(res.isEmpty()) {
                            System.out.println("Nenhum cliente encontrado.");
                        } else {
                            res.forEach(c -> System.out.printf("ID: %d | Nome: %s\n", c.getId(), c.getNome()));
                        }
                    }
                }
            }
        }
    }
    private void listarClientes() {
        System.out.println("\n-- LISTA DE CLIENTES --");
        adminView.listarTodosClientes().forEach(c -> System.out.printf("ID: %d | Nome: %s | Login: %s\n", c.getId(), c.getNome(), c.getLogin()));
    }

    // ==========================================
    // 2. MÓDULO DE PROJETOS
    // ==========================================
    private void menuProjetos() {
        int op = -1;
        while(op != 0) {
            System.out.println("\n--- MÓDULO DE PROJETOS ---");
            System.out.println("1. Cadastrar | 2. Listar | 3. Editar Completo | 4. Excluir | 5. Buscar por Título | 6. Buscar por Status | 0. Voltar");
            op = ConsoleUtil.lerInt("Opção: ");
            switch(op) {
                case 1 -> {
                    listarClientes();
                    ConsoleUtil.pularLinha();
                    int idCli = ConsoleUtil.lerInt("ID do Cliente Proprietário (0 para cancelar): ");
                    if (idCli == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        String titulo = ConsoleUtil.lerString("Título do Projeto: ");
                        var data = ConsoleUtil.lerData("Data Previsão (dd/MM/yyyy): ");
                        adminView.cadastrarProjeto(titulo, idCli, data);
                        System.out.println("✅ Salvo!");
                    }
                }
                case 2 -> listarProjetos();
                case 3 -> {
                    listarProjetos();
                    int id = ConsoleUtil.lerInt("ID do Projeto para Editar (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        Projeto p = adminView.buscarProjetoPorId(id);
                        if(p != null) {
                            System.out.println("Pressione ENTER sem preencher para manter o dado antigo.");

                            String nTit = ConsoleUtil.lerString("Novo Título [" + p.getTitulo() + "]: ");
                            if(!nTit.isEmpty()) p.setTitulo(nTit);

                            String nCliStr = ConsoleUtil.lerString("Novo ID do Cliente Vinculado [" + p.getIdCliente() + "]: ");
                            if(!nCliStr.isEmpty()) p.setIdCliente(Integer.parseInt(nCliStr));

                            System.out.println("Status atual: " + p.getStatus());
                            System.out.println("1. PLANEJAMENTO | 2. APROVADO | 3. EM EXECUCAO | 4. CONCLUIDO | 0. Manter Atual");
                            int opStatus = ConsoleUtil.lerInt("Escolha o novo status: ");
                            switch (opStatus) {
                                case 1 -> p.setStatus(StatusProjeto.PLANEJAMENTO);
                                case 2 -> p.setStatus(StatusProjeto.APROVADO);
                                case 3 -> p.setStatus(StatusProjeto.EM_EXECUCAO);
                                case 4 -> {
                                    p.setStatus(StatusProjeto.CONCLUIDO);
                                    p.setDataPrevisaoConclusao(LocalDate.now()); // Mágica aqui!
                                    System.out.println("ℹ️ Status alterado para CONCLUÍDO. A data foi definida automaticamente para hoje.");
                                }
                            }

                            String rotuloNovaData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Data de Conclusão" : "Nova Data de Previsão";
                            String dataStr = ConsoleUtil.lerString(rotuloNovaData + " [" + p.getDataPrevisaoConclusao().format(dateFormatter) + "] (dd/MM/yyyy): ");
                            if(!dataStr.isEmpty()) {
                                try {
                                    p.setDataPrevisaoConclusao(LocalDate.parse(dataStr, dateFormatter));
                                } catch(Exception e) {
                                    System.out.println("❌ Formato inválido. Data antiga mantida.");
                                }
                            }

                            adminView.atualizarProjeto(p);
                            System.out.println("✅ Projeto atualizado completamente!");
                        } else { System.out.println("❌ Projeto não encontrado!"); }
                    }
                }
                case 4 -> {
                    listarProjetos();
                    int id = ConsoleUtil.lerInt("ID do Projeto para Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarProjetoPorId(id) != null) {
                            adminView.excluirProjeto(id);
                            System.out.println("✅ Excluído!");
                        } else { System.out.println("❌ Projeto não encontrado!"); }
                    }
                }
                case 5 -> {
                    String termo = ConsoleUtil.lerString("Digite parte do título (ou '0' para cancelar): ");
                    if (termo.equals("0")) {
                        System.out.println("Operação cancelada.");
                    } else {
                        List<Projeto> res = adminView.buscarProjetoPorTitulo(termo);
                        if(res.isEmpty()) {
                            System.out.println("Nenhum projeto encontrado.");
                        } else {
                            res.forEach(p -> {
                                Cliente c = adminView.buscarClientePorId(p.getIdCliente());
                                String nomeCli = (c != null) ? c.getNome() : "Desconhecido";
                                String rotuloData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Concluído em" : "Previsão";
                                System.out.printf("ID: %d | Cliente: %s | Título: %s | Status: %s | %s: %s\n",
                                        p.getId(), nomeCli, p.getTitulo(), p.getStatus(), rotuloData, p.getDataPrevisaoConclusao().format(dateFormatter));
                            });
                        }
                    }
                }
                case 6 -> {
                    System.out.println("Escolha o Status para filtrar:");
                    System.out.println("1. PLANEJAMENTO | 2. APROVADO | 3. EM EXECUCAO | 4. CONCLUIDO | 0. Cancelar");
                    int escolha = ConsoleUtil.lerInt("Opção: ");
                    if (escolha == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        StatusProjeto statusAlvo = null;
                        switch (escolha) {
                            case 1 -> statusAlvo = StatusProjeto.PLANEJAMENTO;
                            case 2 -> statusAlvo = StatusProjeto.APROVADO;
                            case 3 -> statusAlvo = StatusProjeto.EM_EXECUCAO;
                            case 4 -> statusAlvo = StatusProjeto.CONCLUIDO;
                        }
                        if(statusAlvo != null) {
                            System.out.println("\n-- RESULTADO DO FILTRO POR STATUS --");
                            List<Projeto> filtrados = adminView.buscarProjetoPorStatus(statusAlvo);
                            if(filtrados.isEmpty()) { System.out.println("Nenhum projeto encontrado com este status."); }
                            else {
                                filtrados.forEach(p -> {
                                    Cliente c = adminView.buscarClientePorId(p.getIdCliente());
                                    String nomeCli = (c != null) ? c.getNome() : "Desconhecido";
                                    String rotuloData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Concluído em" : "Previsão";
                                    System.out.printf("ID: %d | Cliente: %s | Título: %s | %s: %s\n",
                                            p.getId(), nomeCli, p.getTitulo(), rotuloData, p.getDataPrevisaoConclusao().format(dateFormatter));
                                });
                            }
                        } else { System.out.println("❌ Seleção inválida."); }
                    }
                }
            }
        }
    }

    // UX: Mostra NOME do Cliente e Rótulo de Data Dinâmico
    private void listarProjetos() {
        System.out.println("\n-- LISTA GLOBAL DE PROJETOS --");
        adminView.listarTodosProjetos().forEach(p -> {
            Cliente c = adminView.buscarClientePorId(p.getIdCliente());
            String nomeCliente = (c != null) ? c.getNome() : "Desconhecido";
            String rotuloData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Concluído em" : "Previsão";

            System.out.printf("ID: %d | Cliente: %s | Título: %s | Status: %s | %s: %s\n",
                    p.getId(), nomeCliente, p.getTitulo(), p.getStatus(), rotuloData, p.getDataPrevisaoConclusao().format(dateFormatter));
        });
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
                    listarProjetos();
                    ConsoleUtil.pularLinha();
                    int idProj = ConsoleUtil.lerInt("ID do Projeto Vinculado (0 para cancelar): ");
                    if (idProj == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        String desc = ConsoleUtil.lerString("Descrição da Etapa: ");
                        var data = ConsoleUtil.lerData("Data Limite (dd/MM/yyyy): ");
                        adminView.cadastrarEtapa(idProj, desc, data);
                        System.out.println("✅ Salvo!");
                    }
                }
                case 2 -> listarEtapas();
                case 3 -> {
                    listarEtapas();
                    int id = ConsoleUtil.lerInt("ID da Etapa para Editar (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        Etapa e = adminView.buscarEtapaPorId(id);
                        if(e != null) {
                            String nDesc = ConsoleUtil.lerString("Nova Descrição ["+e.getDescricao()+"]: ");
                            if(!nDesc.isEmpty()) e.setDescricao(nDesc);
                            adminView.atualizarEtapa(e);
                            System.out.println("✅ Atualizado!");
                        } else { System.out.println("❌ Etapa não encontrada!"); }
                    }
                }
                case 4 -> {
                    listarEtapas();
                    int id = ConsoleUtil.lerInt("ID da Etapa para Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarEtapaPorId(id) != null) {
                            adminView.excluirEtapa(id);
                            System.out.println("✅ Excluído!");
                        } else { System.out.println("❌ Etapa não encontrada!"); }
                    }
                }
            }
        }
    }
    private void listarEtapas() {
        System.out.println("\n-- LISTA DE ETAPAS --");
        adminView.listarTodasEtapas().forEach(e -> {
            Projeto p = adminView.buscarProjetoPorId(e.getIdProjeto());
            String tituloProjeto = (p != null) ? p.getTitulo() : "Desconhecido";
            System.out.printf("ID: %d | Projeto: %s | Desc: %s | Prazo: %s\n",
                    e.getId(), tituloProjeto, e.getDescricao(), e.getDataLimite().format(dateFormatter));
        });
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
                    int id = ConsoleUtil.lerInt("ID do Serviço para Editar (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        Servico s = adminView.buscarServicoPorId(id);
                        if(s != null) {
                            String nDesc = ConsoleUtil.lerString("Nova Descrição ["+s.getDescricao()+"]: ");
                            if(!nDesc.isEmpty()) s.setDescricao(nDesc);
                            adminView.atualizarServico(s);
                            System.out.println("✅ Atualizado!");
                        } else { System.out.println("❌ Serviço não encontrado!"); }
                    }
                }
                case 4 -> {
                    listarServ();
                    int id = ConsoleUtil.lerInt("ID do Serviço para Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarServicoPorId(id) != null) {
                            adminView.excluirServico(id);
                            System.out.println("✅ Excluído!");
                        } else { System.out.println("❌ Serviço não encontrado!"); }
                    }
                }
                case 5 -> {
                    String termo = ConsoleUtil.lerString("Digite parte da descrição (ou '0' para cancelar): ");
                    if (termo.equals("0")) {
                        System.out.println("Operação cancelada.");
                    } else {
                        List<Servico> res = adminView.buscarServicoPorDescricao(termo);
                        if(res.isEmpty()) {
                            System.out.println("Nenhum serviço encontrado.");
                        } else {
                            res.forEach(s -> System.out.printf("ID: %d | Desc: %s | R$%.2f\n", s.getId(), s.getDescricao(), s.getValorMedida()));
                        }
                    }
                }
            }
        }
    }
    private void listarServ() {
        System.out.println("\n-- CATÁLOGO DE SERVIÇOS --");
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
                    listarEtapas();
                    listarServ();
                    ConsoleUtil.pularLinha();
                    int idEtapa = ConsoleUtil.lerInt("ID da Etapa Escolhida (0 para cancelar): ");
                    if (idEtapa == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        int idServ = ConsoleUtil.lerInt("ID do Serviço Escolhido (0 para cancelar): ");
                        if (idServ == 0) {
                            System.out.println("Operação cancelada.");
                        } else {
                            double qtd = ConsoleUtil.lerDouble("Medida/Área a ser executada nesta etapa em m² (Ex: 10.5): ");
                            adminView.vincularServico(idEtapa, idServ, qtd);
                            System.out.println("✅ Vinculado com sucesso!");
                        }
                    }
                }
                case 2 -> listarVinculos();
                case 3 -> {
                    listarVinculos();
                    int id = ConsoleUtil.lerInt("ID do Vínculo a Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarVinculoPorId(id) != null) {
                            adminView.excluirVinculo(id);
                            System.out.println("✅ Vínculo removido!");
                        } else { System.out.println("❌ Vínculo não encontrado!"); }
                    }
                }
            }
        }
    }

    private void listarVinculos() {
        System.out.println("\n-- VÍNCULOS ETAPA-SERVIÇO EXISTENTES --");
        adminView.listarVinculos().forEach(v -> {
            Etapa e = adminView.buscarEtapaPorId(v.getIdEtapa());
            Servico s = adminView.buscarServicoPorId(v.getIdServico());
            String descEtapa = (e != null) ? e.getDescricao() : "Desconhecida";
            String descServico = (s != null) ? s.getDescricao() : "Desconhecido";
            System.out.printf("Vínculo ID: %d | Etapa: %s | Serviço: %s | Medida/Área: %.2f m²\n",
                    v.getId(), descEtapa, descServico, v.getQuantidade());
        });
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
                    listarEtapas();
                    ConsoleUtil.pularLinha();
                    int idEtapa = ConsoleUtil.lerInt("ID da Etapa Destino (0 para cancelar): ");
                    if (idEtapa == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        String desc = ConsoleUtil.lerString("Descrição do Material: ");
                        double qtd = ConsoleUtil.lerDouble("Quantidade Necessária: ");
                        String un = ConsoleUtil.lerString("Unidade (Ex: Saco, Litro, Un): ");
                        adminView.cadastrarMaterial(idEtapa, desc, qtd, un);
                        System.out.println("✅ Salvo!");
                    }
                }
                case 2 -> listarMateriais();
                case 3 -> {
                    listarMateriais();
                    int id = ConsoleUtil.lerInt("ID do Material para Editar (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        Material m = adminView.buscarMaterialPorId(id);
                        if(m != null) {
                            String nDesc = ConsoleUtil.lerString("Nova Descrição ["+m.getDescricao()+"]: ");
                            if(!nDesc.isEmpty()) m.setDescricao(nDesc);
                            adminView.atualizarMaterial(m);
                            System.out.println("✅ Atualizado!");
                        } else { System.out.println("❌ Material não encontrado!"); }
                    }
                }
                case 4 -> {
                    listarMateriais();
                    int id = ConsoleUtil.lerInt("ID do Material para Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarMaterialPorId(id) != null) {
                            adminView.excluirMaterial(id);
                            System.out.println("✅ Excluído!");
                        } else { System.out.println("❌ Material não encontrado!"); }
                    }
                }
            }
        }
    }

    private void listarMateriais() {
        System.out.println("\n-- LISTA DE MATERIAIS ALOCADOS --");
        adminView.listarMateriais().forEach(m -> {
            Etapa e = adminView.buscarEtapaPorId(m.getIdEtapa());
            String descEtapa = (e != null) ? e.getDescricao() : "Desconhecida";
            System.out.printf("ID: %d | Etapa: %s | Material: %s | Quantidade: %.2f %s\n",
                    m.getId(), descEtapa, m.getDescricao(), m.getQuantidade(), m.getUnidade());
        });
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
                    listarEtapas();
                    ConsoleUtil.pularLinha();
                    int idEtapa = ConsoleUtil.lerInt("ID da Etapa sob Responsabilidade (0 para cancelar): ");
                    if (idEtapa == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        String nome = ConsoleUtil.lerString("Nome da Empresa/Empreiteiro: ");
                        String cnpj = ConsoleUtil.lerString("CNPJ: ");
                        adminView.cadastrarEmpreiteiro(idEtapa, nome, cnpj);
                        System.out.println("✅ Salvo!");
                    }
                }
                case 2 -> listarEmpreiteiros();
                case 3 -> {
                    listarEmpreiteiros();
                    int id = ConsoleUtil.lerInt("ID do Empreiteiro para Editar (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        Empreiteiro e = adminView.buscarEmpreiteiroPorId(id);
                        if(e != null) {
                            String nNome = ConsoleUtil.lerString("Nova Empresa ["+e.getNomeEmpresa()+"]: ");
                            if(!nNome.isEmpty()) e.setNomeEmpresa(nNome);
                            adminView.atualizarEmpreiteiro(e);
                            System.out.println("✅ Atualizado!");
                        } else { System.out.println("❌ Empreiteiro não encontrado!"); }
                    }
                }
                case 4 -> {
                    listarEmpreiteiros();
                    int id = ConsoleUtil.lerInt("ID do Empreiteiro para Excluir (0 para cancelar): ");
                    if (id == 0) {
                        System.out.println("Operação cancelada.");
                    } else {
                        if(adminView.buscarEmpreiteiroPorId(id) != null) {
                            adminView.excluirEmpreiteiro(id);
                            System.out.println("✅ Excluído!");
                        } else { System.out.println("❌ Empreiteiro não encontrado!"); }
                    }
                }
            }
        }
    }

    private void listarEmpreiteiros() {
        System.out.println("\n-- LISTA DE EMPREITEIROS CONTRATADOS --");
        adminView.listarEmpreiteiros().forEach(emp -> {
            Etapa e = adminView.buscarEtapaPorId(emp.getIdEtapa());
            String descEtapa = (e != null) ? e.getDescricao() : "Desconhecida";
            System.out.printf("ID: %d | Etapa: %s | Empresa: %s | CNPJ: %s\n",
                    emp.getId(), descEtapa, emp.getNomeEmpresa(), emp.getCnpj());
        });
    }
}
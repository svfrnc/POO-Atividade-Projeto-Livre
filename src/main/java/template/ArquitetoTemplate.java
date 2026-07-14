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
                    String login = ConsoleUtil.lerString("Login: ").toLowerCase();
                    String senha = ConsoleUtil.lerString("Senha: ");
                    String nome = ConsoleUtil.lerString("Nome Completo: ");
                    adminView.cadastrarCliente(login, senha, nome);
                    System.out.println("✅ Salvo com sucesso!");
                }
                case 2 -> listarClientes();
                case 3 -> {
                    if (!listarClientes()) break;
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
                    if (!listarClientes()) break;
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
                    if (adminView.listarTodosClientes().isEmpty()) {
                        System.out.println("ℹ️ Nenhum cliente cadastrado no momento.");
                        break;
                    }
                    String termo = ConsoleUtil.lerString("Digite parte do nome (ou '0' para cancelar): ");
                    if (termo.equals("0")) {
                        System.out.println("Operação cancelada.");
                    } else {
                        List<Cliente> res = adminView.buscarClientePorNome(termo);
                        if(res.isEmpty()) {
                            System.out.println("ℹ️ Nenhum cliente encontrado com este nome.");
                        } else {
                            res.forEach(c -> System.out.printf("ID: %d | Nome: %s\n", c.getId(), c.getNome()));
                        }
                    }
                }
            }
        }
    }

    private boolean listarClientes() {
        System.out.println("\n-- LISTA DE CLIENTES --");
        List<Cliente> clientes = adminView.listarTodosClientes();
        if (clientes.isEmpty()) {
            System.out.println("ℹ️ Nenhum cliente cadastrado no momento.");
            return false;
        } else {
            clientes.forEach(c -> System.out.printf("ID: %d | Nome: %s | Login: %s\n", c.getId(), c.getNome(), c.getLogin()));
            return true;
        }
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
                    if (!listarClientes()) {
                        System.out.println("❌ Você precisa cadastrar um cliente antes de criar um projeto.");
                        break;
                    }
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
                    if (!listarProjetos()) break;
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
                                    p.setDataPrevisaoConclusao(LocalDate.now());
                                    System.out.println("ℹ️ Status alterado para CONCLUÍDO. A data foi definida automaticamente para hoje.");
                                }
                            }

                            // UX PERFEITA: Só pergunta a data se não tiver acabado de ser auto-preenchida pelo Status 4
                            if (opStatus != 4) {
                                String rotuloNovaData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Data de Conclusão" : "Nova Data de Previsão";
                                String dataStr = ConsoleUtil.lerString(rotuloNovaData + " [" + p.getDataPrevisaoConclusao().format(dateFormatter) + "] (dd/MM/yyyy): ");
                                if(!dataStr.isEmpty()) {
                                    try {
                                        p.setDataPrevisaoConclusao(LocalDate.parse(dataStr, dateFormatter));
                                    } catch(Exception e) {
                                        System.out.println("❌ Formato inválido. Data antiga mantida.");
                                    }
                                }
                            }

                            adminView.atualizarProjeto(p);
                            System.out.println("✅ Projeto atualizado completamente!");
                        } else { System.out.println("❌ Projeto não encontrado!"); }
                    }
                }
                case 4 -> {
                    if (!listarProjetos()) break;
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
                    if (adminView.listarTodosProjetos().isEmpty()) {
                        System.out.println("ℹ️ Nenhum projeto cadastrado no momento.");
                        break;
                    }
                    String termo = ConsoleUtil.lerString("Digite parte do título (ou '0' para cancelar): ");
                    if (termo.equals("0")) {
                        System.out.println("Operação cancelada.");
                    } else {
                        List<Projeto> res = adminView.buscarProjetoPorTitulo(termo);
                        if(res.isEmpty()) {
                            System.out.println("ℹ️ Nenhum projeto encontrado com este título.");
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
                    if (adminView.listarTodosProjetos().isEmpty()) {
                        System.out.println("ℹ️ Nenhum projeto cadastrado no momento.");
                        break;
                    }
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
                            if(filtrados.isEmpty()) { System.out.println("ℹ️ Nenhum projeto encontrado com este status."); }
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

    private boolean listarProjetos() {
        System.out.println("\n-- LISTA GLOBAL DE PROJETOS --");
        List<Projeto> projetos = adminView.listarTodosProjetos();
        if (projetos.isEmpty()) {
            System.out.println("ℹ️ Nenhum projeto cadastrado no momento.");
            return false;
        } else {
            projetos.forEach(p -> {
                Cliente c = adminView.buscarClientePorId(p.getIdCliente());
                String nomeCliente = (c != null) ? c.getNome() : "Desconhecido";
                String rotuloData = p.getStatus() == StatusProjeto.CONCLUIDO ? "Concluído em" : "Previsão";

                System.out.printf("ID: %d | Cliente: %s | Título: %s | Status: %s | %s: %s\n",
                        p.getId(), nomeCliente, p.getTitulo(), p.getStatus(), rotuloData, p.getDataPrevisaoConclusao().format(dateFormatter));
            });
            return true;
        }
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
                    if (!listarProjetos()) {
                        System.out.println("❌ Você precisa ter um projeto antes de cadastrar uma etapa.");
                        break;
                    }
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
                    if (!listarEtapas()) break;
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
                    if (!listarEtapas()) break;
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

    private boolean listarEtapas() {
        System.out.println("\n-- LISTA DE ETAPAS --");
        List<Etapa> etapas = adminView.listarTodasEtapas();
        if (etapas.isEmpty()) {
            System.out.println("ℹ️ Nenhuma etapa cadastrada no momento.");
            return false;
        } else {
            etapas.forEach(e -> {
                Projeto p = adminView.buscarProjetoPorId(e.getIdProjeto());
                String tituloProjeto = (p != null) ? p.getTitulo() : "Desconhecido";
                System.out.printf("ID: %d | Projeto: %s | Desc: %s | Prazo: %s\n",
                        e.getId(), tituloProjeto, e.getDescricao(), e.getDataLimite().format(dateFormatter));
            });
            return true;
        }
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
                    if (!listarServ()) break;
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
                    if (!listarServ()) break;
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
                    if (adminView.listarCatalogoServicos().isEmpty()) {
                        System.out.println("ℹ️ Nenhum serviço cadastrado no catálogo.");
                        break;
                    }
                    String termo = ConsoleUtil.lerString("Digite parte da descrição (ou '0' para cancelar): ");
                    if (termo.equals("0")) {
                        System.out.println("Operação cancelada.");
                    } else {
                        List<Servico> res = adminView.buscarServicoPorDescricao(termo);
                        if(res.isEmpty()) {
                            System.out.println("ℹ️ Nenhum serviço encontrado com este nome.");
                        } else {
                            res.forEach(s -> System.out.printf("ID: %d | Desc: %s | R$%.2f\n", s.getId(), s.getDescricao(), s.getValorMedida()));
                        }
                    }
                }
            }
        }
    }

    private boolean listarServ() {
        System.out.println("\n-- CATÁLOGO DE SERVIÇOS --");
        List<Servico> servicos = adminView.listarCatalogoServicos();
        if (servicos.isEmpty()) {
            System.out.println("ℹ️ Nenhum serviço cadastrado no catálogo.");
            return false;
        } else {
            servicos.forEach(s -> System.out.printf("ID: %d | Desc: %s | R$%.2f\n", s.getId(), s.getDescricao(), s.getValorMedida()));
            return true;
        }
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
                    if (!listarEtapas()) {
                        System.out.println("❌ Você precisa ter etapas cadastradas para realizar vínculos.");
                        break;
                    }
                    if (!listarServ()) {
                        System.out.println("❌ Você precisa ter serviços cadastrados no catálogo para realizar vínculos.");
                        break;
                    }
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
                    if (!listarVinculos()) break;
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

    private boolean listarVinculos() {
        System.out.println("\n-- VÍNCULOS ETAPA-SERVIÇO EXISTENTES --");
        List<EtapaServico> vinculos = adminView.listarVinculos();
        if (vinculos.isEmpty()) {
            System.out.println("ℹ️ Nenhum vínculo registrado no momento.");
            return false;
        } else {
            vinculos.forEach(v -> {
                Etapa e = adminView.buscarEtapaPorId(v.getIdEtapa());
                Servico s = adminView.buscarServicoPorId(v.getIdServico());
                String descEtapa = (e != null) ? e.getDescricao() : "Desconhecida";
                String descServico = (s != null) ? s.getDescricao() : "Desconhecido";
                System.out.printf("Vínculo ID: %d | Etapa: %s | Serviço: %s | Medida/Área: %.2f m²\n",
                        v.getId(), descEtapa, descServico, v.getQuantidade());
            });
            return true;
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
                    if (!listarEtapas()) {
                        System.out.println("❌ Você precisa ter uma etapa cadastrada antes de alocar um material.");
                        break;
                    }
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
                    if (!listarMateriais()) break;
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
                    if (!listarMateriais()) break;
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

    private boolean listarMateriais() {
        System.out.println("\n-- LISTA DE MATERIAIS ALOCADOS --");
        List<Material> materiais = adminView.listarMateriais();
        if (materiais.isEmpty()) {
            System.out.println("ℹ️ Nenhum material alocado no momento.");
            return false;
        } else {
            materiais.forEach(m -> {
                Etapa e = adminView.buscarEtapaPorId(m.getIdEtapa());
                String descEtapa = (e != null) ? e.getDescricao() : "Desconhecida";
                System.out.printf("ID: %d | Etapa: %s | Material: %s | Quantidade: %.2f %s\n",
                        m.getId(), descEtapa, m.getDescricao(), m.getQuantidade(), m.getUnidade());
            });
            return true;
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
                    if (!listarEtapas()) {
                        System.out.println("❌ Você precisa ter uma etapa para atribuir um empreiteiro a ela.");
                        break;
                    }
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
                    if (!listarEmpreiteiros()) break;
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
                    if (!listarEmpreiteiros()) break;
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

    private boolean listarEmpreiteiros() {
        System.out.println("\n-- LISTA DE EMPREITEIROS CONTRATADOS --");
        List<Empreiteiro> empreiteiros = adminView.listarEmpreiteiros();
        if (empreiteiros.isEmpty()) {
            System.out.println("ℹ️ Nenhum empreiteiro contratado no momento.");
            return false;
        } else {
            empreiteiros.forEach(emp -> {
                Etapa e = adminView.buscarEtapaPorId(emp.getIdEtapa());
                String descEtapa = (e != null) ? e.getDescricao() : "Desconhecida";
                System.out.printf("ID: %d | Etapa: %s | Empresa: %s | CNPJ: %s\n",
                        emp.getId(), descEtapa, emp.getNomeEmpresa(), emp.getCnpj());
            });
            return true;
        }
    }
}
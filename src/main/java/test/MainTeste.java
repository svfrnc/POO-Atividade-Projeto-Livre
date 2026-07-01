package test;

import model.dao.*;
import model.entities.*;
import model.enums.*;

public class MainTeste {
    public static void main(String[] args) {

        // 1. Inicializar os DAOs
        ArquitetoDAO arquitetoDAO = new ArquitetoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        AmbienteDAO ambienteDAO = new AmbienteDAO();
        MaterialDAO materialDAO = new MaterialDAO();
        ServicoDAO servicoDAO = new ServicoDAO();
        EmpreiteiroDAO empreiteiroDAO = new EmpreiteiroDAO();

        System.out.println("A GERAR 5 EXEMPLOS PARA CADA FICHEIRO JSON...\n");

        // --- 1. GERAR 5 ARQUITETOS ---
        arquitetoDAO.inserir(new Arquiteto(1, "ana.arq", "senha1", TipoPerfil.ADMIN, "CAU-10001"));
        arquitetoDAO.inserir(new Arquiteto(2, "carlos.arq", "senha2", TipoPerfil.ADMIN, "CAU-10002"));
        arquitetoDAO.inserir(new Arquiteto(3, "beatriz.arq", "senha3", TipoPerfil.ADMIN, "CAU-10003"));
        arquitetoDAO.inserir(new Arquiteto(4, "diogo.arq", "senha4", TipoPerfil.ADMIN, "CAU-10004"));
        arquitetoDAO.inserir(new Arquiteto(5, "elena.arq", "senha5", TipoPerfil.ADMIN, "CAU-10005"));
        System.out.println("-> 5 Arquitetos guardados no ficheiro 'arquitetos.json'");

        // --- 2. GERAR 5 CLIENTES ---
        clienteDAO.inserir(new Cliente(1, "joao.cliente", "pass1", TipoPerfil.COMUM, "111.111.111-11", "911111111"));
        clienteDAO.inserir(new Cliente(2, "maria.cliente", "pass2", TipoPerfil.COMUM, "222.222.222-22", "922222222"));
        clienteDAO.inserir(new Cliente(3, "rui.cliente", "pass3", TipoPerfil.COMUM, "333.333.333-33", "933333333"));
        clienteDAO.inserir(new Cliente(4, "sofia.cliente", "pass4", TipoPerfil.COMUM, "444.444.444-44", "944444444"));
        clienteDAO.inserir(new Cliente(5, "tiago.cliente", "pass5", TipoPerfil.COMUM, "555.555.555-55", "955555555"));
        System.out.println("-> 5 Clientes guardados no ficheiro 'clientes.json'");

        // --- 3. GERAR 5 PROJETOS (Corrigido o Status) ---
        projetoDAO.inserir(new Projeto(1, "Moradia de Luxo - João", StatusProjeto.PLANEJAMENTO, 1));
        projetoDAO.inserir(new Projeto(2, "Apartamento T3 - Maria", StatusProjeto.APROVADO, 2));
        projetoDAO.inserir(new Projeto(3, "Escritório Comercial - Rui", StatusProjeto.CONCLUIDO, 3));
        projetoDAO.inserir(new Projeto(4, "Restaurante à Beira-Mar - Sofia", StatusProjeto.APROVADO, 4));
        projetoDAO.inserir(new Projeto(5, "Remodelação Cozinha - Tiago", StatusProjeto.PLANEJAMENTO, 5));
        System.out.println("-> 5 Projetos guardados no ficheiro 'projetos.json'");

        // --- 4. GERAR 5 AMBIENTES (1 para cada projeto) ---
        ambienteDAO.inserir(new Ambiente(1, "Sala de Estar Principal", 45.5, 1));
        ambienteDAO.inserir(new Ambiente(2, "Quarto Suite", 25.0, 2));
        ambienteDAO.inserir(new Ambiente(3, "Sala de Reuniões", 30.0, 3));
        ambienteDAO.inserir(new Ambiente(4, "Salão de Refeições", 120.0, 4));
        ambienteDAO.inserir(new Ambiente(5, "Cozinha Open Space", 18.5, 5));
        System.out.println("-> 5 Ambientes guardados no ficheiro 'ambientes.json'");

        // --- 5. GERAR 5 MATERIAIS (1 para cada ambiente) ---
        materialDAO.inserir(new Material(1, "Piso Flutuante Carvalho", 50, "m2", 1));
        materialDAO.inserir(new Material(2, "Tinta Branca Mate", 3, "Latas 15L", 2));
        materialDAO.inserir(new Material(3, "Mesa de Vidro Temperado", 1, "Unidade", 3));
        materialDAO.inserir(new Material(4, "Cadeiras de Madeira", 40, "Unidades", 4));
        materialDAO.inserir(new Material(5, "Azulejo de Parede Premium", 20, "m2", 5));
        System.out.println("-> 5 Materiais guardados no ficheiro 'materiais.json'");

        // --- 6. GERAR 5 SERVIÇOS ---
        servicoDAO.inserir(new Servico(1, "Instalação de Piso", 800.00, StatusServico.PENDENTE));
        servicoDAO.inserir(new Servico(2, "Pintura Geral", 1200.00, StatusServico.EM_EXECUCAO));
        servicoDAO.inserir(new Servico(3, "Montagem de Mobiliário", 350.00, StatusServico.FINALIZADO));
        servicoDAO.inserir(new Servico(4, "Instalação Elétrica", 2500.00, StatusServico.EM_EXECUCAO));
        servicoDAO.inserir(new Servico(5, "Colocação de Azulejos", 600.00, StatusServico.PENDENTE));
        System.out.println("-> 5 Serviços guardados no ficheiro 'servicos.json'");

        // --- 7. GERAR 5 EMPREITEIROS ---
        empreiteiroDAO.inserir(new Empreiteiro(1, "Pisos & Companhia Lda", "11.111.111/0001-11"));
        empreiteiroDAO.inserir(new Empreiteiro(2, "Pintores do Sul", "22.222.222/0001-22"));
        empreiteiroDAO.inserir(new Empreiteiro(3, "Móveis e Decoração SA", "33.333.333/0001-33"));
        empreiteiroDAO.inserir(new Empreiteiro(4, "Eletricidade Central Lda", "44.444.444/0001-44"));
        empreiteiroDAO.inserir(new Empreiteiro(5, "Cerâmicas e Obras", "55.555.555/0001-55"));
        System.out.println("-> 5 Empreiteiros guardados no ficheiro 'empreiteiros.json'");

        System.out.println("\n========== SUCESSO ==========");
        System.out.println("Todos os 7 ficheiros JSON foram gerados com 5 exemplos de dados em cada um!");
        System.out.println("Pode abrir a sua pasta e verificar os ficheiros .json para confirmar as estruturas.");
    }
}
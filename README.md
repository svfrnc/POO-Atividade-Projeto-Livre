# 🏗️ ArqBuild - Sistema de Gerenciamento de Projeto Arquitetônico

## 📖 1. Introdução
O projeto de um sistema de gerenciamento de projeto arquitetônico tem como base a facilitação do processo de gerenciamento das etapas contidas em um projeto arquitetônico, onde várias entidades envolvidas complementam as ações entre si e são co-dependentes umas das outras. Tendo isso em vista, pensamos na produção do aplicativo **"ARQBUILD"** que atue de forma a auxiliar profissionais da área da construção civil.

## 🎯 2. Objetivo
O projeto tem como objetivo a implementação de um processo em gerenciamento de projetos que possibilite a um gerente de projetos o acompanhamento das etapas de projeto arquitetônico e do desenvolvimento das partes envolvidas. O aplicativo foi pensado para permitir o controle estruturado de ambientes, materiais necessários e serviços terceirizados, possibilitando ao mesmo tempo um acompanhamento mais próximo e transparente da obra por parte do cliente.

## ⚠️ 3. Descrição do Problema
A problemática encontrada consiste na noção de que arquitetos e técnicos em edificações enfrentam uma grande dificuldade de centralizar as informações essenciais de uma obra, como o escopo, os materiais e os fornecedores. Essa ausência de centralização de dados acaba gerando ruídos de comunicação constantes com o cliente final. O desenvolvimento deste sistema via terminal tem o propósito de resolver essa questão, visando centralizar todas essas informações.

## 👥 4. Perfil de Usuários Envolvidos
A aplicação lida com dois perfis distintos:
* **Arquiteto/Técnico (Administrador):** Possui acesso total ao sistema. É responsabilidade deste perfil cadastrar projetos, clientes, materiais, serviços e gerenciar as aprovações.
* **Cliente (Usuário comum):** Possui acesso restrito. A permissão do cliente se limita a visualizar os projetos que estão vinculados ao seu nome, listar os ambientes da sua obra e aprovar o escopo do projeto.

### 👨‍🎓 Alunos
* Sávio França
* Maxwell Dantas

---

## ⚙️ 5. Operações do Aplicativo
O aplicativo está proposto em realizar um fluxo completo de gerenciamento. A princípio, ele deve permitir que os usuários possam entrar e sair do sistema através de Login e Logout. O núcleo do mesmo consiste em realizar operações de CRUD (Inserir, Listar, Atualizar e Excluir) para todas as entidades fundamentais.

Abaixo está a lista completa de operações que o aplicativo executa:

### 🔐 Autenticação:
* Entrar no sistema (Login);
* Sair do sistema (Logout);

### 📝 Operações CRUD:
* **Projetos:** Inserir, Listar, Atualizar e Excluir.
* **Ambientes:** Inserir, Listar, Atualizar e Excluir.
* **Materiais:** Inserir, Listar, Atualizar e Excluir.
* **Serviços:** Inserir, Listar, Atualizar e Excluir.
* **Empreiteiros:** Inserir, Listar, Atualizar e Excluir.
* **Clientes:** Inserir, Listar, Atualizar e Excluir.

### 🔗 Associações e Vinculações:
* Vincular um Ambiente a um Projeto específico.
* Vincular um Material a um Ambiente específico.

### 🔍 Consultas e Pesquisas:
* Buscar/Pesquisar um Projeto utilizando o nome do cliente (com suporte a busca parcial).
* Listar projetos vinculados a um cliente específico (visão do cliente).
* Listar ambientes de uma obra específica (visão do cliente).

### 🧠 Regras de Negócio e Automação:
* **Aprovar Projeto:** Operação onde o cliente aprova o escopo do projeto.
* **Atualização de Status (Automática):** Iterar sobre todos os ambientes e materiais vinculados durante a aprovação e alterar automaticamente o status do projeto/serviço para "Em Execução".
* **Bloqueio de Exclusão (Automática):** Bloquear novas exclusões de materiais vinculados a um projeto após a sua aprovação.

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem**: Java 21 LTS
* **Gerenciador de Dependências**: Maven
* **Persistência de Dados**: Arquivos `.json`
* **Biblioteca JSON**: Gson (Google)
* **Arquitetura**: Camadas (Model, View/Service, DAO para persistência)

---

## 📊 Diagramas do Sistema

Abaixo estão os diagramas UML que representam a arquitetura e os requisitos da aplicação.

### Diagrama de Caso de Uso
Apresenta os atores do sistema (Arquiteto e Cliente) e as ações que cada um pode realizar.

![Diagrama de Caso de Uso](./Diagrama%20de%20Caso%20de%20Uso.png)

### Diagrama de Classes do Modelo
Apresenta a estrutura orientada a objetos das entidades do sistema, suas propriedades, métodos e os relacionamentos de herança e multiplicidade (um para muitos).

![Diagrama de classes do modelo](./Diagrama%20de%20classes%20do%20modelo.png)

### Diagrama de Persistência
Ilustra as classes do tipo Data Access Object (DAO) responsáveis por salvar e carregar os objetos em formato JSON, utilizando abstração através de uma classe DAO genérica.

![Diagrama das persistencias](./Diagrama%20das%20persistencias.png)

---

## 🚀 Como Executar

1. Certifique-se de ter o **Java 21** e o **Maven** instalados em sua máquina.
2. Clone este repositório.
3. Atualize o projeto Maven para baixar as dependências (`pom.xml`), em especial a biblioteca **Gson**.
4. Execute a classe principal da aplicação para iniciar a interface de linha de comando (CLI).

```bash
# Exemplo de compilação e execução via terminal
mvn clean install
mvn exec:java -Dexec.mainClass="test.MainTeste"

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

# ARQBUILD - Sistema de Gerenciamento de Projetos Arquitetônicos

O **ARQBUILD** é uma aplicação desenvolvida em Java estruturada sob o paradigma de Programação Orientada a Objetos (POO)[cite: 1]. O sistema centraliza informações essenciais de obras (como escopo, materiais e serviços), facilitando o gerenciamento de etapas de projetos arquitetônicos e aproximando a comunicação entre profissionais (arquitetos/técnicos) e clientes finais[cite: 1].

Este repositório consolidada as modificações estruturais da **segunda entrega**, apresentando a evolução da arquitetura do sistema, da modelagem de dados e dos mecanismos de persistência e regras de negócio.

---

## 🛠️ Alterações e Evoluções no Modelo (Segunda Entrega)

Em comparação com a primeira versão do projeto, o modelo de classes e entidades foi profundamente refinado para dar maior robustez às associações, persistência de dados em arquivos JSON e controle de fluxo do sistema:

### 1. Novas Entidades e Ajustes nas Classes
*   **`Entidade`**: Classe abstrata de base criada para fornecer herança de identificador único (`id`) para todas as entidades persistíveis do sistema[cite: 1].
*   **`Usuario`**: Introduzida para consolidar o controle de login e autenticação, servindo como classe mãe de especializações[cite: 1].
*   **`Arquiteto`** (Especialização de `Usuario`): Herda os dados de credencial e adiciona o campo de registro profissional `CAU`[cite: 1]. Atua como administrador com permissões totais no sistema[cite: 1].
*   **`Cliente`** (Especialização de `Usuario`): Herda as credenciais e adiciona os campos `CPF` e `telefone`[cite: 1]. Possui permissões de visualização e aprovação restritas ao seu escopo[cite: 1].
*   **`Etapa` e `EtapaServico`**: Implementadas no modelo para permitir um controle detalhado, modular e faseado do cronograma da obra[cite: 1].
*   **`Empreiteiro`**: Entidade responsável por representar prestadores de serviços parceiros alocados às etapas do projeto[cite: 1].
*   **`Servico`**: Atualizado para incluir o `StatusServico` e permitir a vinculação financeira e de execução com as etapas de construção[cite: 1].
*   **`Material`**: Associado diretamente aos ambientes, garantindo o controle preciso do quantitativo e dos insumos de cada espaço projetado[cite: 1].

### 2. Implementação da Persistência (Camada DAO)
Toda a persistência foi unificada utilizando uma estrutura genérica parametrizada (`DAO<T>`) baseada na biblioteca **Gson** para serialização e desserialização em arquivos JSON[cite: 1].
*   Foram criadas classes de DAO específicas para cada entidade (ex: `ArquitetoDAO`, `ClienteDAO`, `ProjetoDAO`, `ServicoDAO`, `MaterialDAO`, `EmpreiteiroDAO`, `EtapaDAO`, etc.) herdando a lógica de salvar, listar, atualizar e excluir da classe base `DAO<T>`[cite: 1].
*   Adicionado o `LocalDateAdapter` para gerenciar datas de maneira adequada na serialização do JSON[cite: 1].

### 3. Novas Funções e Regras de Negócio Implementadas
*   **Autenticação**: Telas de Login e Logout configuradas dinamicamente com base no perfil do usuário (`TipoPerfil`: ADMIN ou CLIENTE)[cite: 1].
*   **Associações Dinâmicas**: Métodos implementados para vincular um Ambiente a um Projeto específico e um Material a um Ambiente específico[cite: 1].
*   **Pesquisas com Busca Parcial**: Funcionalidade para pesquisar Projetos pelo nome do cliente usando correspondência parcial de caracteres[cite: 1].
*   **Regra de Negócio Complexa ("Aprovar Projeto")**:
    *   O cliente aciona a aprovação do escopo do projeto[cite: 1].
    *   O sistema de forma automática itera sobre todos os ambientes e materiais vinculados[cite: 1].
    *   Altera automaticamente o status do serviço/projeto para `"Em Execução"`[cite: 1].
    *   Ativa um bloqueio automático que impede novas exclusões de materiais vinculados após a aprovação formal do cliente[cite: 1].

---

## 📊 Diagramas do Sistema (Atualizados)

Os diagramas de arquitetura do sistema foram revisados e substituídos pelos arquivos correspondentes presentes na pasta `Diagramas Atualizados`. Abaixo estão detalhadas as visões atualizadas:

### 1. Diagrama de Casos de Uso
Mapeia as permissões completas de CRUD, associações, regras de negócio e autenticação de cada ator (Arquiteto e Cliente)[cite: 1].

![Diagrama de Casos de Uso](Diagramas%20Atualizados/Diagrama%20de%20Caso%20de%20Uso.png)

---

### 2. Diagrama de Classes do Modelo e da Persistência
Detallha a estrutura de atributos, heranças de base e relacionamentos estruturais das entidades da aplicação, além da hierarquia genérica de persistência em arquivos com `DAO<T>`[cite: 1].

![Diagrama de Classes do Modelo e da Persistência](Diagramas%20Atualizados/Diagrama%20das%20classes%20da%20entidade%20e%20da%20persistencia.jpg)

---

### 3. Diagrama de Classes de Operações (View/Service)
Descreve as classes de serviço que intermediam as regras de negócio e a manipulação lógica dos dados das entidades do projeto.

![Diagrama de Classes de Operações](Diagramas%20Atualizados/Classe%20de%20Operações.jpg)

---

### 4. Diagrama de Classe da Interface com o Usuário (Views & Templates)
Ilustra a modelagem e os métodos das classes de template e visualização que constroem a interface interativa de terminal para o usuário com base no perfil de acesso logado.

![Diagrama de Classe da Interface com o Usuário](Diagramas%20Atualizados/Classe%20da%20Interface%20Com%20o%20Usuário.jpg)

---

## 💻 Tecnologias Utilizadas

*   **Java 17** (Princípios sólidos de Programação Orientada a Objetos)
*   **Maven** (Gerenciador de dependências e automação de build)
*   **Gson** (Persistência e conversão de objetos em arquivos JSON)[cite: 1]
```bash
# Exemplo de compilação e execução via terminal
mvn clean install
mvn exec:java -Dexec.mainClass="test.MainTeste"

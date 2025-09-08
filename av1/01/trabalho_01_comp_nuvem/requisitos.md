
# Documento de Requisitos - Trabalho 01

**Cadeira:** Computação em Nuvem
**Projeto:** API de Gerenciamento de Produtos e Estoque

## 1. Visão Geral

O objetivo deste projeto é desenvolver uma API RESTful para gerenciar um catálogo de produtos e seu respectivo estoque. A API seguirá os princípios da arquitetura hexagonal (Ports and Adapters), garantindo a separação de responsabilidades e a testabilidade do sistema.

A base inicial do projeto foi uma entidade de `User`, que servirá como modelo para a criação das novas entidades.

## 2. Atores

*   **Administrador do Sistema:** Responsável por gerenciar os dados de produtos e estoque através da API.

## 3. Casos de Uso

### 3.1. Gerenciamento de Usuários (Base)

*   **UC01: Cadastrar Usuário**
    *   **Descrição:** O sistema deve permitir o cadastro de um novo usuário.
    *   **Endpoint:** `POST /users`
*   **UC02: Listar Usuários**
    *   **Descrição:** O sistema deve permitir a listagem de todos os usuários cadastrados.
    *   **Endpoint:** `GET /users`
*   **UC03: Buscar Usuário por ID**
    *   **Descrição:** O sistema deve permitir a busca de um usuário específico pelo seu ID.
    *   **Endpoint:** `GET /users/{id}`

### 3.2. Gerenciamento de Produtos (Novo)

*   **UC04: Cadastrar Produto**
    *   **Descrição:** O sistema deve permitir o cadastro de um novo produto.
    *   **Endpoint:** `POST /products`
    *   **Campos:** `id`, `nome`, `descricao`, `preco`
*   **UC05: Listar Produtos**
    *   **Descrição:** O sistema deve permitir a listagem de todos os produtos.
    *   **Endpoint:** `GET /products`
*   **UC06: Buscar Produto por ID**
    *   **Descrição:** O sistema deve permitir a busca de um produto pelo seu ID.
    *   **Endpoint:** `GET /products/{id}`
*   **UC07: Atualizar Produto**
    *   **Descrição:** O sistema deve permitir a atualização dos dados de um produto.
    *   **Endpoint:** `PUT /products/{id}`
*   **UC08: Deletar Produto**
    *   **Descrição:** O sistema deve permitir a remoção de um produto.
    *   **Endpoint:** `DELETE /products/{id}`

### 3.3. Gerenciamento de Estoque (Novo)

*   **UC09: Adicionar Produto ao Estoque**
    *   **Descrição:** O sistema deve permitir registrar a entrada de um produto no estoque.
    *   **Endpoint:** `POST /stock`
    *   **Campos:** `id`, `produtoId`, `quantidade`
*   **UC10: Listar Itens em Estoque**
    *   **Descrição:** O sistema deve permitir a listagem de todos os itens no estoque.
    *   **Endpoint:** `GET /stock`
*   **UC11: Buscar Estoque por Produto**
    *   **Descrição:** O sistema deve permitir a consulta da quantidade em estoque de um produto específico.
    *   **Endpoint:** `GET /stock/product/{productId}`
*   **UC12: Atualizar Quantidade em Estoque**
    *   **Descrição:** O sistema deve permitir a atualização da quantidade de um produto no estoque.
    *   **Endpoint:** `PUT /stock/{id}`

## 4. Requisitos Não-Funcionais

*   **RNF01:** A aplicação será desenvolvida em **Java 21**.
*   **RNF02:** O projeto utilizará **Spring Boot**.
*   **RNF03:** O gerenciamento de dependências e build será feito com **Gradle**.
*   **RNF04:** A arquitetura seguirá o padrão **Controller -> Service (Port) -> ServiceImpl (Adapter) -> Repository**.

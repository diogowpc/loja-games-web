# Loja de Games

Aplicação web em Java para gerenciar clientes e compras de uma loja de games. Projeto final da disciplina de Banco de Dados I, do curso de TSI do IFPB, Campus Guarabira.

O foco do trabalho é o banco de dados: o modelo físico em PostgreSQL e a aplicação que o utiliza por meio de dois CRUDs relacionados.

## Funcionalidades

**Clientes**
- Cadastrar, listar, editar e excluir clientes.
- O CPF é único: o banco recusa dois clientes com o mesmo CPF.

**Compras**
- Cadastrar, listar, editar e excluir compras.
- Cada compra pertence a um cliente já cadastrado e usa uma forma de pagamento (Pix, Boleto, Cartão de crédito ou Cartão de débito). As duas escolhas são feitas em listas na tela de cadastro.
- A listagem mostra o nome do cliente e o tipo de pagamento, não apenas os códigos.

**Relação entre os CRUDs:** a compra depende do cliente. Sem ao menos um cliente cadastrado, não há como registrar uma compra. Por isso, um cliente que já tem compras não pode ser excluído.

## Tecnologias

- Java 21
- Spring Boot (Spring Web e Spring JDBC)
- Thymeleaf
- PostgreSQL

## Banco de dados

O script `sql/script.sql` cria as 13 tabelas do modelo:

- Tabelas principais: `cliente`, `forma_pagamento`, `jogo`, `genero`, `desenvolvedora`, `fornecedor`, `compra` e `aluguel`.
- Tabelas associativas, que resolvem as relações N:N: `compra_jogo`, `aluguel_jogo`, `jogo_genero`, `jogo_desenvolvedora` e `jogo_fornecedor`.

O script também cadastra as quatro formas de pagamento. A aplicação implementa os CRUDs de `cliente` e `compra`.

## Como executar

Pré-requisitos: Java 21 ou superior e PostgreSQL instalados.

1. Crie um banco chamado `loja_games`:
   ```sql
   CREATE DATABASE loja_games;
   ```
2. Conectado a esse banco, execute o conteúdo de `sql/script.sql`.
3. Em `src/main/resources/application.properties`, ajuste o usuário e a senha do seu PostgreSQL:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/loja_games
   spring.datasource.username=postgres
   spring.datasource.password=SUA_SENHA
   ```
4. Execute a classe `LojaGamesApplication` pelo VS Code ou pela sua IDE. Se tiver o Maven instalado, também funciona:
   ```
   mvn spring-boot:run
   ```
5. Acesse http://localhost:8080.

## Estrutura do projeto

```
sql/                         script de criação do banco
src/main/java/.../
    model/                   classes que representam as tabelas
    dao/                     acesso ao banco (SQL com JdbcClient)
    dto/                     objeto usado na listagem de compras
    controller/              recebe as requisições e escolhe a página
src/main/resources/
    templates/               páginas HTML (Thymeleaf)
    static/css/              estilos
```
# DaoJDBC

Este projeto foi desenvolvido durante meus estudos de JDBC (Java Database Connectivity), com o objetivo de aplicar os conceitos de conexão, manipulação e persistência de dados em bancos de dados relacionais utilizando Java.

O projeto consiste em uma implementação simples de um DAO (Data Access Object), onde é possível realizar operações básicas de CRUD (Create, Read, Update e Delete) em um banco de dados. O foco principal foi entender o funcionamento do JDBC e a interação entre a aplicação Java e o banco de dados.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **JDBC**: Tecnologia Java para conexão e manipulação de bancos de dados relacionais.
- **Banco de Dados**:
  - Utilizado para armazenamento persistente dos dados MySQL.
- **Maven**: Gerenciador de dependências.

## Funcionalidades

- Conexão com o banco de dados utilizando JDBC.
- Implementação de um padrão DAO para acesso a dados.
- Operações básicas de CRUD para entidades no banco de dados.
- Exemplo de uso de PreparedStatement para consultas seguras.

## Estrutura do Projeto

A estrutura do projeto segue uma organização simples:

  # Contém as classes de acesso a dados (DAO) / # Contém as entidades representando as tabelas do banco |-- util/ # Contém utilitários como a classe de conexão com o banco |-- Main.java # Classe principal para executar o projeto
```
src/
  |-- application       # Implemetação de uma plaicação     
  |-- db                # Conexão com o banco de dados
  |-- model             
    |-- dao             # Criação das intefaces e implementação da FactoryMethod
      |-- impl          # Implementação das intefaces
      |-- entities      # Entdades 
```

## Aprendizados

Durante o desenvolvimento deste projeto, aprofundei meu conhecimento nos seguintes tópicos:

- **Conexão e manipulação de bancos de dados utilizando JDBC.**
- **Implementação do padrão DAO (Data Access Object) em Java.**
- **Uso de PreparedStatement para prevenir injeção de SQL.**
- **Gerenciamento de transações no banco de dados.**

## Considerações

Este projeto foi uma excelente oportunidade para solidificar o entendimento sobre o uso do JDBC no desenvolvimento de aplicações Java, além de fornecer uma base para implementar interações com bancos de dados em projetos maiores.


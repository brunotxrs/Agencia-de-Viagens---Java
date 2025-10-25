# Agência de Viagens: Sistema de Cadastro e Vendas

## 🎯 Contexto do Desafio
Este projeto foi desenvolvido em Java, aplicando conceitos de 
Orientação a Objetos (POO), para simular um módulo de cadastro e 
venda de pacotes de viagens. O sistema é responsável por registrar 
os componentes de um pacote (Transporte e Hospedagem), calcular 
os custos totais em Dólar (incluindo margem de lucro e taxas), 
realizar a conversão para Reais e exibir o resultado final da 
venda ao usuário.

### Requisitos Atendidos:
**O programa atende a todos os requisitos do desafio:**

- Modelagem de classes para Transporte, Hospedagem, Pacote de Viagem e Venda.

- Cálculos financeiros precisos utilizando a classe BigDecimal.

- Conversão de valores entre Dólar e Real com base na cotação informada.

- Interação com o usuário para obtenção de dados e exibição de resultados.

----

### 📂 Estrutura do Projeto
A solução foi organizada seguindo o padrão de pacotes, garantindo a separação clara entre a Lógica de Negócio (Entidades) e a Lógica de Interação/Interface (I/O).

````javaTree
.
└── src
    └── main
        └── java
            └── br
                └── com
                    └── agency
                        ├── entities
                        │   ├── Accommodation.java
                        │   ├── Sale.java
                        │   ├── Transport.java
                        │   └── TravelPackage.java
                        └── interaction
                            ├── InteractionUser.java
                            └── Main.java

`````
**Justificativa da Estrutura:** Para manter a **classe** `Main.java` 
**limpa** (conforme boa prática de POO), toda a lógica de interação e 
obtenção de dados do usuário foi delegada à classe `InteractionUser`. 
Isso facilita futuras atualizações e melhorias na interface sem impactar 
o núcleo do programa.

----

### ⚙️ Implementação das Classes
**1. Entidades de Dados** (`br.com.agency.entities`)

| Classe | Responsabilidade | Destaques de Implementação |
| :--- | :--- | :--- |
| **Transport.java** | Armazena o tipo de transporte e seu custo em Dólar. | Utiliza `BigDecimal` para o valor. |
| **Accommodation.java** | Armazena a descrição da hospedagem e o valor da diária em Dólar. | Utiliza `BigDecimal` para a taxa diária. |
| **TravelPackage.java** | Agrega Transporte e Hospedagem, calcula os totais do pacote. | Possui métodos para calcular o total de hospedagem (`calculateTheTotalAccommodation`) e o valor com lucro (`calculateProfitValue`). |
| **Sale.java** | Armazena dados da venda e lida com a conversão de moedas e exibição. | Contém o método estático `convertToReal` e o método `showSaleTotals` que utiliza `NumberFormat` para formatação. |

**2. Lógica Financeira** (`BigDecimal` e `Locale`)
- **Cálculos Precisos:** Todos os valores monetários 
(diárias, transporte, taxas, cotação e lucro) utilizam a classe 
`java.math.BigDecimal` com `RoundingMode.HALF_UP` 
para garantir precisão financeira e evitar erros de ponto flutuante.

- **Conversão para Real:** A exibição final utiliza 
`java.text.NumberFormat` configurado para `Locale("pt", "BR")`. 
Isso assegura que os valores em Reais (`R$`) sejam exibidos no 
padrão correto: vírgula (`,`) como separador decimal e ponto (`.`) 
como separador de milhares.
-----

### 🛡️ Robustez e Validação de Entrada (I/O)
A classe `InteractionUser.java` foi desenvolvida com métodos 
privados auxiliares que garantem a integridade dos dados inseridos, 
evitando falhas (`Exceptions`) do programa.

O programa possui validadores robustos para entradas de string e 
valores numéricos, forçando o usuário a corrigir o input em caso de 
erro, por meio de loops de repetição (`while(true)`) e tratamento 
de exceções (`try-catch`).

Esta tabela destaca a robustez do código ao lidar com diferentes tipos de entrada:

| Método Validador | Finalidade | Tipo de Validação |
| :--- | :--- | :--- |
| **`readValidString`** | Lê *strings* obrigatórias (Nome, Destino, Tipos, etc.). | Garante que a entrada não esteja **vazia** ou contenha apenas **espaços em branco**. |
| **`readValidBigDecimal`** | Lê valores monetários (Dólar, Margem, Cotação). | Trata caracteres de **letras** (`NumberFormatException`) e impede a entrada de **números negativos**. |
| **`readValidPositiveInt`** | Lê números inteiros (Quantidade de Dias). | Trata caracteres de **letras** e impede a entrada de **números negativos ou zero**. |

----

### ▶️ Exemplo de Execução
Abaixo, um exemplo da interação do usuário, 
demonstrando a robustez dos validadores e o cálculo final correto.

Com certeza! Você está se referindo à tabela que apresenta o **exemplo de execução** do seu programa, mostrando as entradas e os resultados.

| Entrada (Usuário) | Resultado (Sistema) |
| :--- | :--- |
| **Transporte:** aéreo, US$ 500.00 | Validado |
| **Hospedagem:** diária US$ 150.00, 50 dias | Validado |
| **Venda:** Margem 10%, Taxas US$ 15.00 | Validado |
| **Cotação:** R$ 5.21 | Validado |
| **Saída Final (1):** | **Valor Total do Pacote (Dólar):** US$ 9.355,00 |
| **Saída Final (2):** | **Valor Total do Pacote (Reais):** R$ 48.739,55 |

----

## 🧑‍💻 Autor
Este projeto foi desenvolvido como parte do desafio da 
**Atividade 1** do curso: TDS - Módulo A - Desenvolver código orientado a objetos

 

**Bruno Teixeira**

**Técnico em Desenvolvimento de Sistemas**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)]([https://github.com/brunotxrs])

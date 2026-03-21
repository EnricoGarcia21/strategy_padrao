# Strategy no Controle de Estoque

Este projeto aplica o padrao de projeto `Strategy` para alterar o estoque de um produto sem acoplar a regra de movimentacao diretamente ao acesso ao banco.

## Objetivo

A ideia principal foi permitir que o objeto `Produto` altere seu estoque de formas diferentes, trocando a estrategia em tempo de execucao.

Exemplos:

- incrementar estoque
- decrementar estoque

Assim, o comportamento varia sem precisar criar condicionais grandes dentro da classe principal.

## Como o padrao foi aplicado

### Contexto

O contexto do padrao e a classe `Produto`.

Ela possui:

- o atributo `movimentoEstoqueStrategy`
- o metodo `atualizarEstoque(int quantidade, Conexao conexao)`
- os metodos de apoio `adicionarEstoque(...)` e `baixarEstoque(...)`

Esses metodos definem qual estrategia sera usada e delegam a execucao.

### Strategy

A interface `MovimentoEstoqueStrategy` define o contrato comum:

```java
boolean executar(Produto produto, int quantidade, Conexao conexao);
```

Toda estrategia concreta precisa implementar esse metodo.

### Concrete Strategies

Foram usadas duas estrategias:

- `IncrementarEstoqueStrategy`
- `DecrementarEstoqueStrategy`

Cada uma implementa a interface `MovimentoEstoqueStrategy` e executa uma regra diferente para movimentar o estoque.

### DAO

A classe `AtualizarEstoqueDAO` ficou responsavel pela persistencia da movimentacao no banco.

Ela contem os metodos:

- `adicionarEstoque(...)`
- `subtrairEstoque(...)`

Dessa forma:

- `Produto` decide qual comportamento usar
- a estrategia executa a regra
- o DAO realiza o `UPDATE` no banco

## Correcoes realizadas

Durante a revisao, foram feitos os seguintes ajustes:

- `Produto` foi mantido como contexto do padrao, conforme o diagrama proposto
- a interface `MovimentoEstoqueStrategy` foi mantida como contrato das estrategias
- `IncrementarEstoqueStrategy` foi corrigida para implementar a interface corretamente
- `DecrementarEstoqueStrategy` foi corrigida para usar o metodo correto do DAO
- `AtualizarEstoqueDAO` deixou de estar vazio e passou a concentrar a atualizacao de estoque
- a classe `AtualizarEstoque` foi removida para evitar conflito de responsabilidade com `Produto`
- `ProdutoDAO` ficou focado apenas no CRUD do produto

## Estrutura final

```text
Produto (Contexto)
  -> MovimentoEstoqueStrategy (Strategy)
      -> IncrementarEstoqueStrategy (ConcreteStrategy)
      -> DecrementarEstoqueStrategy (ConcreteStrategy)
  -> AtualizarEstoqueDAO
```

## Exemplo de uso

```java
Conexao conexao = SingletonDB.conectar();

Produto produto = Produto.get(1, conexao);

boolean adicionou = produto.adicionarEstoque(10, conexao);
boolean baixou = produto.baixarEstoque(5, conexao);
```

Ou, se quiser trocar a estrategia manualmente:

```java
produto.setMovimentoEstoqueStrategy(new IncrementarEstoqueStrategy());
produto.atualizarEstoque(10, conexao);
```

## Beneficios da abordagem

- reduz acoplamento entre regra de negocio e persistencia
- facilita adicionar novas formas de movimentacao
- melhora a organizacao do codigo
- evita excesso de `if` e `switch`

## Observacao

Nao foi possivel validar a compilacao pelo wrapper Maven neste ambiente porque o `mvnw.cmd` apresentou erro de execucao do proprio wrapper.

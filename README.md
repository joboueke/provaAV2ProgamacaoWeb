# Correção da Atividade – API Sistema Escolar

## Objetivo

Este documento descreve os erros encontrados durante a análise do projeto da atividade e suas respectivas correções.

---

# Erro 1 – Tipo incorreto do parâmetro no método de exclusão

**Arquivo:**

`AlunoController.java`

### Problema

O método responsável por excluir um aluno recebe o parâmetro `id` como `String`.

```java
@DeleteMapping("/{id}")
@Transactional
public void excluir(@PathVariable String id) {
    repository.deleteById(id);
}
```

Entretanto, a entidade `Aluno` utiliza `Integer` como identificador.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

Além disso, após corrigir o `AlunoRepository`, o método `deleteById()` passa a esperar um `Integer`, tornando o código incompatível.

### Correção

Alterar o tipo do parâmetro para `Integer`.

```java
@DeleteMapping("/{id}")
@Transactional
public void excluir(@PathVariable Integer id) {
    repository.deleteById(id);
}
```

---

# Erro 2 – Falta da anotação @Transactional

**Arquivo:**

`ProfessorController.java`

### Problema

O método responsável pelo cadastro de professores não possui a anotação `@Transactional`.

Sem essa anotação, a persistência dos dados pode não ocorrer corretamente dentro de uma transação.

### Correção

Adicionar a anotação acima do método.

```java
@Transactional
```

---

# Erro 3 – Entidade mapeada para a tabela incorreta

**Arquivo:**

`Aluno.java`

### Problema

A entidade `Aluno` está sendo mapeada para a tabela `professores`.

```java
@Table(name = "professores")
```

Isso faz com que os registros de alunos sejam gravados na tabela errada.

### Correção

Alterar o nome da tabela.

```java
@Table(name = "alunos")
```

---

# Erro 4 – Nome e e-mail invertidos

**Arquivo:**

`DadosListagemAluno.java`

### Problema

No construtor de listagem, os métodos utilizados para preencher nome e e-mail estão invertidos.

Exemplo:

```java
aluno.getEmail()
```

está sendo utilizado no campo nome.

E

```java
aluno.getNome()
```

está sendo utilizado no campo e-mail.

### Correção

Trocar as chamadas para seus respectivos campos.

---

# Erro 5 – Método de atualização utilizando POST

**Arquivo:**

`AlunoController.java`

### Problema

O método responsável pela atualização utiliza:

```java
@PostMapping
```

O método HTTP adequado para atualização de recursos é o `PUT`.

### Correção

Alterar para:

```java
@PutMapping
```

---

# Erro 6 – Atualização do atributo incorreto

**Arquivo:**

`Professor.java`

### Problema

Ao atualizar o e-mail do professor, o código altera o atributo `nome`.

```java
this.nome = dados.email();
```

Como consequência, o nome do professor é substituído pelo e-mail.

### Correção

Alterar para:

```java
this.email = dados.email();
```

---

# Erro 7 – Nome incorreto do PathVariable

**Arquivo:**

`MatriculaController.java`

### Problema

O endpoint recebe:

```java
@DeleteMapping("/{id}")
```

Entretanto, o parâmetro foi declarado como:

```java
@PathVariable Integer ids
```

O Spring procura um parâmetro chamado `id`, mas encontra `ids`, ocasionando erro durante a execução.

### Correção

Alterar para:

```java
@PathVariable Integer id
```

---

# Erro 8 – Tipo incorreto do identificador do Repository

**Arquivo:**

`AlunoRepository.java`

### Problema

O repository foi declarado utilizando `String` como tipo do identificador.

```java
public interface AlunoRepository extends JpaRepository<Aluno, String>
```

Porém, a entidade `Aluno` possui o atributo:

```java
private Integer id;
```

Os tipos precisam ser iguais.

### Correção

Alterar para:

```java
public interface AlunoRepository extends JpaRepository<Aluno, Integer>
```

---

# Conclusão

Durante a revisão do projeto foram encontrados oito erros relacionados ao mapeamento de entidades, utilização de anotações do Spring Boot, tipos de dados e implementação dos endpoints REST.

Após realizar todas as correções descritas neste documento, o projeto passa a apresentar maior consistência entre as entidades, controllers e repositories, além de seguir corretamente as boas práticas do Spring Boot e do padrão REST.

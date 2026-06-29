package com.github.app.model.aluno;

// DTO utilizado para listar alunos com campos resumidos.
public record DadosListagemAluno(
    Integer id,
    String nome,
    String email,
    String ra,
    String curso
) {
    public DadosListagemAluno(Aluno aluno) {
        this(
            aluno.getId(),
            aluno.getNome(), // ERRO: Está invertido, o nome e o email.
            aluno.getEmail(), 
            aluno.getRa(),
            aluno.getCurso()
        );
    }
}

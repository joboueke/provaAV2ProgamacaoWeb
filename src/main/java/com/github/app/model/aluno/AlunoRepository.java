package com.github.app.model.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

// ERRO: o segundo parâmetro de JpaRepository deve ser Integer
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}

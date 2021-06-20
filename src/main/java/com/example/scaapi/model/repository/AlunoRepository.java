package com.example.scaapi.model.repository;

import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByTurmas(Optional<Turma> turma);
}
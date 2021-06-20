package com.example.scaapi.model.repository;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByCurso(Optional<Curso> curso);

}

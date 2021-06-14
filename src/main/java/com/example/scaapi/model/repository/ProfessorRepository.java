package com.example.scaapi.model.repository;

import com.example.scaapi.model.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}

package com.example.scaapi.service;

import com.example.scaapi.api.dto.ProfessorDTO;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> getProfessores() {
        return repository.findAll();
    }

    public Optional<Professor> getProfessorById(Long id) {
        return repository.findById(id);
    }
}

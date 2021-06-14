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

    public List<ProfessorDTO> getProfessores() {
        List<ProfessorDTO> list = repository.findAll().stream().map(ProfessorDTO::create).collect(Collectors.toList());
        return list;
    }

    public ProfessorDTO getProfessorById(Long id) {
        Optional<Professor> professor = repository.findById(id);
        return professor.map(ProfessorDTO::create).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
}

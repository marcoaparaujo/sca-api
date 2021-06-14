package com.example.scaapi.service;

import com.example.scaapi.api.dto.DisciplinaDTO;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<DisciplinaDTO> getDisciplinas() {
        List<DisciplinaDTO> list = repository.findAll().stream().map(DisciplinaDTO::create).collect(Collectors.toList());
        return list;
    }

    public DisciplinaDTO getDisciplinaById(Long id) {
        Optional<Disciplina> disciplina = repository.findById(id);
        return disciplina.map(DisciplinaDTO::create).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }
}


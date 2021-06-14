package com.example.scaapi.service;

import com.example.scaapi.api.dto.TurmaDTO;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    private TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public List<TurmaDTO> getTurmas() {
        List<TurmaDTO> list = repository.findAll().stream().map(TurmaDTO::create).collect(Collectors.toList());
        return list;
    }

    public TurmaDTO getTurmaById(Long id) {
        Optional<Turma> turma = repository.findById(id);
        return turma.map(TurmaDTO::create).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }
}

package com.example.scaapi.service;

import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {
    private TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public List<Turma> getTurmas() {
        return repository.findAll();
    }

    public Optional<Turma> getTurmaById(Long id) {
        return repository.findById(id);
    }
}

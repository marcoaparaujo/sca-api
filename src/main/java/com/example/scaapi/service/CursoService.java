package com.example.scaapi.service;

import com.example.scaapi.api.dto.CursoDTO;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.repository.CursoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> getCursos() {
        return repository.findAll();
    }

    public Optional<Curso> getById(Long id) {
        return repository.findById(id);
    }
}

package com.example.scaapi.service;

import com.example.scaapi.api.dto.CursoDTO;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.repository.CursoRepository;
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

    public List<CursoDTO> getCursos() {
        List<CursoDTO> list = repository.findAll().stream().map(CursoDTO::create).collect(Collectors.toList());
        return list;
    }

    public CursoDTO getCursoById(Long id) {
        Optional<Curso> curso = repository.findById(id);
        return curso.map(CursoDTO::create).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

}

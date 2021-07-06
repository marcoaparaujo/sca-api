package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CursoService {

    private CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> getCursos() {
        return repository.findAll();
    }

    public Optional<Curso> getCursoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Curso salvar(Curso curso) {
        validar(curso);
        return repository.save(curso);
    }

    public void validar(Curso curso) {
        if (curso.getNome() == null || curso.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }
}

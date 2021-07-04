package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Professor salvar(Professor professor) {
        validar(professor);
        professor = repository.save(professor);
        return professor;
    }

    public void validar(Professor professor) {
        if (professor.getMatricula() == null || professor.getMatricula() == 0) {
            throw new RegraNegocioException("Matrícula inválida");
        }
        if (professor.getNome() == null || professor.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (professor.getCurso() == null || professor.getCurso().getId() == null || professor.getCurso().getId() == 0) {
            throw new RegraNegocioException("Curso inválido");
        }
    }
}

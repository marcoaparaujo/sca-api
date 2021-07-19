package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfessorService {

    private ProfessorRepository repository;

    private final TurmaService turmaService;

    public ProfessorService(ProfessorRepository repository, TurmaService turmaService) {
        this.repository = repository;
        this.turmaService = turmaService;
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
        return repository.save(professor);
    }

    @Transactional
    public void excluir(Professor professor) {
        Objects.requireNonNull(professor.getId());
        for (Turma turma : professor.getTurmas()) {
            turma.setProfessor(null);
            turmaService.salvar(turma);
        }
        repository.delete(professor);
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

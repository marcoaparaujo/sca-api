package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Turma salvar(Turma turma) {
        validar(turma);
        turma = repository.save(turma);
        return turma;
    }

    public void validar(Turma turma) {
        if (turma.getAno() == null || turma.getAno() == 0) {
            throw new RegraNegocioException("Ano inválido");
        }
        if (turma.getSemestre() == null || turma.getSemestre() == 0) {
            throw new RegraNegocioException("Semestre inválido");
        }
        if (turma.getNome() == null || turma.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (turma.getDisciplina() == null || turma.getDisciplina().getId() == null || turma.getDisciplina().getId() == 0) {
            throw new RegraNegocioException("Disciplina inválida");
        }
    }
}

package com.example.scaapi.service;

import com.example.scaapi.api.dto.AlunoDTO;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<AlunoDTO> getAlunos() {
        List<AlunoDTO> list = repository.findAll().stream().map(AlunoDTO::create).collect(Collectors.toList());
        return list;
    }

    public AlunoDTO getAlunoById(Long id) {
        Optional<Aluno> curso = repository.findById(id);
        return curso.map(AlunoDTO::create).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}

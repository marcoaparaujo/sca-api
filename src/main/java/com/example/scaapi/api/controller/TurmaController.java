package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;
import com.example.scaapi.api.dto.TurmaDTO;
import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.*;
import com.example.scaapi.service.DisciplinaService;
import com.example.scaapi.service.ProfessorService;
import com.example.scaapi.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService service;
    private final DisciplinaService disciplinaService;
    private final ProfessorService professorService;

    @GetMapping()
    public ResponseEntity get() {
        List<Turma> turmas = service.getTurmas();
        return ResponseEntity.ok(turmas.stream().map(TurmaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Turma> turma = service.getTurmaById(id);
        if (!turma.isPresent()) {
            return new ResponseEntity("Turma não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(turma.map(TurmaDTO::create));
    }

    @GetMapping("{id}/alunos")
    public ResponseEntity getDisciplinas(@PathVariable("id") Long id) {
        Optional<Turma> turma = service.getTurmaById(id);
        if (!turma.isPresent()) {
            return new ResponseEntity("Turma não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(turma.get().getAlunos().stream().map(AlunoDTO::create).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity post(TurmaDTO dto) {
        try {
            Turma turma = converter(dto);
            turma = service.salvar(turma);
            return new ResponseEntity(turma, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, TurmaDTO dto) {
        if (!service.getTurmaById(id).isPresent()) {
            return new ResponseEntity("Turma não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Turma turma = converter(dto);
            turma.setId(id);
            service.salvar(turma);
            return ResponseEntity.ok(turma);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Turma converter(TurmaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Turma turma = modelMapper.map(dto, Turma.class);
        if (dto.getIdDisciplina() != null) {
            Optional<Disciplina> disciplina = disciplinaService.getDisciplinaById(dto.getIdDisciplina());
            if (!disciplina.isPresent()) {
                turma.setDisciplina(null);
            } else {
                turma.setDisciplina(disciplina.get());
            }
        }
        if (dto.getIdProfessor() != null) {
            Optional<Professor> professor = professorService.getProfessorById(dto.getIdProfessor());
            if (!professor.isPresent()) {
                turma.setProfessor(null);
            } else {
                turma.setProfessor(professor.get());
            }
        }
        return turma;
    }
}



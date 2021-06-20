package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;
import com.example.scaapi.api.dto.TurmaDTO;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.service.AlunoService;
import com.example.scaapi.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService service;

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
}



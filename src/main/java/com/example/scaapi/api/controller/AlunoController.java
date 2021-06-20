package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;

import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.service.AlunoService;
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
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Aluno> alunos = service.getAlunos();
        return ResponseEntity.ok(alunos.stream().map(AlunoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Aluno> aluno = service.getAlunoById(id);
        if (aluno.isEmpty()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(AlunoDTO::create));
    }
}

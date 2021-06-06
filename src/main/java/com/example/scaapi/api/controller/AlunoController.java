package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;

import com.example.scaapi.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<AlunoDTO> alunos = service.getAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        AlunoDTO aluno = service.getAlunoById(id);
        return ResponseEntity.ok(aluno);
    }
}

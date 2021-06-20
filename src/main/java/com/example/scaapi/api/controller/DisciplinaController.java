package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.DisciplinaDTO;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.service.DisciplinaService;
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
@RequestMapping("/api/v1/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping()
    public ResponseEntity get() {
       List<Disciplina> disciplinas = service.getDisciplinas();
        return ResponseEntity.ok(disciplinas.stream().map(DisciplinaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Disciplina> disciplina = service.getDisciplinaById(id);
        if (disciplina.isEmpty()) {
            return new ResponseEntity("Disciplina não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(disciplina.map(DisciplinaDTO::create));
    }
}

package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.DisciplinaDTO;
import com.example.scaapi.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<DisciplinaDTO> disciplinas = service.getDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        DisciplinaDTO disciplina = service.getDisciplinaById(id);
        return ResponseEntity.ok(disciplina);
    }
}

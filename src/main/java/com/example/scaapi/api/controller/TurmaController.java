package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.TurmaDTO;
import com.example.scaapi.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<TurmaDTO> turmas = service.getTurmas();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        TurmaDTO turma = service.getTurmaById(id);
        return ResponseEntity.ok(turma);
    }
}

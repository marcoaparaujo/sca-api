package com.example.scaapi.api.controller;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Curso> cursos = service.getCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Curso> curso = service.getCursoById(id);
        return ResponseEntity.ok(curso);
    }
}
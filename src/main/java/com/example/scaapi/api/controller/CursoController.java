package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.CursoDTO;
import com.example.scaapi.api.dto.DisciplinaDTO;
import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.service.CursoService;
import com.example.scaapi.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;
    private final DisciplinaService disciplinaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Curso> cursos = service.getCursos();
        return ResponseEntity.ok(cursos.stream().map(CursoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Curso> curso = service.getCursoById(id);
        if (!curso.isPresent()) {
            return new ResponseEntity("Curso não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(curso.map(CursoDTO::create));
    }

    @GetMapping("{id}/disciplinas")
    public ResponseEntity getDisciplinas(@PathVariable("id") Long id) {
        Optional<Curso> curso = service.getCursoById(id);
        if (!curso.isPresent()) {
            return new ResponseEntity("Curso não encontrado", HttpStatus.NOT_FOUND);
        }
        List<Disciplina> disciplinas = disciplinaService.getDisciplinasByCurso(curso);
        return ResponseEntity.ok(disciplinas.stream().map(DisciplinaDTO::create).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity post(CursoDTO dto) {
        try {
            Curso curso = converter(dto);
            curso = service.salvar(curso);
            return new ResponseEntity(curso, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Curso converter(CursoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Curso.class);
    }
}
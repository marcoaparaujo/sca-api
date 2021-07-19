package com.example.scaapi.api.controller;

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
@RequestMapping("/api/v1/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService service;
    private final CursoService cursoService;

    @GetMapping()
    public ResponseEntity get() {
       List<Disciplina> disciplinas = service.getDisciplinas();
        return ResponseEntity.ok(disciplinas.stream().map(DisciplinaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Disciplina> disciplina = service.getDisciplinaById(id);
        if (!disciplina.isPresent()) {
            return new ResponseEntity("Disciplina não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(disciplina.map(DisciplinaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(DisciplinaDTO dto) {
        try {
            Disciplina disciplina = converter(dto);
            disciplina = service.salvar(disciplina);
            return new ResponseEntity(disciplina, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, DisciplinaDTO dto) {
        if (!service.getDisciplinaById(id).isPresent()) {
            return new ResponseEntity("Disciplina não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Disciplina disciplina = converter(dto);
            disciplina.setId(id);
            service.salvar(disciplina);
            return ResponseEntity.ok(disciplina);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Disciplina> disciplina = service.getDisciplinaById(id);
        if (!disciplina.isPresent()) {
            return new ResponseEntity("Disciplina não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(disciplina.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Disciplina converter(DisciplinaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Disciplina disciplina = modelMapper.map(dto, Disciplina.class);
        if (dto.getIdCurso() != null) {
            Optional<Curso> curso = cursoService.getCursoById(dto.getIdCurso());
            if (!curso.isPresent()) {
                disciplina.setCurso(null);
            } else {
                disciplina.setCurso(curso.get());
            }
        }
        return disciplina;
    }
}

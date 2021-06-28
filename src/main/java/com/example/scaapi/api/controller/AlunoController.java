package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.service.AlunoService;
import com.example.scaapi.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;
    private final CursoService cursoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Aluno> alunos = service.getAlunos();
        return ResponseEntity.ok(alunos.stream().map(AlunoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Aluno> aluno = service.getAlunoById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(AlunoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(AlunoDTO dto) {
        try {
            Aluno aluno = converter(dto);
            aluno = service.salvar(aluno);
            return new ResponseEntity(aluno, HttpStatus.CREATED);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Aluno converter(AlunoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Aluno aluno = modelMapper.map(dto, Aluno.class);
        if (dto.getIdCurso() != null) {
            Optional<Curso> curso = cursoService.getCursoById(dto.getIdCurso());
            if (!curso.isPresent()) {
                aluno.setCurso(null);
            } else {
                aluno.setCurso(curso.get());
            }
        }
        return aluno;
    }
}

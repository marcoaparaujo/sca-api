package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Endereco;
import com.example.scaapi.service.AlunoService;
import com.example.scaapi.service.CursoService;
import com.example.scaapi.service.EnderecoService;
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
    private final EnderecoService enderecoService;

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
            Endereco endereco = enderecoService.salvar(aluno.getEndereco());
            aluno.setEndereco(endereco);
            aluno = service.salvar(aluno);
            return new ResponseEntity(aluno, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AlunoDTO dto) {
        if (!service.getAlunoById(id).isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Aluno aluno = converter(dto);
            aluno.setId(id);
            Endereco endereco = enderecoService.salvar(aluno.getEndereco());
            aluno.setEndereco(endereco);
            service.salvar(aluno);
            return ResponseEntity.ok(aluno);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Aluno converter(AlunoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Aluno aluno = modelMapper.map(dto, Aluno.class);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        aluno.setEndereco(endereco);
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

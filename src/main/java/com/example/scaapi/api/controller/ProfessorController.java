package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.ProfessorDTO;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.service.ProfessorService;
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
@RequestMapping("/api/v1/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Professor> professores = service.getProfessores();
        return ResponseEntity.ok(professores.stream().map(ProfessorDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Professor> professor = service.getProfessorById(id);
        if (!professor.isPresent()) {
            return new ResponseEntity("Professor não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(professor.map(ProfessorDTO::create));
    }
}
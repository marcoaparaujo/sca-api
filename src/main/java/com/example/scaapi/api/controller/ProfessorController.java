package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.ProfessorDTO;
import com.example.scaapi.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping()
    public ResponseEntity get() {
        List<ProfessorDTO> professores = service.getProfessores();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        ProfessorDTO professor = service.getProfessorById(id);
        return ResponseEntity.ok(professor);
    }
}
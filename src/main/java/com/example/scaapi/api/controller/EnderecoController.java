package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.EnderecoDTO;
import com.example.scaapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<EnderecoDTO> enderecos = service.getEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        EnderecoDTO endereco = service.getEnderecoById(id);
        return ResponseEntity.ok(endereco);
    }
}


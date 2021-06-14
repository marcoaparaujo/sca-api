package com.example.scaapi.service;

import com.example.scaapi.api.dto.EnderecoDTO;
import com.example.scaapi.model.entity.Endereco;
import com.example.scaapi.model.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public EnderecoDTO getEnderecoById(Long id) {
        Optional<Endereco> endereco = repository.findById(id);
        return endereco.map(EnderecoDTO::create).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }
}

package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CursoDTO {
    private Long id;
    private String nome;

    public static CursoDTO create(Curso curso) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(curso, CursoDTO.class);
    }

    public static Curso converter(CursoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Curso.class);
    }
}
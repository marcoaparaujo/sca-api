package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private String ementa;
    private String bibliografia;
    private Integer cargaHoraria;
    private Curso curso;

    public static DisciplinaDTO create(Disciplina disciplina) {
        ModelMapper modelMapper = new ModelMapper();
        DisciplinaDTO dto = modelMapper.map(disciplina, DisciplinaDTO.class);
        return dto;
    }
}

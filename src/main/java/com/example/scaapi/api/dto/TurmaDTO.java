package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO {

    private Long id;
    private Integer ano;
    private Integer semestre;
    private String nome;

    public static TurmaDTO create(Turma turma) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(turma, TurmaDTO.class);
    }
}

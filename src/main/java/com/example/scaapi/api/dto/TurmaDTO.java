package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO {

    private Long id;
    private Integer ano;
    private Integer semestre;
    private String nome;
    private Disciplina disciplina;
    private Long idProfessor;

    public static TurmaDTO create(Turma turma) {
        ModelMapper modelMapper = new ModelMapper();
        TurmaDTO dto = modelMapper.map(turma, TurmaDTO.class);
        assert dto.getIdProfessor().equals(turma.getProfessor().getId());
        return dto;
    }
}


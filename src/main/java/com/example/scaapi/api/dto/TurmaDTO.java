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
    private Long idDisciplina;
    private String nomeDisciplina;

    public static TurmaDTO create(Turma turma) {
        ModelMapper modelMapper = new ModelMapper();
        TurmaDTO dto = modelMapper.map(turma, TurmaDTO.class);
        assert dto.getIdDisciplina().equals(turma.getDisciplina().getId());
        assert dto.getNomeDisciplina().equals(turma.getDisciplina().getNome());
        return dto;
    }
}


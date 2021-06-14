package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Endereco;
import com.example.scaapi.model.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
    private Long id;
    private Integer matricula;
    private String nome;
    private Long idCurso;
    private String nomeCurso;
    private Endereco endereco;

    public static ProfessorDTO create(Professor professor) {
        ModelMapper modelMapper = new ModelMapper();
        ProfessorDTO dto = modelMapper.map(professor, ProfessorDTO.class);
        assert dto.getIdCurso().equals(professor.getCurso().getId());
        assert dto.getNomeCurso().equals(professor.getCurso().getNome());
        return dto;
    }
}

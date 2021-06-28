package com.example.scaapi.api.dto;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Endereco;
import com.example.scaapi.service.CursoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long id;
    private Integer matricula;
    private String nome;
    private Long idCurso;
    private String nomeCurso;
    private Endereco endereco;

    public static AlunoDTO create(Aluno aluno) {
        ModelMapper modelMapper = new ModelMapper();
        AlunoDTO dto = modelMapper.map(aluno, AlunoDTO.class);
        assert dto.getIdCurso().equals(aluno.getCurso().getId());
        assert dto.getNomeCurso().equals(aluno.getCurso().getNome());
        return dto;
    }

}

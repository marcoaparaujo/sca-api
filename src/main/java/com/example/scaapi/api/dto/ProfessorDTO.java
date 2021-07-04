package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Curso;
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
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public static ProfessorDTO create(Professor professor) {
        ModelMapper modelMapper = new ModelMapper();
        ProfessorDTO dto = modelMapper.map(professor, ProfessorDTO.class);
        dto.logradouro = professor.getEndereco().getLogradouro();
        dto.numero = professor.getEndereco().getNumero();
        dto.complemento = professor.getEndereco().getComplemento();
        dto.bairro = professor.getEndereco().getBairro();
        dto.cidade = professor.getEndereco().getCidade();
        dto.uf = professor.getEndereco().getUf();
        dto.cep = professor.getEndereco().getCep();
        return dto;
    }
}

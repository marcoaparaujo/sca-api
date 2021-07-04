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
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public static AlunoDTO create(Aluno aluno) {
        ModelMapper modelMapper = new ModelMapper();
        AlunoDTO dto = modelMapper.map(aluno, AlunoDTO.class);
        dto.logradouro = aluno.getEndereco().getLogradouro();
        dto.numero = aluno.getEndereco().getNumero();
        dto.complemento = aluno.getEndereco().getComplemento();
        dto.bairro = aluno.getEndereco().getBairro();
        dto.cidade = aluno.getEndereco().getCidade();
        dto.uf = aluno.getEndereco().getUf();
        dto.cep = aluno.getEndereco().getCep();
        return dto;
    }

}

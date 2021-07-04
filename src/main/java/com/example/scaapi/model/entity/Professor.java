package com.example.scaapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Pessoa {

    private Integer matricula;

    @ManyToOne
    private Curso curso;

//    @OneToMany (mappedBy = "professor")
//    private List<Turma> turmas;
}

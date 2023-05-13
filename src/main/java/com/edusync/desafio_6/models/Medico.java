package com.edusync.desafio_6.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(length = 255)
    private String nome;
    @Column(length = 155)
    private String cpf;
    @Column(length = 255)
    private String email;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;
}

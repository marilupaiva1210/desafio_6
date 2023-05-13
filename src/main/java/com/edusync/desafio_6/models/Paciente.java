package com.edusync.desafio_6.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String idade;
    @Column(length = 255)
    private String raca;

}

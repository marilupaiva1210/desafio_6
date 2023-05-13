package com.edusync.desafio_6.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_clinica")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(length = 255)
    private String cnpj;
    @Column(length = 255)
    private String nome;

}

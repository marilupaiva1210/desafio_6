package com.edusync.desafio_6.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(name = "data_hora")
    private String dataHora;
    @Column(length = 255)
    private String procedimento;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}

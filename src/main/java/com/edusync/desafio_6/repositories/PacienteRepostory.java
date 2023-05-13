package com.edusync.desafio_6.repositories;

import com.edusync.desafio_6.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepostory extends JpaRepository<Paciente, Integer> {
    public List<Paciente> findByNameContainingIgnoreCase(String name);

    public Paciente findByName(String username);
}

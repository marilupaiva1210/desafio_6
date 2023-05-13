package com.edusync.desafio_6.repositories;

import com.edusync.desafio_6.models.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
    public List<Clinica> findByNomeContainingIgnoreCase(String paciente);
}

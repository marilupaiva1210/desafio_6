package com.edusync.desafio_6.repositories;

import com.edusync.desafio_6.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    public List<Medico> findByNomeContainingIgnoreCase(String nome);
}

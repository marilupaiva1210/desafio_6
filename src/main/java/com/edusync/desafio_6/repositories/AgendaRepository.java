package com.edusync.desafio_6.repositories;

import com.edusync.desafio_6.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
}

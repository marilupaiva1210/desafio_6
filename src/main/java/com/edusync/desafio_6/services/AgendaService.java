package com.edusync.desafio_6.services;

import com.edusync.desafio_6.models.Agenda;
import com.edusync.desafio_6.models.Paciente;
import com.edusync.desafio_6.repositories.AgendaRepository;
import com.edusync.desafio_6.repositories.PacienteRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public void cadastrar(Agenda agenda) {

        agendaRepository.save(agenda);
    }

    public List<Agenda> listar() {

        return agendaRepository.findAll();
    }

    public void update(Integer codigo, Agenda agenda) {
        if (agendaRepository.existsById(codigo)) {
            agendaRepository.save(agenda);
        }
    }

    public Agenda listarCodigo(Integer codigo) {

        Optional<Agenda> optAgenda = agendaRepository.findById(codigo);
        if (optAgenda.isEmpty()) {

            return null;
        }
        return optAgenda.get();
    }

    public void remover(Integer codigo) {

        if (agendaRepository.existsById(codigo)){
            agendaRepository.deleteById(codigo);
        }
    }
}

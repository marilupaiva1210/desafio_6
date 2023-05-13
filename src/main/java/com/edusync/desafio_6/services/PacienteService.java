package com.edusync.desafio_6.services;

import com.edusync.desafio_6.models.Paciente;
import com.edusync.desafio_6.repositories.PacienteRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepostory pacienteRepostory;

    public void cadastrar(Paciente paciente) {

        pacienteRepostory.save(paciente);
    }

    public List<Paciente> listar() {

        return pacienteRepostory.findAll();
    }

    public void update(Integer codigo, Paciente paciente) {
        if (pacienteRepostory.existsById(codigo)) {
            pacienteRepostory.save(paciente);
        }
    }

    public Paciente listarCodigo(Integer codigo) {

        Optional<Paciente> optPaciente = pacienteRepostory.findById(codigo);
        if (optPaciente.isEmpty()) {

            return null;
        }
        return optPaciente.get();
    }

    public void remover(Integer codigo) {

        if (pacienteRepostory.existsById(codigo)){
            pacienteRepostory.deleteById(codigo);
        }
    }
}

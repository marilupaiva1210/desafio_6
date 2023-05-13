package com.edusync.desafio_6.services;

import com.edusync.desafio_6.models.Medico;
import com.edusync.desafio_6.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public void cadastrar(Medico medico) {

        medicoRepository.save(medico);
    }

    public List<Medico> listar() {

        return medicoRepository.findAll();
    }

    public void update(Integer codigo, Medico medico) {
        if (medicoRepository.existsById(codigo)) {
            medicoRepository.save(medico);
        }
    }

    public Medico listarCodigo(Integer codigo) {

        Optional<Medico> optMedico = medicoRepository.findById(codigo);
        if (optMedico.isEmpty()) {

            return null;
        }
        return optMedico.get();
    }

    public void remover(Integer codigo) {

        if (medicoRepository.existsById(codigo)){
            medicoRepository.deleteById(codigo);
        }
    }
}

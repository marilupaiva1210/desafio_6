package com.edusync.desafio_6.services;

import com.edusync.desafio_6.models.Clinica;
import com.edusync.desafio_6.repositories.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    public void cadastrar(Clinica agenda) {

        clinicaRepository.save(agenda);
    }

    public List<Clinica> listar() {

        return clinicaRepository.findAll();
    }

    public void update(Integer codigo, Clinica agenda) {
        if (clinicaRepository.existsById(codigo)) {
            clinicaRepository.save(agenda);
        }
    }

    public Clinica listarCodigo(Integer codigo) {

        Optional<Clinica> optAgenda = clinicaRepository.findById(codigo);
        if (optAgenda.isEmpty()) {

            return null;
        }
        return optAgenda.get();
    }

    public void remover(Integer codigo) {

        if (clinicaRepository.existsById(codigo)){
            clinicaRepository.deleteById(codigo);
        }
    }
}

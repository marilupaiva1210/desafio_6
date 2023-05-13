package com.edusync.desafio_6.services;

import com.edusync.desafio_6.models.Clinica;
import com.edusync.desafio_6.repositories.ClinicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClinicaServiceTest {

    @InjectMocks
    private ClinicaService clinicaService;

    @Mock
    private ClinicaRepository clinicaRepository;

    @Test
    public void testaListagemClinica(){

        //prepara
        List<Clinica> listaTemp = new ArrayList<>();
        listaTemp.add(new Clinica());
        when(clinicaRepository.findAll()).thenReturn(listaTemp);

        //executa
        int quantidade = clinicaService.listar().size();

        //valida
        Assertions.assertEquals(1, quantidade);
    }
}

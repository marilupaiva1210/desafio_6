package com.edusync.desafio_6.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClinicaTest {

    @Test
    public void testaFieldCnpj() {

        //prepara
        Clinica clinica = new Clinica();

        //executa
        clinica.setCnpj("testes1");

        //valida
        Assertions.assertEquals("testes1", clinica.getCnpj());

    }
}

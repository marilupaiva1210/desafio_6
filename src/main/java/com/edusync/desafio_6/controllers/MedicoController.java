package com.edusync.desafio_6.controllers;

import com.edusync.desafio_6.models.Clinica;
import com.edusync.desafio_6.models.Medico;
import com.edusync.desafio_6.services.ClinicaService;
import com.edusync.desafio_6.services.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController
@RequestMapping(value = "/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private ClinicaService clinicaService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastro dos médicos", description = "Faz o cadastro dos médicos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrarMedico(@RequestBody Medico medico,
                                          @RequestParam Integer codigoClinica) {

        Clinica clinica = clinicaService.listarCodigo(codigoClinica);
        medico.setClinica(clinica);

        try {
            medicoService.cadastrar(medico);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o médico.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Médico cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista os médicos", description = "Faz a listagem de todos os médicos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarMedicos() {

        try {
            return new ResponseEntity(medicoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista médicos por código", description = "Faz a listagem do médico referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(medicoService.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera os médicos", description = "Faz a alteração dos médicos baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody Medico medico) {

        try {
            medicoService.update(codigo, medico);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(medico, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta os médicos", description = "Faz a exclusão do medico escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            medicoService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Médico do código " + codigo + " foi removido! ", HttpStatus.OK);
    }
}
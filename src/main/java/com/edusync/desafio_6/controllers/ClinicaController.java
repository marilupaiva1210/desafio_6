package com.edusync.desafio_6.controllers;

import com.edusync.desafio_6.models.Clinica;

import com.edusync.desafio_6.services.ClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController
@RequestMapping(value = "/clinica")
public class ClinicaController {
    @Autowired
    private ClinicaService clinicaService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastro da Clínica", description = "Faz o cadastro das clínicas")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrarClinicas(@RequestBody Clinica clinica) {

        try {
            clinicaService.cadastrar(clinica);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar a clínica.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Clínica cadastrada!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista as clínicas", description = "Faz a listagem de todas as clínicas")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarClinicas() {

        try {
            return new ResponseEntity(clinicaService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista clínicas por cnpj", description = "Faz a listagem da clínica referente ao cnpj informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(clinicaService.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera as clínicas", description = "Faz a alteração das clínicas baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody Clinica clinica) {

        try {
            clinicaService.update(codigo, clinica);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(clinica, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta as clínicas", description = "Faz a exclusão da clínica escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            clinicaService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Clínica do código " + codigo + " foi removida! ", HttpStatus.OK);
    }
}
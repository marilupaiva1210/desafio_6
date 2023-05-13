package com.edusync.desafio_6.controllers;

import com.edusync.desafio_6.models.Paciente;
import com.edusync.desafio_6.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController
@RequestMapping(value = "/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastro dos pacientes", description = "Faz o cadastro dos pacientes")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrarPaciente(@RequestBody Paciente paciente) {

        try {
            pacienteService.cadastrar(paciente);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o paciente.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Paciente cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista os pacientes", description = "Faz a listagem de todos os pacientes")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPacientes() {

        try {
            return new ResponseEntity(pacienteService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista pacientes por código", description = "Faz a listagem do paciente referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(pacienteService.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera os pacientes", description = "Faz a alteração dos pacientes baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody Paciente paciente) {

        try {
            pacienteService.update(codigo, paciente);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(paciente, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta os pacientes", description = "Faz a exclusão do paciente escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            pacienteService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Paciente do código " + codigo + " foi removido! ", HttpStatus.OK);
    }
}
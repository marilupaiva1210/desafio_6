package com.edusync.desafio_6.controllers;

import com.edusync.desafio_6.models.Agenda;
import com.edusync.desafio_6.models.Medico;
import com.edusync.desafio_6.models.Paciente;
import com.edusync.desafio_6.services.AgendaService;
import com.edusync.desafio_6.services.MedicoService;
import com.edusync.desafio_6.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoService medicoService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastro das agendas", description = "Faz o cadastro das agendas")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrarAgenda(@RequestBody Agenda agenda,
                                          @RequestParam Integer codigoPaciente,
                                          @RequestParam Integer codigoMedico) {

        Paciente paciente = pacienteService.listarCodigo(codigoPaciente);
        agenda.setPaciente(paciente);

        Medico medico = medicoService.listarCodigo(codigoMedico);
        agenda.setMedico(medico);

        try {
            agendaService.cadastrar(agenda);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar a agenda.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Agenda cadastrada!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista as agendas", description = "Faz a listagem de todas as agendas")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarAgendas() {

        try {
            return new ResponseEntity(agendaService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista agendas por código", description = "Faz a listagem da agenda referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(agendaService.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera as agendas", description = "Faz a alteração das agendas baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody Agenda agenda) {

        try {
            agendaService.update(codigo, agenda);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(agenda, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta as agendas", description = "Faz a exclusão da agenda escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            agendaService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Agenda do código " + codigo + " foi removida! ", HttpStatus.OK);
    }
}

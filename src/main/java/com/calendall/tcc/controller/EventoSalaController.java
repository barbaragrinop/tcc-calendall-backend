package com.calendall.tcc.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.dto.EventoSalaDTO;
import com.calendall.tcc.model.mapper.EventoSalaMapper;
import com.calendall.tcc.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/salas")
@Tag(name = "Sala", description = "Gerenciamento de salas")
public class EventoSalaController {

    @Autowired
    private SalaService _salaService;
    @Autowired
    private EventoSalaMapper _EventoSalaMapper;

        @PostMapping("/{id_sala}/criarEvento")
    @Operation(summary = "Cria um novo evento público", description = "Cria um novo evento em uma sala")
    public ResponseEntity<EventoSala> postEventoSala(@PathVariable Long id_sala, @RequestBody @Valid EventoSalaDTO eventoSala) {

        if(
            eventoSala.getDt_evento().isBefore(LocalDate.now()) ||
            eventoSala.getTitulo() == null
            ){
            return ResponseEntity.badRequest().build(); //400
        }

        EventoSala eventoSalaEntity = _EventoSalaMapper.toEntity(eventoSala, id_sala);

        EventoSala newEventoSala = _salaService.createEventoSala(id_sala, eventoSalaEntity);

        if(newEventoSala == null){
            return ResponseEntity.notFound().build(); //404
        }

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newEventoSala.getId_evento())
                        .toUri();
        return ResponseEntity.created(location).body(newEventoSala); //201
    }

    @GetMapping("/{id_sala}/listarEventos")
    @Operation(summary = "Lista todos os eventos de uma sala", description = "Lista todos os eventos de uma sala com o id informado")
    public ResponseEntity<List<EventoSala>> getEventos(@PathVariable Long id_sala) {
        List<EventoSala> eventos = _salaService.findAllEventos(id_sala);
        if(eventos == null){
            return ResponseEntity.noContent().build(); //404
        }
        return ResponseEntity.ok(eventos); //200
    }

    @DeleteMapping("/apagarEventoSala/{id_evento}")
    @Operation(summary = "Deleta um evento de uma sala", description = "Deleta um evento com o id informado")
    public ResponseEntity<EventoSala> deleteEventoSala(@PathVariable("id_evento") Long id_evento) {
        if(_salaService.deleteEventoSala(id_evento)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

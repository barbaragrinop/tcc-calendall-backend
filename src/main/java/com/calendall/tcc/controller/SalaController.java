package com.calendall.tcc.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.Sala;
import com.calendall.tcc.model.SalaUsuario;
import com.calendall.tcc.model.dto.EventoSalaDTO;
import com.calendall.tcc.model.dto.SalaDTO;
import com.calendall.tcc.model.mapper.EventoSalaMapper;
import com.calendall.tcc.model.mapper.SalaMapper;
import com.calendall.tcc.service.SalaService;
import com.calendall.tcc.service.SalaUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/salas")
@Tag(name = "Sala", description = "Gerenciamento de salas")
public class SalaController implements IController<Sala> {

    @Autowired
    private SalaService _salaService;
    @Autowired
    private SalaUsuarioService _salaUsuarioService;

    @Autowired
    private SalaMapper _salaMapper;
    @Autowired
    private EventoSalaMapper _EventoSalaMapper;   

    @Override
    @PostMapping("/criarSala")
    @Operation(summary = "Cria uma nova sala", description = "Cria uma nova sala")
    public ResponseEntity<Sala> post(@RequestBody @Valid SalaDTO sala) {
        Sala salaEntity = _salaMapper.toEntity(sala);

        Sala newSala = _salaService.create(salaEntity);
         URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newSala.getId_sala())
                        .toUri();
        return ResponseEntity.created(location).body(newSala);
    }

    @PostMapping("/{id_sala}/criarEvento")
    @Operation(summary = "Cria um novo evento", description = "Cria um novo evento em uma sala")
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
    

    @Override
    @GetMapping("/listarSalas")
    @Operation(summary = "Lista todas as salas", description = "Lista todas as salas")
    public ResponseEntity<List<Sala>> getAll() {
        List<Sala> salas = _salaService.findAll();
        if(salas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salas);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma sala", description = "Retorna uma sala com o id informado")
    public ResponseEntity<Sala> get(@PathVariable Long id) {
        Sala sala = _salaService.findById(id);
        if(sala == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sala);
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

    @GetMapping("/listarSalaUsuario")
    public ResponseEntity<List<SalaUsuario>> getSalaUsuarios() {
        List<SalaUsuario> salaUsuarios = _salaUsuarioService.findAll();
        if(salaUsuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salaUsuarios);
    }

    @Override
    @DeleteMapping("/apagarSala/{id}")
    @Operation(summary = "Deleta uma sala", description = "Deleta uma sala com o id informado")
    public ResponseEntity<Sala> delete(@PathVariable("id") Long id) {
        if(_salaService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PutMapping("/editarSala/{id}")
    @Operation(summary = "Edita uma sala", description = "Edita todos os dados de uma sala com o id informado")
    public ResponseEntity<Sala> put(@Valid @RequestBody Sala sala) {
       
        if(_salaService.update(sala)){
            return ResponseEntity.ok(sala);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Sala> patch(Sala obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }
    
}

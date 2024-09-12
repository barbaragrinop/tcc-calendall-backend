package com.calendall.tcc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calendall.tcc.model.Sala;
import com.calendall.tcc.model.dto.SalaDTO;
import com.calendall.tcc.model.mapper.SalaMapper;
import com.calendall.tcc.service.SalaService;

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
    private SalaMapper _mapper;

    @Override
    @PostMapping("/")
    @Operation(summary = "Cria uma nova sala", description = "Cria uma nova sala")
    public ResponseEntity<Sala> post(@RequestBody @Valid SalaDTO sala) {
        Sala salaEntity = _mapper.toEntity(sala);

        Sala newSala = _salaService.create(salaEntity);

         URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newSala.getId_sala())
                        .toUri();
        return ResponseEntity.created(location).body(newSala);
    }

    @Override
    public ResponseEntity<List<Sala>> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ResponseEntity<Sala> get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public ResponseEntity<Sala> put(Sala obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public ResponseEntity<Sala> patch(Sala obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }

    @Override
    public ResponseEntity<Sala> delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}

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
@RequestMapping("/sala")
@Tag(name = "Sala", description = "Gerenciamento de salas")
public class SalaController implements IController<Sala> {

    @Autowired
    private SalaService _salaService;
    @Autowired
    private SalaMapper _salaMapper;

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

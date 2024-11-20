package com.calendall.tcc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calendall.tcc.model.SalaUsuario;
import com.calendall.tcc.service.SalaService;
import com.calendall.tcc.service.SalaUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/sala")
@Tag(name = "Sala Usuário", description = "Gerenciamento de usuários nas salas")
public class SalaUsuarioController {

    @Autowired
    private SalaService _salaService;
    @Autowired
    private SalaUsuarioService _salaUsuarioService;

    @PostMapping("/{id_sala}/adicionarUsuario/{id_usuario}")
    @Operation(summary = "Adiciona usuário à sala", description = "Adiciona usuário existente à sala existente")
    public ResponseEntity<SalaUsuario> addUser(@PathVariable Long id_sala, @PathVariable Long id_usuario) {
        SalaUsuario newSalaUsuario = _salaService.adicionaUsario(id_sala, id_usuario);
        
        if(newSalaUsuario == null){
            return ResponseEntity.badRequest().build(); //400
        }

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newSalaUsuario.getId_SalaUsuario())
                        .toUri();
        return ResponseEntity.created(location).body(newSalaUsuario); //201
    }

    @GetMapping("/{id_sala}/listarUsuariosSala/")
    @Operation(summary = "Lista usuários de sala", description = "Lista todos os usuários de uma sala com id informado")
    public ResponseEntity<List<SalaUsuario>> getBySala (@PathVariable Long id_sala) {
        List<SalaUsuario> usersSala = _salaUsuarioService.findBySala(id_sala);
        if(usersSala.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usersSala);
    }

    @PatchMapping("/{id_sala}/adicionarVice/{id_usuario}")
    @Operation(summary = "Adicionar vice representante", description = "Adiciona vice representante em uma sala com id informado")
    public ResponseEntity<SalaUsuario> addVice (@PathVariable Long id_sala, @PathVariable Long id_usuario) {
        SalaUsuario vice = _salaUsuarioService.addVice(id_sala, id_usuario);
        if (vice != null){
            return ResponseEntity.ok(vice);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id_sala}/deletarUsuario/{id_usuario}")
    @Operation(summary = "Deleta um usuário de uma sala", description = "Deleta usuário da sala, com respectivos ids informados")
    public ResponseEntity<SalaUsuario> deleteUsuarioFromSala(@PathVariable Long id_usuario, @PathVariable Long id_sala) {
        if(_salaUsuarioService.deleteUsuarioFromSala(id_usuario, id_sala)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

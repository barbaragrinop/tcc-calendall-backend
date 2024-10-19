package com.calendall.tcc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendall.tcc.model.SalaUsuario;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.service.SalaUsuarioService;
import com.calendall.tcc.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Gerenciamento de usuários")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService _usuarioService;
    private final SalaUsuarioService _salaUsuarioService;

    @GetMapping("/{id_usuario}/salasListagem")
    @Operation(summary = "Lista salas de usuário", description = "Lista todas as salas de um usuário com id informado")
    public ResponseEntity<List<SalaUsuario>> getByUsuario (@PathVariable Long id_usuario) {
        List<SalaUsuario> salasUser = _salaUsuarioService.findByUsuario(id_usuario);
        if(salasUser.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salasUser);
    }

    @GetMapping("/usuariosListagem")
    @Operation(summary = "Lista todos os usuários", description = "Lista todos os usuários")
    public ResponseEntity<List<Usuario>> getAll() {

        List<Usuario> usuarios = _usuarioService.obterTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
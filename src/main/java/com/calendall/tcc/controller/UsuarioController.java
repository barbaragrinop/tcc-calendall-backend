package com.calendall.tcc.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calendall.tcc.model.Usuario;
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

    @GetMapping("/usuariosListagem")
    @Operation(summary = "Lista todos os usuários", description = "Lista todos os usuários")
    public ResponseEntity<List<Usuario>> getAll() {

        List<Usuario> usuarios = _usuarioService.obterTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
package com.calendall.tcc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService _usuarioService;

    @GetMapping("/usuariosListagem")

    public ResponseEntity<List<Usuario>> getAll(){

        List<Usuario> usuarios = _usuarioService.obterTodosUsuarios();
		return ResponseEntity.ok(usuarios);
	}
}
package com.calendall.tcc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import com.calendall.tcc.infra.security.TokenService;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dtos.CadastroDto;
import com.calendall.tcc.model.dtos.LoginDto;
import com.calendall.tcc.model.dtos.TokenJwtDto;
import com.calendall.tcc.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioService _usuarioService;
    private final TokenService _tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        try {
            var usuario = _usuarioService.AutenticarUsuario(loginDto);

            String token = _tokenService.GerarToken(usuario);

            return ResponseEntity.ok(new TokenJwtDto(token));

        } catch (Exception ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody CadastroDto cadastroDto) {

        try {
            Usuario novoUsuario = _usuarioService.CadastrarUsuario(cadastroDto);
            String token = _tokenService.GerarToken(novoUsuario);

            return ResponseEntity.ok(new TokenJwtDto(token));

        } catch (Exception ex) {
            
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
package com.calendall.tcc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import com.calendall.tcc.infra.security.TokenService;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dto.TokenJwtDto;
import com.calendall.tcc.model.dto.CadastroDto;
import com.calendall.tcc.model.dto.LoginDto;
import com.calendall.tcc.model.dto.RedefinicaoSenhaDTO;
import com.calendall.tcc.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autenticacao")
@Tag(name = "Autenticação", description = "Autenticação do usuário no sistema")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioService _usuarioService;
    private final TokenService _tokenService;

    @PostMapping("/login")
    @Operation(summary = "Realiza login do usuário", description = "Realiza login do usuário")

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
    @Operation(summary = "Cadastra um novo usuário", description = "Cadastra um novo usuário no sistema")
    public ResponseEntity<?> cadastrar(@RequestBody CadastroDto cadastroDto) {

        try {
            Usuario novoUsuario = _usuarioService.CadastrarUsuario(cadastroDto);
            String token = _tokenService.GerarToken(novoUsuario);

            return ResponseEntity.ok(new TokenJwtDto(token));

        } catch (Exception ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/redefinicaoSenha/{id_usuario}")
    @Operation(summary = "Redefine a senha do usuário", description = "Redefine a senha do usuário no sistema")
    public ResponseEntity<?> redefinirSenhaComValidacao(@PathVariable Long id_usuario,
            @RequestBody @Valid RedefinicaoSenhaDTO redefinicaoSenhaDto) {

        try {
            _usuarioService.RedefinirSenha(id_usuario, redefinicaoSenhaDto);

            return ResponseEntity.ok("Senha redefinida com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
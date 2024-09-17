package com.calendall.tcc.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import com.calendall.tcc.infra.security.TokenService;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dtos.AutenticacaoResponseDto;
import com.calendall.tcc.model.dtos.CadastroRequestDto;
import com.calendall.tcc.model.dtos.LoginDto;
import com.calendall.tcc.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {

        Usuario usuario = this.usuarioRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (passwordEncoder.matches(loginDto.senha(), usuario.getSenha())) {
            String token = this.tokenService.GerarToken(usuario);

            return ResponseEntity.ok(new AutenticacaoResponseDto(usuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody CadastroRequestDto cadastroDto) {

        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(cadastroDto.email());
        
        if(usuario.isEmpty()){
            Usuario novoUsuario = new Usuario();
            novoUsuario.setSenha(passwordEncoder.encode(cadastroDto.senha()));
            novoUsuario.setEmail(cadastroDto.email());
            novoUsuario.setNome(cadastroDto.nome());
            novoUsuario.setDt_nascimento(cadastroDto.dataNascimento());

            this.usuarioRepository.save(novoUsuario);

        String token = this.tokenService.GerarToken(novoUsuario);
        return ResponseEntity.ok(new AutenticacaoResponseDto(novoUsuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
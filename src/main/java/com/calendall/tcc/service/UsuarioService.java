package com.calendall.tcc.service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dto.CadastroDto;
import com.calendall.tcc.model.dto.LoginDto;
import com.calendall.tcc.model.dto.RedefinicaoSenhaDTO;
import com.calendall.tcc.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository _usuarioRepository;
    private final PasswordEncoder _passwordEncoder;

    public Usuario AutenticarUsuario(LoginDto loginDto) throws Exception {

        var usuario = _usuarioRepository.findByEmail(loginDto.email());

        if (usuario == null) {
            throw new Exception("Este usuário não existe");
        }

        if (!_passwordEncoder.matches(loginDto.senha(), usuario.getSenha())) {
            throw new Exception("Senha ou e-mail incorretos");
        }

        return usuario;
    }

    public Usuario CadastrarUsuario(CadastroDto cadastroDto) throws Exception {

        var usuario = _usuarioRepository.findByEmail(cadastroDto.email());

        if (usuario != null) {
            throw new Exception("O usuário já existe...");
        }

        if (!cadastroDto.senha().equals(cadastroDto.confirmacaoSenha())) {
            throw new Exception("As senhas não coincidem");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setSenha(_passwordEncoder.encode(cadastroDto.senha()));
        novoUsuario.setEmail(cadastroDto.email());
        novoUsuario.setNome(cadastroDto.nome());
        novoUsuario.setDt_nascimento(cadastroDto.dataNascimento());

        _usuarioRepository.save(novoUsuario);
        return novoUsuario;
    }

    public List<Usuario> obterTodosUsuarios() {
        return _usuarioRepository.findAll();
    }

    public void RedefinirSenha(Long usuarioId, RedefinicaoSenhaDTO redefinicaoSenhaDto) throws Exception {
        Usuario usuario = _usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));
    
        if (!_passwordEncoder.matches(redefinicaoSenhaDto.getSenhaAtual(), usuario.getSenha())) {
            throw new Exception("A senha atual está incorreta!");
        }
    
        if (!redefinicaoSenhaDto.getSenhaNova().equals(redefinicaoSenhaDto.getSenhaConfirmada())) {
            throw new Exception("A senha nova e a confirmação da mesma devem ser iguais!");
        }
    
        usuario.setSenha(_passwordEncoder.encode(redefinicaoSenhaDto.getSenhaNova()));
        _usuarioRepository.save(usuario);
    }
}
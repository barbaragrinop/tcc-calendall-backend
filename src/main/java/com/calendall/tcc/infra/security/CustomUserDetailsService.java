package com.calendall.tcc.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = _usuarioRepository.findByEmail(username);

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}
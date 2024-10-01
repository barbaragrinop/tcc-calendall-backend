package com.calendall.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendall.tcc.model.Sala;
import com.calendall.tcc.model.SalaUsuario;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.enums.Funcao;
import com.calendall.tcc.repository.SalaUsuarioRepository;

@Service
public class SalaUsuarioService {
    
    @Autowired
    private SalaUsuarioRepository salaUsuarioRepository;

    public SalaUsuarioService() {
    }

    public SalaUsuario atribuirFuncao(Usuario usuario, Sala sala){

        SalaUsuario salaUsuario = new SalaUsuario();
        salaUsuario.setUsuario(usuario);
        salaUsuario.setSala(sala);
        salaUsuario.setFuncaoUsuario(Funcao.REPRESENTANTE);
        salaUsuarioRepository.save(salaUsuario);

        return salaUsuario;
    }

    public List<SalaUsuario> findAll() {
        return salaUsuarioRepository.findAll();
    }
}

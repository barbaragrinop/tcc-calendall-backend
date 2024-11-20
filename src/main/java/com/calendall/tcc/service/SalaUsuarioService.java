package com.calendall.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Usuario obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal(); 
        return usuarioLogado;
    }

    public Boolean verificarRepresentante(Long id_sala){
        Usuario usuarioLogado = obterUsuarioLogado();
        Long id_usuario = usuarioLogado.getId_usuario();

        SalaUsuario salaUsuario = salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala);

        return salaUsuario.getFuncaoUsuario() == Funcao.REPRESENTANTE || 
                salaUsuario.getFuncaoUsuario() == Funcao.VICE_REPRESENTANTE;
    }

    public Boolean verificarRepresentante(Long id_sala, Long id_usuario){
        SalaUsuario salaUsuario = salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala);

        return salaUsuario.getFuncaoUsuario() == Funcao.REPRESENTANTE || 
                salaUsuario.getFuncaoUsuario() == Funcao.VICE_REPRESENTANTE;
    }

    public Boolean verificarPresencaSala(Long id_sala, Long id_usuario){
        SalaUsuario salaUsuario = salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala);
        if(salaUsuario != null){
            return true;
        }
        return false;
    }

    public Boolean verificarExistenciaVice(Long id_sala){
        List<SalaUsuario> salaUsuarios = salaUsuarioRepository.findBySala(id_sala);
        for(SalaUsuario salaUsuario : salaUsuarios){
            if(salaUsuario.getFuncaoUsuario() == Funcao.VICE_REPRESENTANTE){
                return true;
            }
        }
        return false;
    }

    public SalaUsuario atribuirFuncao(Usuario usuario, Sala sala, Funcao funcao){

        SalaUsuario salaUsuario = new SalaUsuario();
        salaUsuario.setUsuario(usuario);
        salaUsuario.setSala(sala);
        salaUsuario.setFuncaoUsuario(funcao);
        if(funcao == Funcao.ALUNO){
            salaUsuario.setIsAdmin(false);
        }
        else{
            salaUsuario.setIsAdmin(true);
        }
        salaUsuarioRepository.save(salaUsuario);

        return salaUsuario;
    }

    public List<SalaUsuario> findAll() {
        return salaUsuarioRepository.findAll();
    }

    public List<SalaUsuario> findBySala(Long id_sala) {
        return salaUsuarioRepository.findBySala(id_sala);
    }

    public List<SalaUsuario> findByUsuario(Long id_usuario) {
        return salaUsuarioRepository.findByUsuario(id_usuario);
    }

    public SalaUsuario findByUsuarioAndSala(Long id_usuario, Long id_sala) {
        return salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala);
    }

    public SalaUsuario addVice(Long id_sala, Long id_usuario){
        if(verificarRepresentante(id_sala) &
             !verificarExistenciaVice(id_sala) & 
             !verificarRepresentante(id_sala, id_usuario))
        {
            SalaUsuario salaUsuario = salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala);
            salaUsuario.setFuncaoUsuario(Funcao.VICE_REPRESENTANTE);
            salaUsuario.setIsAdmin(true);
            return salaUsuarioRepository.save(salaUsuario);
        }
        return null;
    }

    public boolean deleteUsuarioFromSala(Long id_usuario, Long id_sala){
       
        if(verificarRepresentante(id_sala)){
            if(salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala) != null){
                SalaUsuario salaUsuario = salaUsuarioRepository.findByUsuarioAndSala(id_usuario, id_sala);
                salaUsuarioRepository.delete(salaUsuario);
                return true;
            }
        }        
        return false;
    }

    public boolean deleteAllUsuariosFromSala(Long id_sala) {

        List<SalaUsuario> salaUsuarios = salaUsuarioRepository.findBySala(id_sala);
        
        if (salaUsuarios != null && !salaUsuarios.isEmpty()) {
            salaUsuarioRepository.deleteAll(salaUsuarios);
            return true;
        }

        return false;
    }
}

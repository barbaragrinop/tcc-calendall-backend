package com.calendall.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.Sala;
import com.calendall.tcc.model.SalaUsuario;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.enums.Funcao;
import com.calendall.tcc.repository.EventoSalaRepository;
import com.calendall.tcc.repository.SalaRepository;
import com.calendall.tcc.repository.SalaUsuarioRepository;
import com.calendall.tcc.repository.UsuarioRepository;

@Service
public class SalaService implements IService<Sala> {

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private SalaUsuarioRepository salaUsuarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EventoSalaRepository eventoSalaRepository;

    @Autowired
    private SalaUsuarioService salaUsuarioService;

    public SalaService(){
    }


    @Override
    public Sala create(Sala sala) {
        Usuario usuarioCriador = salaUsuarioService.obterUsuarioLogado();

        salaRepository.save(sala);
        salaUsuarioService.atribuirFuncao(usuarioCriador, sala, Funcao.REPRESENTANTE);

        return sala;
    }

    public SalaUsuario adicionaUsario(Long id_sala, Long id_usuario){
        Sala sala = salaRepository.findById(id_sala).orElse(null);
        Usuario usuarioToAdd = usuarioRepository.findById(id_usuario).orElse(null);

        if(sala != null & usuarioToAdd != null & 
            !salaUsuarioService.verificarPresencaSala(id_sala, id_usuario) &
             salaUsuarioService.verificarRepresentante(id_sala))
        {
            SalaUsuario salaUsuario = salaUsuarioService.atribuirFuncao(usuarioToAdd, sala, Funcao.ALUNO);
            salaUsuarioRepository.save(salaUsuario);
            sala.setQt_membros(sala.getQt_membros() + 1);
            salaRepository.save(sala);
            return salaUsuario;
        }

        return null;
    }

    @Override
    public Sala findById(Long id) {
        Optional<Sala> sala = salaRepository.findById(id);
        return sala.orElse(null);
    }

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public List<EventoSala> findAllEventos(Long id_sala) {
        Sala sala = salaRepository.findById(id_sala).orElse(null);
        if (sala != null){
            return eventoSalaRepository.findBySala(sala);
        }
        return null;
    }

    @Override
    public boolean update(Sala sala) {
        if(salaRepository.existsById(sala.getId_sala())){
            salaRepository.save(sala);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
       if (salaRepository.existsById(id)){
            if(salaUsuarioService.verificarRepresentante(id)){
                salaUsuarioService.deleteAllUsuariosFromSala(id);
                salaRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public boolean deleteEventoSala(Long id_eventoSala) {
        if (eventoSalaRepository.existsById(id_eventoSala)){
            EventoSala eventoSala = eventoSalaRepository.findById(id_eventoSala).get();
            Sala sala = eventoSala.getSala();
            if(salaUsuarioService.verificarRepresentante(sala.getId_sala())){
                eventoSalaRepository.deleteById(id_eventoSala);
                return true;
            } 
        }
        return false;
    }

}

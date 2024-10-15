package com.calendall.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.calendall.tcc.model.Evento;
import com.calendall.tcc.model.EventoPessoal;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dto.EventoPessoalDTO;
import com.calendall.tcc.repository.EventoPessoalRepository;
import com.calendall.tcc.repository.EventoRepository;
import com.calendall.tcc.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class EventoPessoalService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoPessoalRepository eventoPessoalRepository;

    @Autowired
    private SalaService salaService;

    public EventoPessoal CriarEventoPessoal(@PathVariable Long id_evento,
            @RequestBody EventoPessoalDTO eventoPessoalDTO) throws Exception {

        Optional<Evento> eventoExistenteById = eventoRepository.findById(id_evento);

        if (eventoExistenteById.isEmpty()) {
            throw new Exception("Evento não encontrado");
        }

        Usuario usuarioLogado = salaService.obterUsuarioLogado();

        if (usuarioLogado == null) {
            throw new Exception("Usuário não encontrado");
        }

        Evento eventoExistente = eventoExistenteById.get();

        EventoPessoal eventoPessoal = new EventoPessoal();
        eventoPessoal.setEvento(eventoExistente);
        eventoPessoal.setUsuario(usuarioLogado);

        eventoPessoal.setTipoPrioridade(eventoPessoalDTO.getTipoPrioridade());
        eventoPessoal.setTipoNotificacao(eventoPessoalDTO.getTipoNotificacao());

        return eventoPessoalRepository.save(eventoPessoal);
    }
}
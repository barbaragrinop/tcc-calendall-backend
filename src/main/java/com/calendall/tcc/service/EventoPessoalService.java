package com.calendall.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.calendall.tcc.model.Evento;
import com.calendall.tcc.model.EventoPessoal;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dto.EventoPessoalDTO;
import com.calendall.tcc.model.dto.EventoPessoalEdicaoDTO;
import com.calendall.tcc.model.dto.EventoPessoalNovoDTO;
import com.calendall.tcc.repository.EventoPessoalRepository;
import com.calendall.tcc.repository.EventoRepository;

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

    public EventoPessoal CriarEventoPessoalIndividual(EventoPessoalNovoDTO eventoPessoalNovoDTO) throws Exception {

        Usuario usuarioLogado = salaService.obterUsuarioLogado();

        if (usuarioLogado == null) {
            throw new Exception("Usuário não encontrado");
        }

        EventoPessoal novoEventoPessoal = new EventoPessoal();

        novoEventoPessoal.setTipoPrioridade(eventoPessoalNovoDTO.getTipoPrioridade());
        novoEventoPessoal.setTipoNotificacao(eventoPessoalNovoDTO.getTipoNotificacao());
        novoEventoPessoal.setUsuario(usuarioLogado);

        Evento novoEvento = new Evento();

        novoEvento.setTitulo(eventoPessoalNovoDTO.getTitulo());
        novoEvento.setDescricao(eventoPessoalNovoDTO.getDescricao());
        novoEvento.setDt_evento(eventoPessoalNovoDTO.getDt_evento());
        novoEvento.setIc_completa(false);

        novoEvento = eventoRepository.save(novoEvento);
        novoEventoPessoal.setEvento(novoEvento);

        return eventoPessoalRepository.save(novoEventoPessoal);
    }

    public List<EventoPessoal> BuscarEventosPessoaisPorUsuario(Optional<Usuario> usuario) {
        return eventoPessoalRepository.findByUsuario(usuario);
    }

    public List<EventoPessoal> BuscarEventosPessoaisNaDataAtual(Long id_usuario){
        return eventoPessoalRepository.findEventosPessoaisNaDataAtualPorUsuario(id_usuario);
    }

    public List<EventoPessoal> BuscarEventosPessoaisAposDataAtual(Long id_usuario){
        return eventoPessoalRepository.findEventosPessoaisAposDataAtualPorUsuario(id_usuario);
    }

    public void DeletarEventoPessoal(Long id_evento_pessoal) throws Exception {
        Optional<EventoPessoal> eventoPessoal = eventoPessoalRepository.findById(id_evento_pessoal);

        if (eventoPessoal.isEmpty()) {
            throw new Exception("Evento pessoal não encontrado.");
        }
        eventoPessoalRepository.deleteById(id_evento_pessoal);
    }

    public EventoPessoal AtualizarEventoPessoal(Long id_evento_pessoal, EventoPessoalEdicaoDTO eventoPessoalEdicaoDTO)
            throws Exception {

        EventoPessoal eventoPessoal = eventoPessoalRepository.findById(id_evento_pessoal)
                .orElseThrow(() -> new Exception("Evento pessoal não encontrado."));

        eventoPessoal.setTipoPrioridade(eventoPessoalEdicaoDTO.getTipoPrioridade());
        eventoPessoal.setTipoNotificacao(eventoPessoalEdicaoDTO.getTipoNotificacao());

        return eventoPessoalRepository.save(eventoPessoal);
    }
}
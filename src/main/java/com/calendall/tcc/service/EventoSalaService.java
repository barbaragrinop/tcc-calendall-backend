package com.calendall.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.Sala;
import com.calendall.tcc.repository.EventoSalaRepository;
import com.calendall.tcc.repository.SalaRepository;

@Service
public class EventoSalaService {

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private EventoSalaRepository eventoSalaRepository;

    @Autowired
    private SalaService salaService;

    public EventoSalaService(){
    }


    public EventoSala createEventoSala(Long id_sala, EventoSala eventoSala) {
        Sala sala = salaRepository.findById(id_sala).orElse(null);
        
        if(salaService.verificarRepresentante(id_sala) ){
            if (sala != null){
                eventoSala.setSala(sala);
                eventoSalaRepository.save(eventoSala);
                return eventoSala;
            }
        }        
        return null;
    }


    public List<EventoSala> findAllEventos(Long id_sala) {
        Sala sala = salaRepository.findById(id_sala).orElse(null);
        if (sala != null){
            return eventoSalaRepository.findBySala(sala);
        }
        return null;
    }

     public List<EventoSala> BuscarEventosPublicosNaDataAtual(Long id_sala){
        return eventoSalaRepository.findEventosNaDataAtualPorSala(id_sala);
    }

    public List<EventoSala> BuscarEventosPublicosAposDataAtual(Long id_sala){
        return eventoSalaRepository.findEventosAposDataAtualPorSala(id_sala);
    }

    public boolean deleteEventoSala(Long id_eventoSala) {
        if (eventoSalaRepository.existsById(id_eventoSala)){
            EventoSala eventoSala = eventoSalaRepository.findById(id_eventoSala).get();
            Sala sala = eventoSala.getSala();
            if(salaService.verificarRepresentante(sala.getId_sala())){
                eventoSalaRepository.deleteById(id_eventoSala);
                return true;
            } 
        }
        return false;
    }

    public EventoSala completeEventoSala(Long id_eventoSala){
        
        if(eventoSalaRepository.existsById(id_eventoSala)){
            EventoSala eventoSala = eventoSalaRepository.findById(id_eventoSala).get();
            Sala sala = eventoSala.getSala();
            if(salaService.verificarRepresentante(sala.getId_sala())){
                eventoSala.setIc_completa(true);
                return eventoSalaRepository.save(eventoSala);
            }
        }
        return null;
    }
}

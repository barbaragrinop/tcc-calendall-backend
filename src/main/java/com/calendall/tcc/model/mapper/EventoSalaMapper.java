package com.calendall.tcc.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.Sala;
import com.calendall.tcc.model.dto.EventoSalaDTO;
import com.calendall.tcc.repository.SalaRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EventoSalaMapper {

    @Autowired
    private SalaRepository salaRepository;
    
    public EventoSala toEntity(EventoSalaDTO dto, Long id_sala) {
        EventoSala eventoSala = new EventoSala();

        eventoSala.setId_evento(null);

        Sala sala = salaRepository.findById(id_sala).orElse(null);
        eventoSala.setSala(sala);

        eventoSala.setTitulo(dto.getTitulo());
        eventoSala.setDescricao(dto.getDescricao());
        eventoSala.setDt_evento(dto.getDt_evento());
        eventoSala.setIc_completa(false);

        return eventoSala;
    }

    public EventoSalaDTO toDTO(EventoSala eventoSala) {
        EventoSalaDTO eventoSalaDTO = new EventoSalaDTO();

        eventoSalaDTO.setTitulo(eventoSala.getTitulo());
        eventoSalaDTO.setDescricao(eventoSala.getDescricao());
        eventoSalaDTO.setDt_evento(eventoSala.getDt_evento());
        //eventoSalaDTO.setId_sala(eventoSala.getSala().getId_sala());

        return eventoSalaDTO;
    }

    public List<EventoSala> toEntity(List<EventoSalaDTO> eventoSalaDTOs, Long id_sala) {
        return eventoSalaDTOs.stream()
                .map(dto -> toEntity(dto, id_sala))
                .collect(Collectors.toList());
    }


    public List<EventoSalaDTO> toDTO(List<EventoSala> eventoSalas) {
        return eventoSalas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}

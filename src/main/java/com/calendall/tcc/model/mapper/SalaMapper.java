package com.calendall.tcc.model.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.calendall.tcc.model.Sala;
import com.calendall.tcc.model.dto.SalaDTO;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class SalaMapper {
    
    public Sala toEntity(SalaDTO dto) {
        Sala sala = new Sala();
        sala.setId_sala(null);
        sala.setNome(dto.getNome());
        sala.setDescricao(dto.getDescricao());
        sala.setDt_criacao(LocalDateTime.now());

        return sala;
    }

    public SalaDTO toDTO(Sala sala) {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNome(sala.getNome());
        salaDTO.setDescricao(sala.getDescricao());

        return salaDTO;
    }

    public List<Sala> toEntity(List<SalaDTO> salaDTOs) {
        return salaDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<SalaDTO> toDTO(List<Sala> salas) {
        return salas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}

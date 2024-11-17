package com.calendall.tcc.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter

public class EventoSalaDTO {

    @NotNull(message = "Título do evento é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data do evento é obrigatória")
    private LocalDateTime dt_evento;

}

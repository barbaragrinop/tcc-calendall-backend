package com.calendall.tcc.model.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter

public class EventoSalaDTO {

    @NotNull(message = "Título do evento é obrigatório")
    private String titulo;

    //opcional
    private String descricao;

    @NotNull(message = "Data do evento é obrigatória")
    private LocalDate dt_evento;

    // @NotNull(message = "ID da sala é obrigatório")
    // private Long id_sala;

}

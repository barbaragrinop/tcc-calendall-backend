package com.calendall.tcc.model.dto;

import java.time.LocalDateTime;

import com.calendall.tcc.model.enums.TipoPrioridade;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoPessoalNovoDTO {

    @NotNull(message = "Prioridade do evento pessoal é obrigatório")
    private TipoPrioridade tipoPrioridade;

    @NotNull(message = "Tipo de notificacao do evento pessoal é obrigatório")
    private String tipoNotificacao;

    @NotNull(message = "Título do evento é obrigatório.")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data do evento é obrigatória")
    private LocalDateTime dt_evento;
}
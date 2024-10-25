package com.calendall.tcc.model.dto;

import com.calendall.tcc.model.enums.TipoPrioridade;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoPessoalEdicaoDTO {
    @NotNull(message = "Prioridade do evento pessoal é obrigatório")
    private TipoPrioridade tipoPrioridade;

    @NotNull(message = "Tipo de notificacao do evento pessoal é obrigatório")
    private String tipoNotificacao;
}
package com.calendall.tcc.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter

public class SalaDTO {

    @NotNull(message = "Nome da sala é obrigatório")
    private String nome;
    private String descricao;

}

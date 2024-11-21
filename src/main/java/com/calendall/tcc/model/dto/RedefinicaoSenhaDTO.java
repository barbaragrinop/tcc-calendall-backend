package com.calendall.tcc.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RedefinicaoSenhaDTO {
    @NotNull(message = "Senha atual é obrigatória")
    private String senhaAtual;

    @NotNull(message = "Nova senha é obrigatório")
    private String senhaNova;

    @NotNull(message = "Confirmação de senha é obrigatório")
    private String senhaConfirmada;
}
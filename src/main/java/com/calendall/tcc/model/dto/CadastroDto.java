package com.calendall.tcc.model.dto;

import java.time.LocalDate;

public record CadastroDto(String nome, String email, String senha, String confirmacaoSenha, LocalDate dataNascimento) {
    
}

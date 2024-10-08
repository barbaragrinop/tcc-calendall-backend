package com.calendall.tcc.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_usuario")

@Setter
@Getter

public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(name = "nm_usuario", nullable = false)
    @NotNull(message = "Nome do usuário é obrigatório.")
    private String nome;

    @Column(name = "ds_email", nullable = false)
    @NotNull(message = "Email do usuário é obrigatório.")
    private String email;

    @Column(name = "ds_senha", nullable = false)
    @NotNull(message = "Senha do usuáio é obrigatória.")
    private String senha;

    @Column(name = "dt_nascimento", nullable = false, length = 8)
    @NotNull(message = "Data de nascimento do usuário é obrigatória.")
    @Past(message = "Data de nascimento deve estar no passado")
    private LocalDate dt_nascimento;

    public Usuario(){ }
}

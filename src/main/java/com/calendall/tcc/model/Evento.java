package com.calendall.tcc.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_evento")

@Setter
@Getter

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull(message = "Nome do evento é obrigatório.")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dt_evento", nullable = false)
    @NotNull(message = "Data do evento é obrigatória")
    private LocalDateTime dt_evento;

    @Column(name = "ic_completa", nullable = false)
    @NotNull(message = "Indicador de evento completo é obrigatório")
    private Boolean ic_completa;

    public Evento(){ }
}

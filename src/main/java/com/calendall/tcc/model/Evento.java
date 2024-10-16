package com.calendall.tcc.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_evento")

@Setter
@Getter

@Inheritance(strategy = InheritanceType.JOINED)
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;

    @Column(name = "nm_titulo", nullable = false)
    @NotNull(message = "Título do evento é obrigatório.")
    private String titulo;

    @Column(name = "ds_evento")
    private String descricao;

    @Column(name = "dt_evento", nullable = false)
    @NotNull(message = "Data do evento é obrigatória")
    private LocalDate dt_evento;

    @Column(name = "ic_completa", nullable = false)
    @NotNull(message = "Indicador de evento completo é obrigatório")
    private Boolean ic_completa;

    public Evento(){ }
}

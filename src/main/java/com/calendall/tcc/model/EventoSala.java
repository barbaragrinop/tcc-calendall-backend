package com.calendall.tcc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_evento_sala")

@Setter
@Getter

@PrimaryKeyJoinColumn(name = "evento_id")
public class EventoSala extends Evento {

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    @NotNull(message = "ID da Sala responsável pelo evento é obrigatório.")
    private Sala id_sala;

}


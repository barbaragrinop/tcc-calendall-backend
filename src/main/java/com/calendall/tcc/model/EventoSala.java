package com.calendall.tcc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_evento_sala")

@Setter
@Getter

@PrimaryKeyJoinColumn(name = "id_evento")
public class EventoSala extends Evento {

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private Sala sala;

}


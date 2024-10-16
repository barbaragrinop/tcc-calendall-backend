package com.calendall.tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

import com.calendall.tcc.model.enums.TipoPrioridade;

@Entity
@Table(name = "tbl_evento_pessoal")

@Setter
@Getter

public class EventoPessoal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_pessoal")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    @NotNull(message = "Evento associado é obrigatório.")
    private Evento evento;

    @Column(name = "nm_prioridade", nullable = false)
    @NotNull(message = "Prioridade do Evento Pessoal é obrigatória.")
    private TipoPrioridade tipoPrioridade;

    @Column(name = "nm_notificacao")
    private String tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull(message = "Usuário responsável pelo evento é obrigatório.")
    private Usuario usuario;
}
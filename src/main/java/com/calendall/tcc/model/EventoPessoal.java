package com.calendall.tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.calendall.tcc.model.enums.TipoPrioridade;

@Entity
@Table(name = "tb_evento_pessoal")

@Setter
@Getter

@PrimaryKeyJoinColumn(name = "evento_id")
public class EventoPessoal extends Evento {

    @Column(name = "tipoPrioridade", nullable = false)
    @NotNull(message = "Prioridade do Evento Pessoal é obrigatória.")
    private TipoPrioridade tipoPrioridade;

    @Column(name = "tipoNotificacao")
    private String tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull(message = "ID do Usuário responsável pelo evento é obrigratório.")
    private Usuario id_usuario;

}


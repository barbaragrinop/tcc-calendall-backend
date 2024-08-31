package com.calendall.tcc.model;

import com.calendall.tcc.model.enums.Funcao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_SalaUsuario")

@Setter
@Getter

public class SalaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_SalaUsuario;

    @ManyToOne
    @JoinColumn(name = "id_Sala", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "funcaoUsuario", nullable = false)
    private Funcao funcaoUsuario;
}


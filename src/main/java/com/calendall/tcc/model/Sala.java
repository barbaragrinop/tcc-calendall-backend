package com.calendall.tcc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_sala")

@Setter
@Getter

public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    @Column(name = "nm_sala", nullable = false)
    @NotNull(message = "Nome da sala é obrigatório.")
    private String nome;

    @Column(name = "ds_sala")
    private String descricao;

    @Column(name = "dt_criacao", nullable = false)
    @PastOrPresent(message = "Data de criação deve estar no passado ou presente")
    @CreationTimestamp
    private LocalDateTime dt_criacao;

    public Sala(){ }
}

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
@Table(name = "tb_sala")

@Setter
@Getter

public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull(message = "Nome da sala é obrigatório.")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dt_criacao", nullable = false)
    @PastOrPresent(message = "Data de criação deve estar no passado ou presente")
    @CreationTimestamp
    private LocalDateTime dt_criacao;

    @ManyToOne
    @JoinColumn(name = "id_representante1", nullable = false)
    @NotNull(message = "ID do Usuário representante da sala é obrigratório.")
    private Usuario id_representante1;

    @ManyToOne
    @JoinColumn(name = "id_representante2")
    private Usuario id_representante2;

    public Sala(){ }
}

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
@Table(name = "tb_publicacao")

@Setter
@Getter

public class Publicacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conteudo", nullable = false)
    @NotNull(message = "Conteúdo da publicação é obrigatório.")
    private String conteudo;

    @Column(name = "dt_publicacao", nullable = false)
    @PastOrPresent(message = "Data de publicação deve estar no passado ou presente")
    @CreationTimestamp
    private LocalDateTime dt_publicacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull(message = "ID do Usuário criador da publicação é obrigratório.")
    private Usuario id_usuario;


    public Publicacao(){ }
}

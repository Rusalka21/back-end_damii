package com.back.pid_grupo01.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invitacion")
public class Invitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int idInvitacion;

    @ManyToOne
    @JoinColumn(name = "id_remitente", nullable = false)
    private Usuario remitente;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "id_receptor", nullable = false)
    private Usuario receptor;
    private Boolean respondida;
    private Boolean aceptada;
}

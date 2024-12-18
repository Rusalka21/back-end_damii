package com.back.pid_grupo01.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;

    private String titulo;
    private String descripcion;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    private Boolean cumplida;

    private TareaPrioridad prioridad;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @Builder.Default
    private Boolean activo = true; // Nuevo campo para delete lógico
}

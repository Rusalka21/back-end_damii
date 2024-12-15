package com.back.pid_grupo01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoResponse {

    private Integer idProyecto;
    private String nombre;
    private String color;

    // Lista de invitados (representados como DTOs)
    private List<UsuarioResponse> invitados;
}

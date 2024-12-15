package com.back.pid_grupo01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String username1;
}

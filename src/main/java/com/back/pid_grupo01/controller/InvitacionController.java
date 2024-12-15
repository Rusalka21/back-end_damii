package com.back.pid_grupo01.controller;

import com.back.pid_grupo01.model.Invitacion;
import com.back.pid_grupo01.model.Proyecto;
import com.back.pid_grupo01.model.ProyectoResponse;
import com.back.pid_grupo01.service.InvitacionService;
import com.back.pid_grupo01.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitaciones")
@RequiredArgsConstructor
public class InvitacionController {

    private final InvitacionService invitacionService;

    // Crear una nueva invitación
    @PostMapping
    public ResponseEntity<Invitacion> createInvitacion(@RequestBody Invitacion invitacion) {
        Invitacion nuevaInvitacion = invitacionService.createInvitacion(invitacion);
        return new ResponseEntity<>(nuevaInvitacion, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Invitacion>> getUnansweredInvitacionesByUserId(@PathVariable Integer idUsuario){
        List<Invitacion> invitaciones = invitacionService.getUnansweredInvitacionesByUserId(idUsuario);

        return ResponseEntity.ok(invitaciones);
    }
}

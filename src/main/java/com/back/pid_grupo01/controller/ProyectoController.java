package com.back.pid_grupo01.controller;

import com.back.pid_grupo01.model.Proyecto;
import com.back.pid_grupo01.model.Usuario;
import com.back.pid_grupo01.model.ProyectoResponse;
import com.back.pid_grupo01.model.UsuarioResponse;
import com.back.pid_grupo01.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    // Crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<Proyecto> createProyecto(@RequestBody Proyecto proyecto) {
        Proyecto nuevoProyecto = proyectoService.createProyecto(proyecto);
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }

    // Obtener un proyecto por su ID
    @GetMapping("/{idProyecto}")
    public ResponseEntity<ProyectoResponse> getProyectoById(@PathVariable Integer idProyecto) {
        Proyecto proyecto = proyectoService.getProyectoById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));


        // Crear y devolver el DTO ProyectoResponse
        ProyectoResponse response = new ProyectoResponse(
                proyecto.getIdProyecto(),
                proyecto.getNombre(),
                proyecto.getColor(),
                proyecto.getUsuario()
        );

        return ResponseEntity.ok(response);
    }

    // Obtener todos los proyectos de un usuario por su ID
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProyectoResponse>> getProyectosByUsuarioId(@PathVariable Integer idUsuario) {
        List<Proyecto> proyectos = proyectoService.getProyectosByUsuarioId(idUsuario);

        // Mapear cada proyecto a su DTO ProyectoResponse
        List<ProyectoResponse> response = proyectos.stream().map(proyecto -> {

            return new ProyectoResponse(
                    proyecto.getIdProyecto(),
                    proyecto.getNombre(),
                    proyecto.getColor(),
                    proyecto.getUsuario()
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Actualizar un proyecto
    @PutMapping("/{idProyecto}")
    public ResponseEntity<Proyecto> updateProyecto(@PathVariable Integer idProyecto, @RequestBody Proyecto proyecto) {
        proyecto.setIdProyecto(idProyecto);
        Proyecto proyectoActualizado = proyectoService.updateProyecto(proyecto);
        return ResponseEntity.ok(proyectoActualizado);
    }

    // Eliminar un proyecto por su ID
    @DeleteMapping("/{idProyecto}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer idProyecto) {
        proyectoService.deleteProyecto(idProyecto);
        return ResponseEntity.noContent().build();
    }

}

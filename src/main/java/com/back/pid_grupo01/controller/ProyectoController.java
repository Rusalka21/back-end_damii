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

        // Mapear la lista de invitados al DTO UsuarioResponse
        List<UsuarioResponse> invitados = proyecto.getInvitados().stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getEmail(),
                        usuario.getUsername(),
                        usuario.getUsername1()))
                .collect(Collectors.toList());

        // Crear y devolver el DTO ProyectoResponse
        ProyectoResponse response = new ProyectoResponse(
                proyecto.getIdProyecto(),
                proyecto.getNombre(),
                proyecto.getColor(),
                invitados
        );

        return ResponseEntity.ok(response);
    }

    // Obtener todos los proyectos de un usuario por su ID
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProyectoResponse>> getProyectosByUsuarioId(@PathVariable Integer idUsuario) {
        List<Proyecto> proyectos = proyectoService.getProyectosByUsuarioId(idUsuario);

        // Mapear cada proyecto a su DTO ProyectoResponse
        List<ProyectoResponse> response = proyectos.stream().map(proyecto -> {
            List<UsuarioResponse> invitados = proyecto.getInvitados().stream()
                    .map(usuario -> new UsuarioResponse(
                            usuario.getId(),
                            usuario.getNombre(),
                            usuario.getApellido(),
                            usuario.getEmail(),
                            usuario.getUsername(),
                            usuario.getUsername1()))
                    .collect(Collectors.toList());

            return new ProyectoResponse(
                    proyecto.getIdProyecto(),
                    proyecto.getNombre(),
                    proyecto.getColor(),
                    invitados
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    //Obtener proyectos por id de Invitado
    @GetMapping("/invitado/{id}")
    public ResponseEntity<List<Proyecto>> findProjectsByInvitadoId(@PathVariable Integer id){
        List<Proyecto> proyectos = proyectoService.findProjectsByInvitadoId(id);
        if (proyectos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proyectos);
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

    // Agregar un invitado al proyecto
    @PatchMapping("/{idProyecto}/invitados/{idUsuario}")
    public ResponseEntity<Proyecto> agregarInvitado(@PathVariable Integer idProyecto, @PathVariable Integer idUsuario) {
        Proyecto proyectoActualizado = proyectoService.agregarInvitado(idProyecto, idUsuario);
        return ResponseEntity.ok(proyectoActualizado);
    }

    // Eliminar un invitado del proyecto
    @DeleteMapping("/{idProyecto}/invitados/{idUsuario}")
    public ResponseEntity<Proyecto> eliminarInvitado(@PathVariable Integer idProyecto, @PathVariable Integer idUsuario) {
        Proyecto proyectoActualizado = proyectoService.eliminarInvitado(idProyecto, idUsuario);
        return ResponseEntity.ok(proyectoActualizado);
    }

    // Obtener todos los invitados de un proyecto
    @GetMapping("/{idProyecto}/invitados")
    public ResponseEntity<List<UsuarioResponse>> obtenerInvitados(@PathVariable Integer idProyecto) {
        List<Usuario> invitados = proyectoService.obtenerInvitados(idProyecto);

        // Mapear la lista de invitados al DTO UsuarioResponse
        List<UsuarioResponse> response = invitados.stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getEmail(),
                        usuario.getUsername(),
                        usuario.getUsername1()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}

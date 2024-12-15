package com.back.pid_grupo01.controller;

import com.back.pid_grupo01.model.Comentario;
import com.back.pid_grupo01.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Crear o actualizar un comentario
    @PostMapping
    public ResponseEntity<Comentario> saveComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioService.saveComentario(comentario);
        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED);
    }

    // Obtener un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        return comentario.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener comentarios por ID de usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Comentario>> getComentariosByUsuarioId(@PathVariable Integer idUsuario) {
        List<Comentario> comentarios = comentarioService.getComentariosByUsuarioId(idUsuario);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    // Obtener comentarios por ID de tarea
    @GetMapping("/tarea/{idTarea}")
    public ResponseEntity<List<Comentario>> getComentariosByTareaId(@PathVariable Integer idTarea) {
        List<Comentario> comentarios = comentarioService.getComentariosByTareaId(idTarea);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    // Obtener comentarios de un usuario en una tarea específica
    @GetMapping("/usuario/{idUsuario}/tarea/{idTarea}")
    public ResponseEntity<List<Comentario>> getComentariosByUsuarioIdAndTareaId(@PathVariable Integer idUsuario, @PathVariable Integer idTarea) {
        List<Comentario> comentarios = comentarioService.getComentariosByUsuarioIdAndTareaId(idUsuario, idTarea);
        return comentarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(comentarios);
    }

    // Eliminar comentario (delete lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentarioById(@PathVariable Integer id) {
        comentarioService.deleteComentarioById(id);
        return ResponseEntity.noContent().build();
    }

    // Restaurar comentario
    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<Comentario> restaurarComentario(@PathVariable Integer id) {
        Comentario comentarioRestaurado = comentarioService.restaurarComentario(id);
        return ResponseEntity.ok(comentarioRestaurado);
    }
}

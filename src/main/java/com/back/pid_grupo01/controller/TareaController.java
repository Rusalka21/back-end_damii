package com.back.pid_grupo01.controller;

import com.back.pid_grupo01.model.Tarea;
import com.back.pid_grupo01.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.createTarea(tarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    // Obtener todas las tareas de un proyecto
    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<List<Tarea>> getTareasByProyectoId(@PathVariable Integer idProyecto) {
        List<Tarea> tareas = tareaService.getTareasByProyectoId(idProyecto);
        return tareas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tareas);
    }

    // Obtener tarea por ID
    @GetMapping("/{idTarea}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Integer idTarea) {
        Optional<Tarea> tarea = tareaService.getTareaById(idTarea);
        return tarea.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Obtener tarea por titulo dentro de un proyecto específico
    @GetMapping("/proyecto/{idProyecto}/titulo/{titulo}")
    public ResponseEntity<Tarea> getTareaByTituloAndProyecto(@PathVariable Integer idProyecto, @PathVariable String titulo) {
        Optional<Tarea> tarea = tareaService.getTareaByTituloAndProyecto(titulo, idProyecto);
        return tarea.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Actualizar tarea
    @PutMapping("/{idTarea}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Integer idTarea, @RequestBody Tarea tarea) {
        tarea.setIdTarea(idTarea);
        Tarea tareaActualizada = tareaService.updateTarea(tarea);
        return ResponseEntity.ok(tareaActualizada);
    }

    // Eliminar tarea
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Integer idTarea) {
        tareaService.deleteTarea(idTarea);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{idTarea}")
    public ResponseEntity<Tarea> actualizarEstadoTarea(@PathVariable Integer idTarea) {
        Tarea tareaActualizada = tareaService.actualizarEstadoTarea(idTarea);
        return tareaActualizada != null ? ResponseEntity.ok(tareaActualizada)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/{idTarea}/restaurar")
    public ResponseEntity<Tarea> restaurarTarea(@PathVariable Integer idTarea) {
        Tarea tareaRestaurada = tareaService.restaurarTarea(idTarea);
        return ResponseEntity.ok(tareaRestaurada);
    }

}

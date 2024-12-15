package com.back.pid_grupo01.service;

import com.back.pid_grupo01.model.Tarea;
import com.back.pid_grupo01.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;

    // Crear una nueva tarea
    public Tarea createTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Obtener todas las tareas activas de un proyecto
    public List<Tarea> getTareasByProyectoId(Integer idProyecto) {
        return tareaRepository.findByProyectoIdProyectoAndActivoTrue(idProyecto);
    }

    // Obtener todas las tareas activas de un usuario
    public List<Tarea> getTareasByUsuarioId(Integer idUsuario) {
        return tareaRepository.findByUsuarioIdAndActivoTrue(idUsuario);
    }

    // Obtener tarea activa por ID
    public Optional<Tarea> getTareaById(Integer idTarea) {
        return tareaRepository.findByIdTareaAndActivoTrue(idTarea);
    }

    // Obtener tarea activa por título dentro de un proyecto específico
    public Optional<Tarea> getTareaByTituloAndProyecto(String titulo, Integer idProyecto) {
        return tareaRepository.findByTituloAndProyectoIdProyectoAndActivoTrue(titulo, idProyecto);
    }

    // Actualizar tarea
    public Tarea updateTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Eliminar tarea (delete lógico)
    public void deleteTarea(Integer idTarea) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(idTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setActivo(false); // Marcar como inactiva
            tareaRepository.save(tarea);
        }
    }

    public Tarea actualizarEstadoTarea(Integer idTarea, Boolean cumplida) {
        Optional<Tarea> tareaOpt = tareaRepository.findByIdTareaAndActivoTrue(idTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCumplida(cumplida);
            return tareaRepository.save(tarea);
        }
        return null;
    }
    public Tarea restaurarTarea(Integer idTarea) {
        // Buscar la tarea por su ID
        Optional<Tarea> tareaOpt = tareaRepository.findById(idTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();

            // Cambiar el estado de activo a true
            tarea.setActivo(true);

            // Guardar los cambios en la base de datos
            return tareaRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }

}

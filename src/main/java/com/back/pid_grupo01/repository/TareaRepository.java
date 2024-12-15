package com.back.pid_grupo01.repository;

import com.back.pid_grupo01.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    // Obtener tareas activas por ID de proyecto
    List<Tarea> findByProyectoIdProyectoAndActivoTrue(Integer idProyecto);

    // Obtener tarea activa por ID
    Optional<Tarea> findByIdTareaAndActivoTrue(Integer idTarea);

    // Obtener tarea activa por título dentro de un proyecto específico
    Optional<Tarea> findByTituloAndProyectoIdProyectoAndActivoTrue(String titulo, Integer idProyecto);
}

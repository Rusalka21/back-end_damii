package com.back.pid_grupo01.repository;

import com.back.pid_grupo01.model.Proyecto;
import com.back.pid_grupo01.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    
    // Buscar un proyecto por su id
    Optional<Proyecto> findByIdProyecto(Integer idProyecto);
    
    // Buscar todos los proyectos de un usuario por su id de usuario
    List<Proyecto> findByUsuarioId(Integer idUsuario);
    
    // Buscar un proyecto por su nombre
    Optional<Proyecto> findByNombre(String nombre);

    // Obtener proyectos activas por ID de usuario
    List<Proyecto> findByUsuarioIdAndActivoTrue(Integer idUsuario);

}

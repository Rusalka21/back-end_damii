package com.back.pid_grupo01.repository;

import com.back.pid_grupo01.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    // Obtener comentarios activos por ID de tarea
    List<Comentario> findByTareaIdTareaAndActivoTrue(Integer idTarea);

    // Obtener comentarios activos por ID de usuario
    List<Comentario> findByUsuarioIdAndActivoTrue(Integer idUsuario);

    // Obtener comentario activo por ID
    Optional<Comentario> findByIdComentarioAndActivoTrue(Integer idComentario);

    // Obtener comentarios activos por ID de usuario y tarea
    List<Comentario> findByUsuarioIdAndTareaIdTareaAndActivoTrue(Integer idUsuario, Integer idTarea);

}

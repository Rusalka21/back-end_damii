package com.back.pid_grupo01.service;

import com.back.pid_grupo01.model.Comentario;
import com.back.pid_grupo01.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // Guardar un comentario
    public Comentario saveComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Obtener comentario activo por ID
    public Optional<Comentario> getComentarioById(Integer id) {
        return comentarioRepository.findByIdComentarioAndActivoTrue(id);
    }

    // Obtener comentarios activos por usuario
    public List<Comentario> getComentariosByUsuarioId(Integer idUsuario) {
        return comentarioRepository.findByUsuarioIdAndActivoTrue(idUsuario);
    }

    // Obtener comentarios activos por tarea
    public List<Comentario> getComentariosByTareaId(Integer idTarea) {
        return comentarioRepository.findByTareaIdTareaAndActivoTrue(idTarea);
    }

    // Obtener comentarios activos por usuario y tarea
    public List<Comentario> getComentariosByUsuarioIdAndTareaId(Integer idUsuario, Integer idTarea) {
        return comentarioRepository.findByUsuarioIdAndTareaIdTareaAndActivoTrue(idUsuario, idTarea);
    }

    // Eliminar comentario (delete lógico)
    public void deleteComentarioById(Integer id) {
        Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
        if (comentarioOpt.isPresent()) {
            Comentario comentario = comentarioOpt.get();
            comentario.setActivo(false);
            comentarioRepository.save(comentario);
        }
    }

    // Restaurar comentario eliminado lógicamente
    public Comentario restaurarComentario(Integer id) {
        Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
        if (comentarioOpt.isPresent()) {
            Comentario comentario = comentarioOpt.get();
            comentario.setActivo(true);
            return comentarioRepository.save(comentario);
        } else {
            throw new RuntimeException("Comentario no encontrado");
        }
    }
}

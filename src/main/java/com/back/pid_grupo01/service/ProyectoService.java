package com.back.pid_grupo01.service;

import com.back.pid_grupo01.model.Proyecto;
import com.back.pid_grupo01.model.Tarea;
import com.back.pid_grupo01.model.Usuario;
import com.back.pid_grupo01.repository.ProyectoRepository;
import com.back.pid_grupo01.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    // Crear un nuevo proyecto
    public Proyecto createProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Obtener un proyecto por su ID
    public Optional<Proyecto> getProyectoById(Integer idProyecto) {
        return proyectoRepository.findById(idProyecto);
    }

    // Obtener todos los proyectos de un usuario por su ID
    public List<Proyecto> getProyectosByUsuarioId(Integer idUsuario) {
        return proyectoRepository.findByUsuarioIdAndActivoTrue(idUsuario);
    }

    // Actualizar un proyecto
    public Proyecto updateProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Eliminar un proyecto por su ID (eliminación lógica)
    public void deleteProyecto(Integer idProyecto) {
        Optional<Proyecto> proyectoOpt = proyectoRepository.findById(idProyecto);
        if (proyectoOpt.isPresent()) {
            Proyecto proyecto = proyectoOpt.get();
            proyecto.setActivo(false); // Marcar como inactiva
            proyectoRepository.save(proyecto);
        }
    }

    public Proyecto restaurarProyecto(Integer idProyecto) {
        Optional<Proyecto> proyectoOpt = proyectoRepository.findById(idProyecto);
        if (proyectoOpt.isPresent()) {
            Proyecto proyecto = proyectoOpt.get();

            // Cambiar el estado de activo a true
            proyecto.setActivo(true);

            // Guardar los cambios en la base de datos
            return proyectoRepository.save(proyecto);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }


}

package com.back.pid_grupo01.service;

import com.back.pid_grupo01.model.Proyecto;
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
        return proyectoRepository.findByUsuarioId(idUsuario);
    }

    // Obtener todos los proyectos por Id de Invitados
    public List<Proyecto> findProjectsByInvitadoId(Integer userId) {
        return proyectoRepository.findByInvitados_Id(userId);
    }

    // Actualizar un proyecto
    public Proyecto updateProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Eliminar un proyecto por su ID (eliminación física)
    public void deleteProyecto(Integer idProyecto) {
        proyectoRepository.deleteById(idProyecto);
    }

    // Agregar un invitado al proyecto
    public Proyecto agregarInvitado(Integer idProyecto, Integer idUsuario) {
        Proyecto proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar si el usuario ya está en la lista de invitados
        if (proyecto.getInvitados().contains(usuario)) {
            throw new RuntimeException("El usuario ya está invitado a este proyecto");
        }

        // Agregar el usuario a la lista de invitados
        proyecto.getInvitados().add(usuario);
        return proyectoRepository.save(proyecto);
    }

    // Eliminar un invitado del proyecto
    public Proyecto eliminarInvitado(Integer idProyecto, Integer idUsuario) {
        Proyecto proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Remover el usuario de la lista de invitados
        proyecto.getInvitados().remove(usuario);
        return proyectoRepository.save(proyecto);
    }

    // Obtener todos los invitados de un proyecto
    public List<Usuario> obtenerInvitados(Integer idProyecto) {
        Proyecto proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        return proyecto.getInvitados();
    }
}

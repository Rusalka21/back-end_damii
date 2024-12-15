package com.back.pid_grupo01.service;

import com.back.pid_grupo01.model.Invitacion;
import com.back.pid_grupo01.model.Tarea;
import com.back.pid_grupo01.repository.InvitacionRepository;
import com.back.pid_grupo01.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitacionService {

    private final InvitacionRepository invitacionRepository;

    // Crear una nueva invitación
    public Invitacion createInvitacion(Invitacion invitacion) {

        Invitacion nuevaInvitacion = Invitacion.builder()
                .remitente(invitacion.getRemitente())
                .receptor(invitacion.getReceptor())
                .proyecto(invitacion.getProyecto())
                .respondida(false)
                .aceptada(null)
                .build();

        return invitacionRepository.save(nuevaInvitacion);
    }

    // Obtener todas las invitaciones no respondidas de un usuario
    public List<Invitacion> getUnansweredInvitacionesByUserId(Integer idUsuario) {
        return invitacionRepository.findByReceptor_IdAndRespondidaFalse(idUsuario);
    }
}

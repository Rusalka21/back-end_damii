package com.back.pid_grupo01.repository;

import com.back.pid_grupo01.model.Invitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitacionRepository extends JpaRepository<Invitacion, Integer> {
    List<Invitacion> findByReceptor_IdAndRespondidaFalse(Integer userId);
}

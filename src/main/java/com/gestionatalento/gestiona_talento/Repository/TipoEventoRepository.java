package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.TipoEvento;

@Repository
public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long> {
    @Query("SELECT t FROM TipoEvento t WHERE t.activo = 'S'")
    List<TipoEvento> obtenerListaActiva();
}

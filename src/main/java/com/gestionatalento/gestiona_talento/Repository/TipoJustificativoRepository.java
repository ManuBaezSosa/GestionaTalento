package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.TipoJustificativo;

@Repository
public interface TipoJustificativoRepository extends JpaRepository<TipoJustificativo, Long> {
    @Query("SELECT t FROM TipoJustificativo t WHERE t.activo = 'S' and t.codTipJustificativo <> 9")
    List<TipoJustificativo> obtenerListaActiva();
}

package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("SELECT new com.gestionatalento.gestiona_talento.Dto.DocumentoDto(" +
           "d.nroDocumento, d.persona, d.tipoDocumento, d.nomArchivo, d.tipArchivo, d.tamArchivo, " +
           "d.estado, d.fecDocumento, d.fecVencimiento, d.observacion) " +
           "FROM Documento d WHERE d.estado IN ('C', 'F') AND d.fecVencimiento < current_date")
    List<DocumentoDto> findByFecVencimiento();
}
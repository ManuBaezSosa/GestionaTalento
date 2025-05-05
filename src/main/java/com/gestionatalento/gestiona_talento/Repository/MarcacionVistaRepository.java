package com.gestionatalento.gestiona_talento.Repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Dto.MarcacionVistaDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionVista;

@Repository
public interface MarcacionVistaRepository extends JpaRepository<MarcacionVista, String> {
    
    @Query("SELECT new com.gestionatalento.gestiona_talento.Dto.MarcacionVistaDto(m.nroDocumento, m.fecha, m.entrada, m.salida) " +
    "FROM MarcacionVista m " +
        "WHERE m.fecha BETWEEN :fecDesde AND :fecHasta AND m.nroDocumento = :nroDocumento")
    List<MarcacionVistaDto> obtenerMarcaciones(
    @Param("nroDocumento") String nroDocumento, 
    @Param("fecDesde") Date fecDesde, 
    @Param("fecHasta") Date fecHasta
);
}
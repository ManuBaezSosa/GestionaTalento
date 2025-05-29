package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.InventarioDocumento;

@Repository
public interface InventarioDocumentoRepository extends JpaRepository<InventarioDocumento, Long> {
    
    @Query("SELECT e FROM DescuentoSalarial e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    InventarioDocumento findByInventarioDocumento(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Modifying
    @Query("DELETE FROM InventarioDocumento e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    int deleteByInventarioDocumento(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);
}

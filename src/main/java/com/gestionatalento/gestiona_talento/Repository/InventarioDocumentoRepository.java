package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.InventarioDocumento;

@Repository
public interface InventarioDocumentoRepository extends JpaRepository<InventarioDocumento, Long> {
    
    @Query("SELECT e FROM DescuentoSalarial e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    InventarioDocumento findByInventarioDocumento(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Modifying
    @Query("DELETE FROM InventarioDocumento e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    int deleteByInventarioDocumento(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Query("SELECT d FROM InventarioDocumento d WHERE d.id.nroPeriodo = :nroPeriodo")
    List<InventarioDocumento> findByInventarioDocumentoExistentes(@Param("nroPeriodo") Long nroPeriodo);

    @Query("SELECT e FROM Empleado e WHERE NOT EXISTS(SELECT null FROM InventarioDocumento x WHERE x.empleado.codEmpleado = e.codEmpleado and x.id.nroPeriodo = :nroPeriodo)")
    List<Empleado> findByInventarioDocumentoFaltantes(@Param("nroPeriodo") Long nroPeriodo);
}

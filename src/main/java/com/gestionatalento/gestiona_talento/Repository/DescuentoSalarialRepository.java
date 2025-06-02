package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;

import jakarta.transaction.Transactional;

@Repository
public interface DescuentoSalarialRepository extends JpaRepository<DescuentoSalarial, Long> {

    @Query("SELECT e FROM DescuentoSalarial e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    DescuentoSalarial findByDescuentoSalarial(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Modifying
    @Transactional
    @Query("DELETE FROM DescuentoSalarial e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    int deleteByDescuentoSalarial(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Query("SELECT e FROM DescuentoSalarial e WHERE e.monto > (e.empleado.asignacion * 0.25)")
    List<DescuentoSalarial> findByMonto();

    @Query("SELECT e FROM DescuentoSalarial e WHERE e.monto > 0 and e.empleado.cargo.departamento.direccion.codDireccion = :codDireccion ORDER BY e.empleado.persona.apellidos ASC, e.empleado.persona.nombres ASC")
    List<DescuentoSalarial> findByDireccion(@Param("codDireccion") Long nroPeriodo);

}

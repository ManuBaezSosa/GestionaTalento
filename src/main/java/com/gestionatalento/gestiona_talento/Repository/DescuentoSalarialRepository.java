package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;

import jakarta.transaction.Transactional;

@Repository
public interface DescuentoSalarialRepository extends JpaRepository<DescuentoSalarial, Long> {

    @Query("SELECT e FROM DescuentoSalarial e WHERE e.id.codPeriodo = :codPeriodo AND e.id.codEmpleado = :codEmpleado")
    DescuentoSalarial findByDescuentoSalarial(@Param("codPeriodo") String codPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Modifying
    @Transactional
    @Query("DELETE FROM DescuentoSalarial e WHERE e.id.codPeriodo = :codPeriodo AND e.id.codEmpleado = :codEmpleado")
    int deleteByDescuentoSalarial(@Param("codPeriodo") String codPeriodo, @Param("codEmpleado") Long codEmpleado);
}

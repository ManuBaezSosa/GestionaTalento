package com.gestionatalento.gestiona_talento.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.EmpleadoNovedad;

@Repository
public interface EmpleadoNovedadRepository extends JpaRepository<EmpleadoNovedad, Long> {

    @Query(value = """
    SELECT COUNT(1)
    FROM empleados_novedades e
    WHERE date_trunc('day', e.fecha) BETWEEN 
          date_trunc('month', CAST(:fecha AS timestamp)) AND 
          (date_trunc('month', CAST(:fecha AS timestamp)) + INTERVAL '1 month - 1 day')
          AND e.estado = 'ALTA'
    """, nativeQuery = true)
    int findByMesAlta(@Param("fecha") LocalDate fecha);

    @Query(value = """
    SELECT COUNT(1)
    FROM empleados_novedades e
    WHERE date_trunc('day', e.fecha) BETWEEN 
          date_trunc('month', CAST(:fecha AS timestamp)) AND 
          (date_trunc('month', CAST(:fecha AS timestamp)) + INTERVAL '1 month - 1 day')
          AND e.estado = 'BAJA'
    """, nativeQuery = true)
    int findByMesBaja(@Param("fecha") LocalDate fecha);
    
}

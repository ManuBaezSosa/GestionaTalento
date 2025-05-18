package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.HoraExtra;

import jakarta.transaction.Transactional;

@Repository
public interface HoraExtraRepository extends JpaRepository<HoraExtra, Long> {

    @Query("SELECT e FROM HoraExtra e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    HoraExtra findByHoraExtra(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);

    @Modifying
    @Transactional
    @Query("DELETE FROM HoraExtra e WHERE e.id.nroPeriodo = :nroPeriodo AND e.id.codEmpleado = :codEmpleado")
    int deleteByHoraExtra(@Param("nroPeriodo") Long nroPeriodo, @Param("codEmpleado") Long codEmpleado);
}

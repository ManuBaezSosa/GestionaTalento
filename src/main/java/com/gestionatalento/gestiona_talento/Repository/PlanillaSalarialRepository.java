package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.PlanillaSalarial;

@Repository
public interface PlanillaSalarialRepository extends JpaRepository<PlanillaSalarial, Long> {

    @Query("SELECT p FROM PlanillaSalarial p WHERE p.situacionLaboral.codSituacionLaboral = :codSituacionLaboral AND p.presupuesto.codPresupuesto = :codPresupuesto AND p.programa.codPrograma = :codPrograma AND p.fuenteFinanciamiento.codFuenteFinanciamiento = :codFuenteFinanciamiento AND p.objetoGasto.codObjetoGasto = :codObjetoGasto AND p.subprograma.codSubprograma = :codSubprograma")
    List<PlanillaSalarial> findByCabecera(@Param("codSituacionLaboral") Long codSituacionLaboral,
                                           @Param("codPresupuesto") Long codPresupuesto,
                                           @Param("codPrograma") Long codPrograma,
                                           @Param("codFuenteFinanciamiento") Long codFuenteFinanciamiento,
                                           @Param("codObjetoGasto") Long codObjetoGasto,
                                           @Param("codSubprograma") Long codSubprograma);

}

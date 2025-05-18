package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.ParametroSalarial;

@Repository
public interface ParametroSalarialRepository extends JpaRepository<ParametroSalarial, Long> {

    @Query("SELECT e FROM ParametroSalarial e WHERE e.situacionLaboral.codSituacionLaboral = :codSituacionLaboral")
    List<ParametroSalarial> findBySituacionLaboral(@Param("codSituacionLaboral") Long codSituacionLaboral);

    @Query("SELECT p FROM ParametroSalarial p WHERE p.situacionLaboral.codSituacionLaboral = :codSituacionLaboral AND p.presupuesto.codPresupuesto = :codPresupuesto")
    List<ParametroSalarial> findByPresupuesto(@Param("codSituacionLaboral") Long codSituacionLaboral,
                                              @Param("codPresupuesto") Long codPresupuesto);

    @Query("SELECT p FROM ParametroSalarial p WHERE p.situacionLaboral.codSituacionLaboral = :codSituacionLaboral AND p.presupuesto.codPresupuesto = :codPresupuesto AND p.programa.codPrograma = :codPrograma")
    List<ParametroSalarial> findByPrograma(@Param("codSituacionLaboral") Long codSituacionLaboral,
                                           @Param("codPresupuesto") Long codPresupuesto,
                                           @Param("codPrograma") Long codPrograma);

    @Query("SELECT p FROM ParametroSalarial p WHERE p.situacionLaboral.codSituacionLaboral = :codSituacionLaboral AND p.presupuesto.codPresupuesto = :codPresupuesto AND p.programa.codPrograma = :codPrograma AND p.fuenteFinanciamiento.codFuenteFinanciamiento = :codFuenteFinanciamiento")
    List<ParametroSalarial> findByFuenteFinanciamiento(@Param("codSituacionLaboral") Long codSituacionLaboral,
                                           @Param("codPresupuesto") Long codPresupuesto,
                                           @Param("codPrograma") Long codPrograma,
                                           @Param("codFuenteFinanciamiento") Long codFuenteFinanciamiento);

    @Query("SELECT p FROM ParametroSalarial p WHERE p.situacionLaboral.codSituacionLaboral = :codSituacionLaboral AND p.presupuesto.codPresupuesto = :codPresupuesto AND p.programa.codPrograma = :codPrograma AND p.fuenteFinanciamiento.codFuenteFinanciamiento = :codFuenteFinanciamiento AND p.objetoGasto.codObjetoGasto = :codObjetoGasto")
    List<ParametroSalarial> findByObjetoGasto(@Param("codSituacionLaboral") Long codSituacionLaboral,
                                           @Param("codPresupuesto") Long codPresupuesto,
                                           @Param("codPrograma") Long codPrograma,
                                           @Param("codFuenteFinanciamiento") Long codFuenteFinanciamiento,
                                           @Param("codObjetoGasto") Long codObjetoGasto);

}

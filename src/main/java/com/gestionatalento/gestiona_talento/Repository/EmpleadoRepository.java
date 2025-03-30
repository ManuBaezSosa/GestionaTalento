package com.gestionatalento.gestiona_talento.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Dto.PasantesDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    @Query("SELECT e FROM Empleado e JOIN e.persona p WHERE p.nroDocumento = :nroDocumento")
    Optional<Empleado> findByNroDocumento(@Param("nroDocumento") String nroDocumento);
    
    @Query("SELECT e FROM Empleado e JOIN e.persona p WHERE p.nombres = :nombres")
    List<Empleado> findByNombre(@Param("nombres") String nombre);
    
}

package com.gestionatalento.gestiona_talento.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    @Query("SELECT e FROM Empleado e JOIN e.persona p WHERE p.nroDocumento = :nroDocumento")
    List<Empleado> findByNroDocumento(@Param("nroDocumento") String nroDocumento);
    
    @Query("SELECT e FROM Empleado e JOIN e.persona p WHERE p.nombres = :nombres")
    List<Empleado> findByNombre(@Param("nombres") String nombre);

    @Query("SELECT e FROM Empleado e WHERE e.estado = 'A' and e.codEmpleado = :codEmpleado")
    Empleado findByIdEmpleadoActivo(@Param("codEmpleado") Long codEmpleado);

    @Query("SELECT e FROM Empleado e JOIN e.persona p WHERE e.estado = 'A' and p.codPersona = :codPersona")
    Empleado findByCodPersonaEmpleadoActivo(@Param("codPersona") Long codPersona);

    @Query("SELECT e FROM Empleado e JOIN e.persona p WHERE e.estado = 'A' and p.nroDocumento = :nroDocumento")
    Empleado findByNroDocumentoEmpleadoActivo(@Param("nroDocumento") String nroDocumento);
    
}

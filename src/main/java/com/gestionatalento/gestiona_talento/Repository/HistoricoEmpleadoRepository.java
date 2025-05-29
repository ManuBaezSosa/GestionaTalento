package com.gestionatalento.gestiona_talento.Repository;

import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.HistoricoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HistoricoEmpleadoRepository extends JpaRepository<HistoricoEmpleado, Long> {
    
}

package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.GradoSalarial;

@Repository
public interface GradoSalarialRepository extends JpaRepository<GradoSalarial, Long> {
    
}

package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    
}

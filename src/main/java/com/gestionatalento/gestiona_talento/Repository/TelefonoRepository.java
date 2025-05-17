package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Telefono;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
    
}

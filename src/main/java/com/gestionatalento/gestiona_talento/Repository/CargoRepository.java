package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    
}

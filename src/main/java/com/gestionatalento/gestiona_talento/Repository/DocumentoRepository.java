package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    
}

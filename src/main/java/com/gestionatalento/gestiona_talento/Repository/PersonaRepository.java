package com.gestionatalento.gestiona_talento.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
    // Método personalizado para buscar por nombre
    public List<Persona> findByNombres(String nombres);
    // Método personalizado para buscar por nombre
    public Optional<Persona> findByNroDocumento(String nroDocumento);
    
}

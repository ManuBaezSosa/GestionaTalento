package com.gestionatalento.gestiona_talento.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Rol;

@Repository
public interface RoleRepository extends JpaRepository<Rol,Long>{
    public Optional<Rol> findByCodRol(String codRol);
    
}

package com.gestionatalento.gestiona_talento.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findById(Long id); 
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.permisosAdicionales")
    List<Usuario> findAllWithPermisos();

    
} 
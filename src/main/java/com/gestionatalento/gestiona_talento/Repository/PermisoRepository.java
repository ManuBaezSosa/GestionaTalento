package com.gestionatalento.gestiona_talento.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entities.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso,Long>{
    List<Permiso> findAll();
}

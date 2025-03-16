package com.gestionatalento.gestiona_talento.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Optional<Role> findByName(String name);


}

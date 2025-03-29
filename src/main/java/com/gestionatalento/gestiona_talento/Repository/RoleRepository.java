package com.gestionatalento.gestiona_talento.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    public Optional<Role> findByName(String name);
    


}

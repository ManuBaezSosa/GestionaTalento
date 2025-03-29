package com.gestionatalento.gestiona_talento.Service.Role;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entity.Role;

@Service
public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);
    Role save(Role rol);
    Role delete(Role rol);
}

package com.gestionatalento.gestiona_talento.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entity.Rol;

@Service
public interface RoleService {
    List<Rol> findAll();
    Rol findById(Long id);
    Rol save(Rol rol);
    Rol delete(Rol rol);
}

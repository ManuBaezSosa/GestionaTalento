package com.gestionatalento.gestiona_talento.Service.Permiso;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Permiso;

@Service
public interface PermisoService {
    List<Permiso> findAllPermisos();
    Permiso findById(Long id);
    Permiso save(Permiso permiso);
    Permiso delete(Permiso permiso);
}

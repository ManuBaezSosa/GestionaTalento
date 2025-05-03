package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gestionatalento.gestiona_talento.Entity.Permiso;
import com.gestionatalento.gestiona_talento.Repository.PermisoRepository;
import com.gestionatalento.gestiona_talento.Service.PermisoService;

public class PermisoServiceImpl implements PermisoService  {

    @Autowired
    private PermisoRepository permisoRepository;

    @Override
    public List<Permiso> findAllPermisos() {
        return permisoRepository.findAll();
    }

    @Override
    public Permiso findById(Long id) {
    Optional<Permiso> permisoOtp = permisoRepository.findById(id);
    if(!permisoOtp.isPresent()){
            throw new RolNotFoundException("El permiso con el id " + id + " no existe");
    }

        return permisoOtp.get();
    }

    @Override
    public Permiso save(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    public Permiso delete(Permiso permiso) {
        Optional<Permiso> permisoOtp = permisoRepository.findById(permiso.getId());
        if(!permisoOtp.isPresent()){
            throw new RolNotFoundException("El permiso con el id " + permiso.getId() + " no existe");
        }
        permisoRepository.delete(permiso);
        return permisoOtp.get();
    }



    public class RolNotFoundException extends RuntimeException {
        public RolNotFoundException(String message) {
            super(message);
        }
    }

}

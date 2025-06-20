package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gestionatalento.gestiona_talento.Entity.Rol;
import com.gestionatalento.gestiona_talento.Repository.RoleRepository;
import com.gestionatalento.gestiona_talento.Service.RoleService;

public class RolServiceImpl implements RoleService {

    @Autowired
    private RoleRepository rolRepository;

    @Override
    public Rol delete(Rol rol) {
       // Para borrar un rol primero lo busco para asi devolverlo
       Optional<Rol> rolEliminado = rolRepository.findByCodRol(rol.getCodRol());

       if(!rolEliminado.isPresent()){
            throw new RolNotFoundException("El rol con nombre " + rol.getCodRol() + " no existe");
       }

       rolRepository.delete(rol);

       return rolEliminado.get();
       
    }

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Long id) {
        // Para borrar un rol primero lo busco para asi devolverlo
       Optional<Rol> rolBuscado = rolRepository.findById(id);

       if(!rolBuscado.isPresent()){
            throw new RolNotFoundException("El rol con el id " + id + " no existe");
       }

        return rolBuscado.get();
    }

    
    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public class RolNotFoundException extends RuntimeException {
        public RolNotFoundException(String message) {
            super(message);
        }
    }
}

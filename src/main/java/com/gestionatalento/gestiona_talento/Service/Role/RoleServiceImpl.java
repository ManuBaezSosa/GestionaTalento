package com.gestionatalento.gestiona_talento.Service.Role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gestionatalento.gestiona_talento.Entities.Role;
import com.gestionatalento.gestiona_talento.Repository.RoleRepository;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role delete(Role rol) {
       // Para borrar un rol primero lo busco para asi devolverlo
       Optional<Role> rolEliminado = roleRepository.findByName(rol.getName());

       if(!rolEliminado.isPresent()){
            throw new RolNotFoundException("El rol con nombre " + rol.getName() + " no existe");
       }

       roleRepository.delete(rol);

       return rolEliminado.get();
       
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        // Para borrar un rol primero lo busco para asi devolverlo
       Optional<Role> rolBuscado = roleRepository.findById(id);

       if(!rolBuscado.isPresent()){
            throw new RolNotFoundException("El rol con el id " + id + " no existe");
       }

        return rolBuscado.get();
    }

    
    @Override
    public Role save(Role rol) {
        return roleRepository.save(rol);
    }

    public class RolNotFoundException extends RuntimeException {
        public RolNotFoundException(String message) {
            super(message);
        }
    }
}

package com.gestionatalento.gestiona_talento.Service.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Permiso;
import com.gestionatalento.gestiona_talento.Entities.Role;
import com.gestionatalento.gestiona_talento.Entities.Usuario;
import com.gestionatalento.gestiona_talento.Repository.PermisoRepository;
import com.gestionatalento.gestiona_talento.Repository.RoleRepository;
import com.gestionatalento.gestiona_talento.Repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermisoRepository permisoRepository; // Correcto

    @Override
    public Usuario save(Usuario usuario) {
        //Si el usuario es Admin entonces
        if(usuario.isAdmin()){
            Role rolAdmin = roleRepository.findByName("ADMIN")
            .orElseThrow(() -> new RuntimeException("El rol ADMIN no existe"));

            // Obtenemos toda la lista de permisos
            List<Permiso> todosPermisos = permisoRepository.findAll();

            // Asignamos todos los permisos al rol ADMIN
            rolAdmin.setPermissions(new HashSet<>(todosPermisos));;

             // Guardamos el rol actualizado en la base de datos
             roleRepository.save(rolAdmin);
        
        }

        return usuarioRepository.save(usuario);
    } 

    @Override
    public Usuario delete(Usuario usuario) {

        Optional<Usuario> optUser = usuarioRepository.findById(usuario.getId());
        if(optUser.isPresent()){
            usuarioRepository.delete(usuario);
        }else{
            throw new RuntimeException("No se encontro el usuario a borrar");
        }
        usuarioRepository.delete(usuario);

        return optUser.get();
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.of(usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario con id " + id + " no existe")));
    }   

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("El usuario con nombre " + username + " no existe"));
    }
    

   


    
}
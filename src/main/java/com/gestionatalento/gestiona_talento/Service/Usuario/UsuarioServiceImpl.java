package com.gestionatalento.gestiona_talento.Service.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Permiso;
import com.gestionatalento.gestiona_talento.Entities.Role;
import com.gestionatalento.gestiona_talento.Entities.Usuario;
import com.gestionatalento.gestiona_talento.Jwt.ApplicationConfig;
import com.gestionatalento.gestiona_talento.Repository.PermisoRepository;
import com.gestionatalento.gestiona_talento.Repository.RoleRepository;
import com.gestionatalento.gestiona_talento.Repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final Jwt.ApplicationConfig applicationConfig;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermisoRepository permisoRepository;

    UsuarioServiceImpl(Jwt.ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
       if(usuario.isAdmin()){

            //DEJAMOS UNOS LOG
            System.out.println("Creando usuario ADMIN...");

            //Buscamos que exista el rol de admin
            Role roleAdmin = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("El rol ADMIN no existe"));

            //Asiganamos el rol ADMIN
            if (!usuario.getRoles().contains(roleAdmin)) {
                usuario.getRoles().add(roleAdmin);
            }

            //Luego agregamos los permisos
            List<Permiso> allPermisos = permisoRepository.findAll();

            usuario.getPermisosAdicionales().addAll(allPermisos);

       }else{
            System.out.println("Creando usuario USER...");

            //Buscamos si hay un rol usuario
            Role rolUser = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("El rol ADMIN no existe"));

            // Asignar rol USER
            if (!usuario.getRoles().contains(rolUser)) {
                usuario.getRoles().add(rolUser);
            }
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



    @Override
    public Usuario asiganarPermisos(Usuario usuario, List<Long> permisoIds) {
        //Primero buscamos si existe 
        Optional<Usuario> userOptional = usuarioRepository.findById(usuario.getId());
        if(!userOptional.isPresent()){
            new T
        }
        
        return null;
    }

    
    


   


    
}
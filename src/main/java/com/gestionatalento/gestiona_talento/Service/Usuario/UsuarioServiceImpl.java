package com.gestionatalento.gestiona_talento.Service.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.EntityNotFoundException;
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
    private PermisoRepository permisoRepository;


    /**
     * Este metodo crea a los usuarios
     */
    @Override
    public Usuario crearUsuario(Usuario usuario) {
       if(usuario.isAdmin()){


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

            //Buscamos si hay un rol usuario
            Role rolUser = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("El rol USER no existe"));

            // Asignar rol USER
            if (!usuario.getRoles().contains(rolUser)) {
                usuario.getRoles().add(rolUser);
            }
       }

       return usuarioRepository.save(usuario);
    }
    


    @Override
    public Usuario delete(Usuario usuario) {
        Usuario usuario1 = obtenerUsuarioPorId(usuario.getId());

        usuarioRepository.delete(usuario);

        return usuario1;
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
    public Usuario asignarPermisos(Long idUsuario, List<Long> permisoIds) {
        Usuario usuario = obtenerUsuarioPorId(idUsuario);
        Set<Permiso> permisosEncontrados = obtenerPermisosPorIds(permisoIds);

        usuario.getPermisosAdicionales().addAll(permisosEncontrados);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario eleminarPermisosUsuarios(Long idUsuario, List<Long> permisoIds) {
        Usuario usuario = obtenerUsuarioPorId(idUsuario);
        Set<Permiso> permisosEncontrados = obtenerPermisosPorIds(permisoIds);

        usuario.getPermisosAdicionales().removeAll(permisosEncontrados);

        return usuarioRepository.save(usuario);
    }

    private Usuario obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("El usuario con id " + idUsuario + " no existe"));
    }

    private Set<Permiso> obtenerPermisosPorIds(List<Long> permisoIds) {
        List<Permiso> permisos = permisoRepository.findAllById(permisoIds);
        if (permisos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron permisos con los IDs: " + permisoIds);
        }
        return new HashSet<>(permisos);
    }





}
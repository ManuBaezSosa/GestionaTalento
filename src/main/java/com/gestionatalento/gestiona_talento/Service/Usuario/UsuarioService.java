
package com.gestionatalento.gestiona_talento.Service.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entity.Usuario;

@Service
public interface UsuarioService  {
    public List<Usuario> findAll(); 
    public Optional<Usuario> findById(Long id);
    public Usuario crearUsuario(Usuario usuario);
    public Usuario delete(Usuario usuario);
    public Usuario findByUsername(String user);   
    public Usuario asignarPermisos(Long idUsuario, List<Long> permisoIds);
    Usuario eleminarPermisosUsuarios(Long idUsuario, List<Long> permisoIds);

}
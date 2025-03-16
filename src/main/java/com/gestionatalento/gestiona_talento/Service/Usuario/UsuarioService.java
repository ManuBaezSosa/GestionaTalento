
package com.gestionatalento.gestiona_talento.Service.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Usuario;

@Service
public interface UsuarioService  {
    public List<Usuario> findAll(); 
    public Optional<Usuario> findById(Long id);
    public Usuario save(Usuario usuario);
    public Usuario delete(Usuario usuario);
    public Usuario findByUsername(String user);   
    
    //Este metodo nos devolvera una lista de usuario con sus roles y permisos

}
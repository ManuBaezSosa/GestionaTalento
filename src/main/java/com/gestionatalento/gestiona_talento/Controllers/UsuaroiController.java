package com.gestionatalento.gestiona_talento.Controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entities.Role;
import com.gestionatalento.gestiona_talento.Entities.Usuario;
import com.gestionatalento.gestiona_talento.Repository.RoleRepository;
import com.gestionatalento.gestiona_talento.Repository.UsuarioRepository;
import com.gestionatalento.gestiona_talento.Request.UsuarioRequest;


@RestController
@RequestMapping("/auth")
public class UsuaroiController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    

    @GetMapping
    public String prueba(){
        return "Funca";
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequest request) {
        try {
            // Verificar si ya existe un usuario con ese username
            try {
                Optional<Usuario> existente = usuarioRepository.findByUsername(request.getUsername());
                if (existente.isPresent()) {
                    return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("El nombre de usuario ya existe");
                }
            } catch (RuntimeException e) {
                // El usuario no existe, podemos continuar
            }
            
            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setUsername(request.getUsername());
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
            usuario.setNombre(request.getNombreCompleto());
            usuario.setDocuemento(request.getDocumento());
            usuario.setCargo(request.getCargo());
            usuario.setFechaAlta(Date.valueOf(LocalDate.now()));
            usuario.setEstado("ACTIVO");
            usuario.setAdmin(request.isAdmin()); // Usa el helper method
            
            // Asignar rol según el tipo de usuario
            Set<Role> roles = new HashSet<>();
            Role rol;
            
            if (request.isAdmin()) {
                rol = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rol de administrador no encontrado"));
            } else {
                rol = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rol de usuario no encontrado"));
            }
            
            roles.add(rol);
            usuario.setRoles(roles);
            
            // El service se encargará de asignar todos los permisos si es admin
            Usuario usuarioCreado = usuarioRepository.save(usuario);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario " + (request.isAdmin() ? "administrador" : "normal") + 
                    " creado exitosamente con ID: " + usuarioCreado.getId());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear usuario: " + e.getMessage());
        }
}

}

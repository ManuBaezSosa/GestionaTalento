package com.gestionatalento.gestiona_talento.Controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.Usuario;
import com.gestionatalento.gestiona_talento.Repository.UsuarioRepository;
import com.gestionatalento.gestiona_talento.Request.UsuarioRequest;
import com.gestionatalento.gestiona_talento.ServiceImpl.UsuarioServiceImpl;


@RestController
@RequestMapping("/api/admin/users") // Define la ruta base para todas las solicitudes
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;


    /*
     * Endpoint para crear usuarios
     * Solo administradores pueden acceder
     */
    @PostMapping // Mapea este método al endpoint POST /api/admin/users
   // @PreAuthorize("hasAuthority('ADMIN')") // Solo usuarios con rol ADMIN pueden acceder
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


            // Usar el servicio para crear el usuario
            Usuario usuarioCreado = usuarioServiceImpl.crearUsuario(usuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario " + (request.isAdmin() ? "administrador" : "normal") + " creado exitosamente con ID: " + usuarioCreado.getId());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear usuario: " + e.getMessage());
        }
    }

    /*
     * Endpoint para eliminar usuarios por ID
     * Solo administradores pueden acceder
     */
    @DeleteMapping("/{idUsuario}")
    @PreAuthorize("hasAuthority('ADMIN')") // Solo usuarios con rol ADMIN pueden acceder
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(idUsuario);
            return ResponseEntity.ok(usuarioOptional.get()); // Devuelve el usuario eliminado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + idUsuario + " no existe.");
        }
    }


    /**
     * Endpoint para obtener un usuario por su ID
     * Solo administradores pueden acceder
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") // Solo usuarios con rol ADMIN pueden acceder
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return usuarioRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener usuario: " + e.getMessage());
        }
    }


    /**
     * Endpoint para asiganar permisos los usuarios
     * Solo administradores pueden acceder
     */
    @PostMapping("/{idUsuario}/permisos")
    @PreAuthorize("hasAuthority('ADMIN')") // Solo usuarios con rol ADMIN pueden acceder
    public ResponseEntity<Usuario> asignarPermisos(
            @PathVariable Long idUsuario, 
            @RequestBody List<Long> permisoIds) {
        Usuario usuarioActualizado = usuarioServiceImpl.asignarPermisos(idUsuario, permisoIds);
        return ResponseEntity.ok(usuarioActualizado);
    }


    /**
     * Endpoint para elimar permisos por ID a los usuarios
     * Solo administradores pueden acceder
     */
    @DeleteMapping("/{idUsuario}/permisos")
    @PreAuthorize("hasAuthority('ADMIN')") // Solo usuarios con rol ADMIN pueden acceder
    public ResponseEntity<Usuario> eliminarPermisos(
            @PathVariable Long idUsuario, 
            @RequestBody List<Long> permisoIds) {
        Usuario usuarioActualizado = usuarioServiceImpl.eleminarPermisosUsuarios(idUsuario, permisoIds);
        return ResponseEntity.ok(usuarioActualizado);
    }




}

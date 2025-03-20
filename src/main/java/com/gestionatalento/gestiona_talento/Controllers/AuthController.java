package com.gestionatalento.gestiona_talento.Controllers;

import com.gestionatalento.gestiona_talento.Entities.Usuario;
import com.gestionatalento.gestiona_talento.Repository.UsuarioRepository;
import com.gestionatalento.gestiona_talento.Request.LoginRequest;
import com.gestionatalento.gestiona_talento.Response.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Jwt.JwtService;

import lombok.AllArgsConstructor;

import java.util.Optional;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/auth") // Define la ruta base para todas las solicitudes a este controlador
@AllArgsConstructor
public class AuthController {

    // Inyección de dependencias
    @Autowired
    private final AuthenticationManager authenticationManager; // Gestor de autenticación de Spring Security
    @Autowired
    private final JwtService jwtService; // Servicio personalizado para manipular tokens JWT
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Buscamos el usuario en la base de datos
        Optional<Usuario> usuarioBd = usuarioRepository.findByUsername(loginRequest.getUsername());

        // Verificamos si el usuario existe
        if (!usuarioBd.isPresent()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }

        // Verificamos si el usuario está bloqueado
        if (usuarioBd.get().isBlocked()) {
            return ResponseEntity.status(403).body("La cuenta está bloqueada");
        }

        try {
            // Intentamos autenticar el usuario con las credenciales proporcionadas
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Si la autenticación es exitosa, reiniciamos los intentos fallidos
            Usuario usuario = usuarioBd.get();
            usuario.setIntentosFallidos(0);
            usuarioRepository.save(usuario);

            // Generamos el token
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtService.getToken(user);

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            // Si las credenciales son incorrectas, incrementamos los intentos fallidos
            Usuario usuario = usuarioBd.get();
            
            // Manejamos el caso donde intentosFallidos es null
            Integer intentosActuales = usuario.getIntentosFallidos();
            if (intentosActuales == null) {
                intentosActuales = 0;
            }
            
            // Incrementamos y guardamos
            usuario.setIntentosFallidos(intentosActuales + 1);
            
            // Si los intentos fallidos llegan a 3, bloqueamos al usuario
            if (usuario.getIntentosFallidos() >= 3) {
                usuario.setEstado("BLOQUEADO");
            }
            
            // Guardamos los cambios en la base de datos
            usuarioRepository.save(usuario);
            
            return ResponseEntity.status(401).body("Credenciales inválidas");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en la autenticación: " + e.getMessage());
        }
    }
}

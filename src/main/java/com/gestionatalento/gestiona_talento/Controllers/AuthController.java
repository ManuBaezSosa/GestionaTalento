package com.gestionatalento.gestiona_talento.Controllers;

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

import com.gestionatalento.gestiona_talento.Entities.AuthResponse;
import com.gestionatalento.gestiona_talento.Entities.LoginRequest;
import com.gestionatalento.gestiona_talento.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/auth") // Define la ruta base para todas las solicitudes a este controlador
@RequiredArgsConstructor // Genera constructor con todos los campos final
public class AuthController {
    
    // Inyección de dependencias
    private final AuthenticationManager authenticationManager; // Gestor de autenticación de Spring Security
    private final JwtService jwtService; // Servicio personalizado para manipular tokens JWT
    
    /**
     * Endpoint para iniciar sesión y obtener token JWT
     * @param request Contiene username y password del usuario
     * @return ResponseEntity con el token JWT si la autenticación es exitosa
     */
    @PostMapping("/login") // Mapea este método al endpoint POST /auth/login
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Intentamos autenticar al usuario con las credenciales proporcionadas
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), // Username proporcionado
                    request.getPassword()  // Password proporcionado
                )
            );
            
            // Si llegamos aquí, la autenticación fue exitosa
            UserDetails user = (UserDetails) authentication.getPrincipal(); // Obtenemos el usuario autenticado
            
            // Generamos un token JWT para el usuario
            String token = jwtService.getToken(user);
            
            // Retornamos una respuesta exitosa con el token
            return ResponseEntity.ok(new AuthResponse(token));
            
        } catch (BadCredentialsException e) {
            // Si las credenciales son incorrectas, devolvemos un error 401 Unauthorized
            return ResponseEntity.status(401).body("Credenciales inválidas");
        } catch (Exception e) {
            // Capturamos cualquier otra excepción y devolvemos un error 500
            return ResponseEntity.status(500).body("Error en la autenticación: " + e.getMessage());
        }
    }

    
}

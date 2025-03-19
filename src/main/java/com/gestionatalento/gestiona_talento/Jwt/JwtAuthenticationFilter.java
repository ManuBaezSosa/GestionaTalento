package com.gestionatalento.gestiona_talento.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Marca esta clase como un componente Spring que debe ser detectado y registrado automáticamente
@RequiredArgsConstructor // Genera un constructor con todos los campos final como parámetros
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Inyección de dependencias a través del constructor (@RequiredArgsConstructor)
    private final JwtService jwtService; // Servicio para manejar operaciones JWT
    private final UserDetailsService userDetailsService; // Servicio para cargar detalles del usuario

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        // Obtenemos el token JWT del request
        final String token = getTokenFromRequest(request);
        final String username;

        // Si no hay token, continuamos con la cadena de filtros sin autenticar
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraemos el username del token
        username = jwtService.getUsernameFromToken(token);

        // Validamos que el username exista y que el usuario no esté ya autenticado
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Cargamos los detalles del usuario desde la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Verificamos que el token sea válido para este usuario
            if (jwtService.isTokenValid(token, userDetails)) {
                // Creamos un token de autenticación con los detalles del usuario y sus autoridades (roles y permisos)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, // Principal (el usuario)
                        null, // Credenciales (no necesarias después de la autenticación)
                        userDetails.getAuthorities() // Autoridades (roles y permisos)
                );
                
                // Agregamos detalles de la solicitud al token de autenticación
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Establecemos la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // Continuamos con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    // Método para extraer el token JWT del encabezado de autorización
    private String getTokenFromRequest(HttpServletRequest request) {
        // Obtenemos el encabezado de autorización
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Si el encabezado existe y comienza con "Bearer ", extraemos el token
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Quitamos "Bearer " para obtener solo el token
        }
        return null; // Si no hay token o no tiene el formato correcto, retornamos null
    }
}
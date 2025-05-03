package com.gestionatalento.gestiona_talento.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gestionatalento.gestiona_talento.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration // Indica que esta clase proporciona configuración de beans para Spring
@EnableWebSecurity // Habilita la seguridad web de Spring Security
@EnableMethodSecurity // Habilita la seguridad a nivel de método con anotaciones como @PreAuthorize
@RequiredArgsConstructor // Genera un constructor con todos los campos final como parámetros
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authProvider;

    @Bean // Indica que este método proporciona un bean para el contenedor de Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            // Deshabilitamos CSRF porque usaremos tokens JWT que son inmunes a ataques CSRF
            .csrf(AbstractHttpConfigurer::disable)
            
            // Configuramos las reglas de autorización HTTP
            .authorizeHttpRequests(authRequest -> 
                authRequest
                    // Permitimos acceso público a las rutas de autenticación
                    .requestMatchers("/auth/**").permitAll()
                    
                    // Restringimos el acceso a rutas de administrador solo a usuarios con rol ADMIN
                    //.requestMatchers("/api/admin/**").hasAuthority("ADMIN")

                    .requestMatchers("/api/admin/**").permitAll()

                    .requestMatchers("/empleados/**").permitAll()

                    .requestMatchers("/personas/**").permitAll()

                    .requestMatchers("/configuraciones/**").permitAll()

                    .requestMatchers("/descuentos-salariales/**").permitAll()


                    // Cualquier otra solicitud requiere autenticación
                    //
                    .anyRequest().authenticated()
            )
            
            // Configuramos la administración de sesiones como STATELESS (sin estado)
            // Esto es importante para aplicaciones RESTful con JWT
            .sessionManagement(sessionManager -> 
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Registramos nuestro proveedor de autenticación
            .authenticationProvider(authProvider)
            
            // Agregamos nuestro filtro JWT antes del filtro de autenticación estándar
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            
            // Construimos la cadena de filtros de seguridad
            .build();
    }
}

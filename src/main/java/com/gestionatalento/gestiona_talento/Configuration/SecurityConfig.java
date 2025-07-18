package com.gestionatalento.gestiona_talento.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;

import lombok.RequiredArgsConstructor;

@Configuration // Indica que esta clase proporciona configuración de beans para Spring
@EnableWebSecurity // Habilita la seguridad web de Spring Security
@EnableMethodSecurity // Habilita la seguridad a nivel de método con anotaciones como @PreAuthorize
@RequiredArgsConstructor // Genera un constructor con todos los campos final como parámetros
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authProvider;

    @Bean // Indica que este método proporciona un bean para el contenedor de Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(Customizer.withDefaults())
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

                    .requestMatchers("/marcaciones/**").permitAll()

                    .requestMatchers("/horas-extras/**").permitAll()

                    .requestMatchers("/justificativos/**").permitAll()

                    .requestMatchers("/vacaciones/**").permitAll()

                    .requestMatchers("/salarios/**").permitAll()

                    .requestMatchers("/contratos/**").permitAll()


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
            
            // Construimos la cadena de filtros de seguridad
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
}

package com.gestionatalento.gestiona_talento.Controllers;

import com.gestionatalento.gestiona_talento.Dto.AuthDto;
import com.gestionatalento.gestiona_talento.Entity.Usuario;
import com.gestionatalento.gestiona_talento.Repository.UsuarioRepository;
import com.gestionatalento.gestiona_talento.Response.AuthResponse;
import com.gestionatalento.gestiona_talento.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto authDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getNombreUsuario(), authDto.getCredencial())
            );

            Usuario usuario = usuarioRepository.findByNombreUsuario(authDto.getNombreUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            String jwt = jwtService.generateToken(usuario.getNombreUsuario());

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
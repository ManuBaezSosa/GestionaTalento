package com.gestionatalento.gestiona_talento.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service // Marca esta clase como un servicio Spring
public class JwtService {

    // Clave secreta para firmar los tokens JWT - En producción, esta debería estar en un archivo de configuración seguro
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    
    // Tiempo de expiración del token: 24 horas en milisegundos
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // Método público para generar un token para un usuario
    public String getToken(UserDetails user) {
        // Creamos un mapa para almacenar información adicional (claims)
        Map<String, Object> extraClaims = new HashMap<>();
        
        // Obtenemos todas las autoridades (roles y permisos) del usuario y las convertimos a strings
        List<String> authorities = user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority) // Extrae el nombre de cada autoridad
            .collect(Collectors.toList()); // Convierte el stream a lista
        
        // Agregamos la lista de autoridades al mapa de claims extras
        extraClaims.put("authorities", authorities);
        
        // Generamos el token con los claims extras
        return getToken(extraClaims, user);
    }

    // Método privado para construir el token JWT
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder() // Iniciamos el constructor del token
             .claims() // Accedemos a la sección de claims
             .add(extraClaims) // Agregamos los claims extras
             .subject(user.getUsername()) // Establecemos el sujeto (username)
             .issuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión (ahora)
             .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración
             .and() // Volvemos al constructor principal
             .signWith(getKey()) // Firmamos el token con nuestra clave secreta
             .compact(); // Construimos la cadena compacta del token
    }

    // Método privado para obtener la clave de firma
    private Key getKey() {
        return SECRET_KEY;
    }

    // Método para extraer el username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject); // El subject del token contiene el username
    }

    // Método para extraer las autoridades del token
    @SuppressWarnings("unchecked") // Suprimimos la advertencia de conversión no verificada
    public List<String> getAuthoritiesFromToken(String token) {
        Claims claims = getAllClaims(token);
        return (List<String>) claims.get("authorities"); // Obtenemos la lista de autoridades
    }

    // Método para validar si un token es válido para un usuario
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        // El token es válido si el username coincide y no ha expirado
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Método para obtener todos los claims del token
    private Claims getAllClaims(String token) {
        return Jwts
                .parser() // Iniciamos el parser de JWT
                .verifyWith((SecretKey)getKey()) // Verificamos la firma con nuestra clave
                .build() // Construimos el parser
                .parseSignedClaims(token) // Parseamos el token firmado
                .getPayload(); // Obtenemos el payload (claims)
    }

    // Método genérico para extraer un claim específico del token
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims); // Aplicamos la función de extracción al claim
    }

    // Método para obtener la fecha de expiración del token
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    // Método para verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date()); // Comparamos con la fecha actual
    }
}

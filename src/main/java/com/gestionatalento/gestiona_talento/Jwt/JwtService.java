package com.gestionatalento.gestiona_talento.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    //Aqui creamos la firma ed nuestro JWT
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

   public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        return Jwts.builder()
             .claims()
             .add(extraClaims)  // Agrega claims adicionales
             .subject(user.getUsername())  // Subject (usuario)
             .issuedAt(new Date(System.currentTimeMillis()))  // Fecha de emisión
             .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))  // Fecha de expiración (24 minutos)
             .and()
             .signWith(getKey())  // Firma el token con la clave secreta
             .compact();  // Convierte el token a una cadena compacta
    }

    private Key getKey() {
        return SECRET_KEY;
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith((SecretKey)getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }


}

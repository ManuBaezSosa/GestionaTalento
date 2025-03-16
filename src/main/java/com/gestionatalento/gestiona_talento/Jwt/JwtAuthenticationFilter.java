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

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private JwtService jwtService;
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Primero obtenemos el TOKEN del request
        final String token = getTokenFromRequest(request);
        final String username;

        if (token == null) {
            filterChain.doFilter(request, response);//devolvemos a la cadena de filtros
            return;
        }

        //Si todo va bien yo debo de accerder al uusername del token que nos provee el servicio de jwt
        username=jwtService.getUsernameFromToken(token); //aqui obtemos el dato puro y duro del token

        //Aca si el username que obtenemos del token no es nulo y no se encuenta en el security context holder lo buscamos en la base de datos
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //debemos validar si el token es valido
            if(jwtService.isTokenValid(token,userDetails )){
                //si esto es valido debemos de actualizar el security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                //Una vez que se crea el UsernamePasswordAuthenticationToken debemos de settear el details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);



            }

        }

    }

    /*Este nos devuelve el token y el token no es mas que una cadena de caracteres*/
    private String getTokenFromRequest(HttpServletRequest request) {
        //Primero obtemos el encabezado del request porque es ahi donde se encuentra el token
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

        //Este encabezado que vamos a extraer va a comenzar con la plabra Bearer es decir que authHeader comienza con Bearer
        //Es por esto es que debemos omitir este encabezado
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); //aqui extraemos el token puro y duro
        }else{
            return null;
        }


    }
}

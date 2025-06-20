package com.gestionatalento.gestiona_talento.Security;

import java.util.Collection;
import java.util.stream.Collectors;

import com.gestionatalento.gestiona_talento.Entity.Usuario;
import com.gestionatalento.gestiona_talento.Entity.UsuarioRol;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {

    private final Usuario usuario;

    public UsuarioPrincipal(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
            .map(UsuarioRol::getRol)
            .map(rol -> new SimpleGrantedAuthority(rol.getCodRol()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getCredencial();
    }

    @Override
    public String getUsername() {
        return usuario.getNombreUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "S".equalsIgnoreCase(usuario.getActivo());
    }
}

package com.gestionatalento.gestiona_talento.Entities;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios", schema = "prueba")
public class Usuario implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario")
    private Long id ;
    private String username;
    private String password;
    @Column(name = "nombre_completo")
    private String nombre;
    private String docuemento;
    private Date fechaAlta;
    private Date fechaBaja;
    private String cargo;
    private String estado;
    private boolean admin;

    public boolean isBlocked() {
        return "BLOQUEADO".equals(estado) || (intentosFallidos != null && intentosFallidos >= 3);
    }

    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "usuario_permisos",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private Set<Permiso> permisosAdicionales = new HashSet<>();

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos = 0;

    @Column(name = "creado_por")
    private String creadoPor;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Solo agregar roles como authorities
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        // Agregar permisos directos del usuario
        for (Permiso permiso : permisosAdicionales) {
            authorities.add(new SimpleGrantedAuthority(permiso.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return username; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

  
    

}

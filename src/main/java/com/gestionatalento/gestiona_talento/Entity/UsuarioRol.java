package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios_roles")
public class UsuarioRol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_usuario_rol")
    private Long nroUsuarioRol;

    @ManyToOne
    @JoinColumn(name = "roles", referencedColumnName = "nro_rol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private Usuario usuario;
    
}

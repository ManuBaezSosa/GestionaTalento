
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
@Table(name = "telefonos")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id_telefono")
    private Long idCorreo;
    
    @ManyToOne
    @JoinColumn(name = "cod_persona", nullable = false)
    private Persona persona;

    private String descripcion;

    private String observacion;
    
}
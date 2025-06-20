package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "gestiona", name = "periodos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Periodo {
    
    @Id
    @Column(name = "nro_periodo")
    private Long nroPeriodo;

    @Column(name = "cod_periodo", unique = true)
    private String codPeriodo;

    @Column(name = "abreviatura")
    private String abreviatura;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
    
}
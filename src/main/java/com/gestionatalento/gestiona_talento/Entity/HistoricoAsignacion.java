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
@Table(schema = "gestiona", name = "historico_asignacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoAsignacion {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "dependencia")
    private String dependencia;

    @Column(name = "departamento_division")
    private String departamentoDivision;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario_anterior")
    private Double salarioAnterior;

    @Column(name = "diferencia")
    private Double diferencia;

    @Column(name = "salario_actual")
    private Double salarioActual;
    
    @Column(name = "nro_resolucion")
    private String nro_resolucion;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}
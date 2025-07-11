package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "gestiona", name = "empleados", uniqueConstraints = @UniqueConstraint(columnNames = {"cod_persona", "fec_acto_administrativo"}))

public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // `Empleado` tiene su propia PK
    @Column(name = "cod_empleado")
    private Long codEmpleado;

    @ManyToOne
    @JoinColumn(name = "cod_persona", referencedColumnName = "cod_persona")  // Clave foránea a `Persona`
    private Persona persona;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "fec_acto_administrativo")
    private LocalDate fecActoAdministrativo;
    
    @Column(name = "fec_ingreso")
    private LocalDate fecIngreso;
    
    @Column(name = "fec_egreso")
    private LocalDate fecEgreso;
    
    @Column(name = "observacion")
    private String observacion;
    
    @Column(name = "asignacion")
    private Double asignacion;
    
    @Column(name = "nro_resolucion")
    private String nroResolucion;
    
    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;
    
    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @ManyToOne
    @JoinColumn(name = "cod_cargo", referencedColumnName = "cod_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "cod_sede", referencedColumnName = "cod_sede")
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "cod_situacion_laboral", referencedColumnName = "cod_situacion_laboral")
    private SituacionLaboral situacionLaboral;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
    
}

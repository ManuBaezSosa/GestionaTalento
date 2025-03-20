package com.gestionatalento.gestiona_talento.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
public class Empleado {

    @Id
    @Column(name = "cod_persona")
    private Long codPersona;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "cod_persona")
    private Persona persona;
    
    private String estado;
    
    private String acto;
    
    @Column(name = "fec_acto")
    private LocalDate fecActo;
    
    @Column(name = "fec_inicio")
    private LocalDate fecInicio;
    
    @Column(name = "fec_egreso")
    private LocalDate fecEgreso;
    
    private String observacion;
    
    @Column(name = "cod_cargo")
    private String codCargo;
    
    @Column(name = "cod_sede")
    private String codSede;
    
    @Column(name = "cod_sit_laboral")
    private String codSitLaboral;
    
    private Double asignacion;
    
    @Column(name = "nro_resolucion")
    private String nroResolucion;
    
    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;
    
    @Column(name = "hora_salida")
    private LocalTime horaSalida;


    
}

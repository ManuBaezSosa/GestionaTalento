package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    @Column(name = "desc_cargo")
    private String cargo;
    
    @Column(name = "desc_sede")
    private String descSede;
    
    @Column(name = "desc_sit_laboral")
    private String descSitLaboral;
    
    private Double asignacion;
    
    @Column(name = "nro_resolucion")
    private String nroResolucion;
    
    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;
    
    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "pasante")
    private boolean pasante;

    @ManyToOne
    @JoinColumn(name = "cod_area", referencedColumnName = "cod_area")
    private Area area;

    
}

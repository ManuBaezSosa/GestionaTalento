package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gestionatalento.gestiona_talento.Entity.Cargo;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Entity.Sede;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDto {
    private Long codEmpleado;
    private Long codPersona;
    private String estado;
    private LocalDate fecActoAdministrativo;
    private LocalDate fecIngreso;
    private LocalDate fecEgreso;
    private String observacion;
    private Double asignacion;
    private String nroResolucion;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Cargo cargo;
    private Sede sede;
    private SituacionLaboral situacionLaboral;

}

package com.gestionatalento.gestiona_talento.Request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gestionatalento.gestiona_talento.Entity.Cargo;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Entity.Sede;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* ESTA CLASE SE UTILIZA PARA DAR DE ALTA A LOS EMPLEADOS*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {
    private Persona persona;
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

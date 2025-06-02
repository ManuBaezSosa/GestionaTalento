package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestionatalento.gestiona_talento.Entity.Cargo;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Entity.Sede;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDto {
    private Long codEmpleado;
    private Persona persona;
    private String estado;
    private LocalDate fecActoAdministrativo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecIngreso;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecEgreso;
    private String observacion;
    @NotNull(message = "La asignación no puede ser nula")  // Validación de que no sea nulo
    @Min(value = 1, message = "La asignación debe ser mayor que 0")  // Validación de que sea mayor a 0
    private Double asignacion;
    private String nroResolucion;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Cargo cargo;
    private Sede sede;
    private SituacionLaboral situacionLaboral;

    /* Agregado para baja de empleados */
    private String comentario;
}

package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDto {
    private Long codPersona;
    private String pais;
    private String cargo;
    private LocalDate fechaInicio;
    private LocalDate fechaActo;
    private Double asignacion;
    private String sede;
    private String situacionLaboral;
    private String nroResolucion;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private String estado;


}

package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasantesDto {
    private Long codPersona;
    private Double asignacion;
    private String nombre;
    private String apellido;
    private String nroDocumento;
    private LocalDate fechaInicio;
    private LocalDate fechaEgreso;
}

package com.gestionatalento.gestiona_talento.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindEmpleadoResponse {
    private String nombreCompleto;
    private String area;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private String antiguedad;
    private String descripcion;
}

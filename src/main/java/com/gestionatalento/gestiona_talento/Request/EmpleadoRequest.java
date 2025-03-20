package com.gestionatalento.gestiona_talento.Request;

import com.gestionatalento.gestiona_talento.Entities.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {
    private Long id_persona;
    private Empleado empleado;
}

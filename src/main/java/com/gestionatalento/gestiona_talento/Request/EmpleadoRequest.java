package com.gestionatalento.gestiona_talento.Request;

import com.gestionatalento.gestiona_talento.Entity.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* ESTA CLASE SE UTILIZA PARA DAR DE ALTA A LOS EMPLEADOS*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {
    private Long id_persona;
    private Empleado empleado;
}

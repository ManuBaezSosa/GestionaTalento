package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.GradoSalarial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanillaSalarialDetDto {

    private Long nroPlanilla;
    private Empleado empleado;
    private GradoSalarial gradoSalarial;
    private Double asignacion;
    
}

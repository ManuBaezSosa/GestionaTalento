package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.FuenteFinanciamiento;
import com.gestionatalento.gestiona_talento.Entity.GradoSalarial;
import com.gestionatalento.gestiona_talento.Entity.ObjetoGasto;
import com.gestionatalento.gestiona_talento.Entity.Periodo;
import com.gestionatalento.gestiona_talento.Entity.Presupuesto;
import com.gestionatalento.gestiona_talento.Entity.Programa;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;
import com.gestionatalento.gestiona_talento.Entity.Subprograma;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

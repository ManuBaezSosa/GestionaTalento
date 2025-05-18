package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.FuenteFinanciamiento;
import com.gestionatalento.gestiona_talento.Entity.ObjetoGasto;
import com.gestionatalento.gestiona_talento.Entity.Presupuesto;
import com.gestionatalento.gestiona_talento.Entity.Programa;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;
import com.gestionatalento.gestiona_talento.Entity.Subprograma;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametroSalarialDto {
    private Empleado empleado;
    private FuenteFinanciamiento fuenteFinanciamiento;
    private Presupuesto presupuesto;
    private Programa programa;
    private SituacionLaboral situacionLaboral;
    private ObjetoGasto objetoGasto;
    private Subprograma subPrograma;
}

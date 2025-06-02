package com.gestionatalento.gestiona_talento.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanillaSalarialInfDto {

    private PlanillaSalarialCabDto cabecera;
    private List<PlanillaSalarialDetDto> detalle;
    
}

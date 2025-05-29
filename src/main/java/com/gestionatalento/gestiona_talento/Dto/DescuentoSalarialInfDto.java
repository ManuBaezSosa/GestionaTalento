package com.gestionatalento.gestiona_talento.Dto;

import java.util.List;

import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoSalarialInfDto {

    private DescuentoSalarialCabDto cabecera;
    private List<DescuentoSalarial> detalle;
    
}

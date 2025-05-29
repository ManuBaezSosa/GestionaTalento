package com.gestionatalento.gestiona_talento.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovedadDto {
    private String mes;
    private int cantidadAltas;
    private int cantidadBajas;
}

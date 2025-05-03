package com.gestionatalento.gestiona_talento.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDto {
    private Long codDireccion;
    private String descripcion;
    private String estado;
}

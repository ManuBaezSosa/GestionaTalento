package com.gestionatalento.gestiona_talento.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEventoDto {
    private Long codTipEvento;
    private String descripcion;
    private String activo;
}

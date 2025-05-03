package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Departamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDto {
    private Long codCargo;
    private String descripcion;
    private String estado;
    private Departamento departamento;
}

package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraExtraDto {
    private Empleado empleado;
    private String codPeriodo;
    private int horaExtra;
    private Double monto;
    private String observacion;
    private String exoneraEntrada;
}

package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Periodo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraExtraDto {
    private Empleado empleado;
    private Periodo periodo;
    private int horaExtra;
    private Double monto;
    private String observacion;
    private String exoneraEntrada;
}

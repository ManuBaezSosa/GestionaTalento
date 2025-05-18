package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Periodo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoSalarialDto {
    private Empleado empleado;
    private Periodo periodo;
    private int entradaTardia;
    private int salidaAnticipada;
    private int ausencia;
    private Double monto;
    private String observacion;
}

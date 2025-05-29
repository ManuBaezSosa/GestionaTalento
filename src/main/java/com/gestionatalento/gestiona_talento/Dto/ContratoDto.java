package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;

import com.gestionatalento.gestiona_talento.Entity.Periodo;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDto {
    private Long nroContrato;
    private Periodo periodo;
    private Persona persona;
    private String nroDocumento;
    private String nombres;
    private String apellidos;
    private Double asignacion;
    private String montoLetras;
    private String estado;
    private Date fecDesde;
    private Date fecHasta;
    private SituacionLaboral situacionLaboral;
    private String nomFirmante1;
    private String nomFirmante2;
    private String observacion;
}

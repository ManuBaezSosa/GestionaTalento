package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;
import java.time.LocalTime;

import com.gestionatalento.gestiona_talento.Entity.TipoEvento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDto {
    private Long nroEvento;
    private Date fecha;
    private String descripcion;
    private TipoEvento tipoEvento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private String vigente;
}

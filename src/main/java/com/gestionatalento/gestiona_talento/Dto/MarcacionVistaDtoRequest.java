package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.gestionatalento.gestiona_talento.Entity.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionVistaDtoRequest {
    private String nroDocumento;
    private Date fecDesde;
    private Date fecHasta;
}

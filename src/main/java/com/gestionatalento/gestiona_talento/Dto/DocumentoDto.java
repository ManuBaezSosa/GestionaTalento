package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;


import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Entity.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDto {
    private Long nroDocumento;
    private Persona persona;
    private TipoDocumento tipoDocumento;
    private String nomArchivo;
    private String tipArchivo;
    private Long tamArchivo;
    private String estado;
    private Date fecDocumento;
    private String observacion;
}

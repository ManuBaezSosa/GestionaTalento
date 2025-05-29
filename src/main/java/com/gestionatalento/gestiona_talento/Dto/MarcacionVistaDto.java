package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionVistaDto {
    private String nroDocumento;
    private Date fecha;
    private String entrada;
    private String salida;
}

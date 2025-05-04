package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;
import java.util.List;

import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Entity.TipoJustificativo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustificativoDto {
    private Long nroJustificativo;
    private String descripcion;
    private TipoJustificativo tipoJustificativo;
    private String estado;
    private Persona persona;
    private List<Date> fechaJustificativo;
}

package com.gestionatalento.gestiona_talento.Dto;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestionatalento.gestiona_talento.Entity.EstadoCivil;
import com.gestionatalento.gestiona_talento.Entity.Pais;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    private Long codPersona;
    @NotNull(message = "El numero de documento es obligatorio")
    private String nroDocumento;
    private String nroRuc;
    @NotNull(message = "Los nombres son obligatorios")
    private String nombres;
    @NotNull(message = "Los apellidos son obligatorios")
    private String apellidos;
    private String codNivelEstudio;
    private Pais pais;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecNacimiento;
    private String lugarNacimiento;
    private String poseeDiscapacidad;
    private String descripcionDiscapacidad;
    private String rutaFoto;
    private EstadoCivil estadoCivil;
}

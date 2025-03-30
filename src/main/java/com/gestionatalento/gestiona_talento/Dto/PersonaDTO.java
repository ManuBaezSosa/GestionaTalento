package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDate;

import com.gestionatalento.gestiona_talento.Entity.EstadoCivil;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Long codPersona;
    private String nroDocumento;
    private String nroRuc;
    private String nombres;
    private String apellidos;
    private String codNivelEstudio;
    private Long codPaisNacimiento;
    private LocalDate fecNacimiento;
    private String lugarNacimiento;
    private String poseeDiscapacidad;
    private String descripcionDiscapacidad;
    private String rutaFoto;
    private EstadoCivil estadoCivil;
}

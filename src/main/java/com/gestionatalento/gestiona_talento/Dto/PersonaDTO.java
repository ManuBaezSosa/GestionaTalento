package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Long codPersona;
    private String nroDocumento;
    private String lugarNacimiento;
    private String nombre;
    private String apellido;
    private String ruc;
    private String paisNacimineto;
    private LocalDate fechaNacimiento;
    private String formacionAcademica;
    private Boolean poseeTitulo;
    private Boolean poseeDispacidad;
    private String descripcionDisca;
    private String estadoCivil;
    private String fotoPersona;
}

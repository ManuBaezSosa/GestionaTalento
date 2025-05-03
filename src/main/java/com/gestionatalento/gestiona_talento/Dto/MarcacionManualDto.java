package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gestionatalento.gestiona_talento.Entity.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionManualDto {
    private Long nroMarcacion;
    private Persona persona;
    private List<LocalDateTime> marcacion;
}

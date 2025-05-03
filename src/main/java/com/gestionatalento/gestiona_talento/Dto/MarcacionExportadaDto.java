package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDateTime;

import com.gestionatalento.gestiona_talento.Entity.UsuarioExportado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionExportadaDto {
    private UsuarioExportado usuarioExportado;
    private LocalDateTime fecMarcacion;
}

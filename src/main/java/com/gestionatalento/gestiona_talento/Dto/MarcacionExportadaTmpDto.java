package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDateTime;

import com.gestionatalento.gestiona_talento.Entity.UsuarioExportadoTmp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionExportadaTmpDto {
    private UsuarioExportadoTmp usuarioExportadoTmp;
    private LocalDateTime fecMarcacion;
}

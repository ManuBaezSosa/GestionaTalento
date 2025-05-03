package com.gestionatalento.gestiona_talento.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioExportadoTmpDto {
    private Long codUsuario;
    private String nroDocumento;
}

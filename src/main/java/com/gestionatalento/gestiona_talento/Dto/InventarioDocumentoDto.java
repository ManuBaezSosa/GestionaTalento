package com.gestionatalento.gestiona_talento.Dto;

import java.time.LocalDateTime;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Periodo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDocumentoDto {
    
    private Empleado empleado;
    private Periodo periodo;
    private String estado;
    private LocalDateTime fecha;
    private String comentario;

}

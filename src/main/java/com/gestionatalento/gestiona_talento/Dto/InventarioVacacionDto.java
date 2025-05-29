package com.gestionatalento.gestiona_talento.Dto;

import com.gestionatalento.gestiona_talento.Entity.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioVacacionDto {

    private Long nroInventario;
    private Empleado empleado;
    private int cantidadGenerada;
    private int cantidadUtilizado;
    private int cantidadReservado;
    private String comentario;
    
}

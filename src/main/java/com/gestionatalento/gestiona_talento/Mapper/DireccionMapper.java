package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.DireccionDto;
import com.gestionatalento.gestiona_talento.Entity.Direccion;

@Mapper
public interface DireccionMapper {

    public static Direccion setDireccion(DireccionDto direccionDto){
        Direccion direccion = new Direccion();
        direccion.setDescripcion(direccionDto.getDescripcion());
        direccion.setEstado("A");

        return direccion;
    }
    public static Direccion setActualizarDireccion(DireccionDto direccionDto) {
        Direccion direccion = new Direccion();
        direccion.setCodDireccion(direccionDto.getCodDireccion());
        direccion.setDescripcion(direccionDto.getDescripcion());
        direccion.setEstado(direccionDto.getEstado());

        return direccion;
    }
    
}

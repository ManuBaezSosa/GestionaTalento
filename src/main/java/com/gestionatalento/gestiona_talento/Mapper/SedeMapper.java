package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.SedeDto;
import com.gestionatalento.gestiona_talento.Entity.Sede;

@Mapper
public interface SedeMapper {

    public static Sede setSede(SedeDto sedeDto){
        Sede sede = new Sede();
        sede.setDescripcion(sedeDto.getDescripcion());
        sede.setEstado("A");

        return sede;
    }
    public static Sede setActualizarSede(SedeDto sedeDto) {
        Sede sede = new Sede();
        sede.setCodSede(sedeDto.getCodSede());
        sede.setDescripcion(sedeDto.getDescripcion());
        sede.setEstado(sedeDto.getEstado());

        return sede;
    }
    
}

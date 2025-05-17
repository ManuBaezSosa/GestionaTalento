package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.TelefonoDto;
import com.gestionatalento.gestiona_talento.Entity.Telefono;

@Mapper
public interface TelefonoMapper {

    public static Telefono setTelefono(TelefonoDto telefonoDto){
        Telefono telefono = new Telefono();
        telefono.setPersona(telefonoDto.getPersona());
        telefono.setNumeroTelefono(telefonoDto.getNumeroTelefono());
        telefono.setObservacion(telefonoDto.getObservacion());

        return telefono;
    }
    public static Telefono setActualizarTelefono(TelefonoDto telefonoDto) {
        Telefono telefono = new Telefono();
        telefono.setCodTelefono(telefonoDto.getCodTelefono());
        telefono.setPersona(telefonoDto.getPersona());
        telefono.setNumeroTelefono(telefonoDto.getNumeroTelefono());
        telefono.setObservacion(telefonoDto.getObservacion());

        return telefono;
    }
    
}

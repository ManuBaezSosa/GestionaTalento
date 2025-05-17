package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.CorreoDto;
import com.gestionatalento.gestiona_talento.Entity.Correo;

@Mapper
public interface CorreoMapper {

    public static Correo setCorreo(CorreoDto correoDto){
        Correo correo = new Correo();
        correo.setPersona(correoDto.getPersona());
        correo.setCorreo(correoDto.getCorreo());
        correo.setObservacion(correoDto.getObservacion());

        return correo;
    }
    public static Correo setActualizarCorreo(CorreoDto correoDto) {
        Correo correo = new Correo();
        correo.setCodCorreo(correoDto.getCodCorreo());
        correo.setPersona(correoDto.getPersona());
        correo.setCorreo(correoDto.getCorreo());
        correo.setObservacion(correoDto.getObservacion());

        return correo;
    }
    
}

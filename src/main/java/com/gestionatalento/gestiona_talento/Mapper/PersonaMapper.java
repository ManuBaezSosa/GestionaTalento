package com.gestionatalento.gestiona_talento.Mapper;

import java.util.Optional;
import org.mapstruct.Mapper;
import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.Persona;

@Mapper
public interface PersonaMapper {

    public static Persona setPersona(PersonaDto personaDto){
        Persona persona = new Persona();
        persona.setNroDocumento(personaDto.getNroDocumento());
        persona.setNroRuc(personaDto.getNroRuc());
        persona.setNombres(personaDto.getNombres());
        persona.setApellidos(personaDto.getApellidos());
        persona.setCodNivelEstudio(personaDto.getCodNivelEstudio());
        persona.setCodPaisNacimiento(personaDto.getCodPaisNacimiento());
        persona.setFecNacimiento(personaDto.getFecNacimiento());
        persona.setLugarNacimiento(personaDto.getLugarNacimiento());
        persona.setPoseeDiscapacidad(personaDto.getPoseeDiscapacidad());
        persona.setDescripcionDiscapacidad(personaDto.getDescripcionDiscapacidad());
        persona.setRutaFoto(personaDto.getRutaFoto());
        return persona;
    }
    public static Persona setActualizarPersona(Persona persona, PersonaDto personaDto) {
        Optional.ofNullable(personaDto.getNroDocumento()).ifPresent(persona::setNroDocumento);
        Optional.ofNullable(personaDto.getNroRuc()).ifPresent(persona::setNroRuc);
        Optional.ofNullable(personaDto.getNombres()).ifPresent(persona::setNombres);
        Optional.ofNullable(personaDto.getApellidos()).ifPresent(persona::setApellidos);
        Optional.ofNullable(personaDto.getCodNivelEstudio()).ifPresent(persona::setCodNivelEstudio);
        Optional.ofNullable(personaDto.getCodPaisNacimiento()).ifPresent(persona::setCodPaisNacimiento);
        Optional.ofNullable(personaDto.getFecNacimiento()).ifPresent(persona::setFecNacimiento);
        Optional.ofNullable(personaDto.getLugarNacimiento()).ifPresent(persona::setLugarNacimiento);
        Optional.ofNullable(personaDto.getPoseeDiscapacidad()).ifPresent(persona::setPoseeDiscapacidad);
        Optional.ofNullable(personaDto.getDescripcionDiscapacidad()).ifPresent(persona::setDescripcionDiscapacidad);
        Optional.ofNullable(personaDto.getRutaFoto()).ifPresent(persona::setRutaFoto);
        return persona;
    }
    
}

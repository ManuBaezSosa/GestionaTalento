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
        Persona personaActualizada = new Persona();
        personaActualizada.setCodPersona(persona.getCodPersona());
        personaActualizada.setNroDocumento(persona.getNroDocumento());
        personaActualizada.setNroRuc(persona.getNroRuc());
        personaActualizada.setNombres(persona.getNombres());
        personaActualizada.setApellidos(persona.getApellidos());
        personaActualizada.setCodNivelEstudio(persona.getCodNivelEstudio());
        personaActualizada.setCodPaisNacimiento(persona.getCodPaisNacimiento());
        personaActualizada.setFecNacimiento(persona.getFecNacimiento());
        personaActualizada.setLugarNacimiento(persona.getLugarNacimiento());
        personaActualizada.setPoseeDiscapacidad(persona.getPoseeDiscapacidad());
        personaActualizada.setDescripcionDiscapacidad(persona.getDescripcionDiscapacidad());
        personaActualizada.setRutaFoto(persona.getRutaFoto());

        Optional.ofNullable(personaDto.getNroDocumento()).ifPresent(personaActualizada::setNroDocumento);
        Optional.ofNullable(personaDto.getNroRuc()).ifPresent(personaActualizada::setNroRuc);
        Optional.ofNullable(personaDto.getNombres()).ifPresent(personaActualizada::setNombres);
        Optional.ofNullable(personaDto.getApellidos()).ifPresent(personaActualizada::setApellidos);
        Optional.ofNullable(personaDto.getCodNivelEstudio()).ifPresent(personaActualizada::setCodNivelEstudio);
        Optional.ofNullable(personaDto.getCodPaisNacimiento()).ifPresent(personaActualizada::setCodPaisNacimiento);
        Optional.ofNullable(personaDto.getFecNacimiento()).ifPresent(personaActualizada::setFecNacimiento);
        Optional.ofNullable(personaDto.getLugarNacimiento()).ifPresent(personaActualizada::setLugarNacimiento);
        Optional.ofNullable(personaDto.getPoseeDiscapacidad()).ifPresent(personaActualizada::setPoseeDiscapacidad);
        Optional.ofNullable(personaDto.getDescripcionDiscapacidad()).ifPresent(personaActualizada::setDescripcionDiscapacidad);
        Optional.ofNullable(personaDto.getRutaFoto()).ifPresent(personaActualizada::setRutaFoto);
        return personaActualizada;
    }
    
}

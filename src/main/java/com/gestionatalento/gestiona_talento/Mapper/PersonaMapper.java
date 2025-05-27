package com.gestionatalento.gestiona_talento.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.EstadoCivil;
import com.gestionatalento.gestiona_talento.Entity.Pais;
import com.gestionatalento.gestiona_talento.Entity.Persona;

import java.nio.file.Path;

@Mapper
public interface PersonaMapper {
    
    public static Persona setPersona(PersonaDto personaDto, MultipartFile fotoString, String storagePath) throws IOException{
        Persona persona = new Persona();
        persona.setNroDocumento(personaDto.getNroDocumento());
        persona.setNroRuc(personaDto.getNroRuc());
        persona.setNombres(personaDto.getNombres());
        persona.setApellidos(personaDto.getApellidos());
        persona.setCodNivelEstudio(personaDto.getCodNivelEstudio());

        Pais pais = new Pais();
        pais.setCodPais(personaDto.getPais().getCodPais());
        persona.setPais(pais);

        EstadoCivil estadoCivil = new EstadoCivil();
        estadoCivil.setCodEstadoCivil(personaDto.getEstadoCivil().getCodEstadoCivil());
        persona.setEstadoCivil(estadoCivil);

        persona.setFecNacimiento(personaDto.getFecNacimiento());
        persona.setLugarNacimiento(personaDto.getLugarNacimiento());
        persona.setPoseeDiscapacidad(personaDto.getPoseeDiscapacidad());
        persona.setDescripcionDiscapacidad(personaDto.getDescripcionDiscapacidad());
        persona.setRutaFoto(fotoString.getOriginalFilename());

        String filename = fotoString.getOriginalFilename();
        Path filepath = Paths.get(storagePath, filename);
        Files.copy(fotoString.getInputStream(), filepath);

        return persona;
    }
    public static Persona setActualizarPersona(Persona persona, PersonaDto personaDto, MultipartFile fotoString, String storagePath) throws IOException{
        Persona personaActualizada = new Persona();
        personaActualizada.setCodPersona(persona.getCodPersona());
        personaActualizada.setNroDocumento(persona.getNroDocumento());
        personaActualizada.setNroRuc(persona.getNroRuc());
        personaActualizada.setNombres(persona.getNombres());
        personaActualizada.setApellidos(persona.getApellidos());
        personaActualizada.setCodNivelEstudio(persona.getCodNivelEstudio());

        Pais pais = new Pais();
        pais.setCodPais(persona.getPais().getCodPais());
        personaActualizada.setPais(pais);

        EstadoCivil estadoCivil = new EstadoCivil();
        estadoCivil.setCodEstadoCivil(personaDto.getEstadoCivil().getCodEstadoCivil());
        personaActualizada.setEstadoCivil(estadoCivil);

        personaActualizada.setFecNacimiento(persona.getFecNacimiento());
        personaActualizada.setLugarNacimiento(persona.getLugarNacimiento());
        personaActualizada.setPoseeDiscapacidad(persona.getPoseeDiscapacidad());
        personaActualizada.setDescripcionDiscapacidad(persona.getDescripcionDiscapacidad());
        personaActualizada.setRutaFoto(fotoString.getOriginalFilename());

        String filename = fotoString.getOriginalFilename();
        Path filepath = Paths.get(storagePath, filename);
        Files.copy(fotoString.getInputStream(), filepath);

        return personaActualizada;
    }
    
}

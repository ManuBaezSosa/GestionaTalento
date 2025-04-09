
package com.gestionatalento.gestiona_talento.Service.Persona;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;


@Service
public interface PersonaService {
    List<Persona> findAllPersonas();
    GenericResponse crearPersona(PersonaDto personaDto);
    Object buscarPersona(PersonaRequest request);  
    GenericResponse actualizarPersona(PersonaDto personaDTO);
}
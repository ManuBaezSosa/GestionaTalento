
package com.gestionatalento.gestiona_talento.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;


@Service
public interface PersonaService {
    List<Persona> findAllPersonas();
    GenericResponse crearPersona(PersonaDto personaDto, MultipartFile foto);
    Object buscarPersona(PersonaRequest request);  
    GenericResponse actualizarPersona(PersonaDto personaDTO, MultipartFile foto);
}
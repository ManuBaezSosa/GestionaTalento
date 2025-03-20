
package com.gestionatalento.gestiona_talento.Service.Persona;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Persona;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;


@Service
public interface PersonaService {
    List<Persona> findAllPersonas();
    Persona crearPersona(Persona persona);
    Persona elimminarPersonaId(Long id);
    Object buscarPersona(PersonaRequest request);  
}
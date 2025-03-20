package com.gestionatalento.gestiona_talento.Service.Persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Persona;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Object buscarPersona(PersonaRequest request) {
        if ("nroDocumento".equals(request.getTipoBusqueda())) {
            // Búsqueda por número de documento (único)
            return personaRepository.findByNroDocumento(request.getValor())
                .orElseThrow(() -> new RuntimeException("La persona con el documento proporcionado no existe"));
        }
    
        if ("nombre".equals(request.getTipoBusqueda())) {
            // Búsqueda por nombre (pueden ser varias personas)
            List<Persona> personas = personaRepository.findByNombre(request.getValor());
            if (personas.isEmpty()) {
                throw new RuntimeException("La persona con el nombre proporcionado no existe");
            }
            return personas;
        }
    
        throw new RuntimeException("Debes proporcionar el nroDocumento o el nombre");
    }

    @Override
    public Persona elimminarPersonaId(Long id) {
        Persona personaEliminada = personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La persona con el ID " + id + " no existe"));
        
        personaRepository.delete(personaEliminada);
        return personaEliminada;
    }



    
}

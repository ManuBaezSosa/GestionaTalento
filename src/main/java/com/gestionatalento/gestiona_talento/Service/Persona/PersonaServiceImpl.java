package com.gestionatalento.gestiona_talento.Service.Persona;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.PersonaDTO;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Persona;
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
        
        validarCampo(persona);
        return personaRepository.save(persona);
    }

    @Override
    public Object buscarPersona(PersonaRequest request) {
        if ("nroDocumento".equals(request.getTipoBusqueda())) {
            // Búsqueda por número de documento (único)
            return personaRepository.findByNroDocumento(request.getValor())
                .orElseThrow(() -> new RuntimeException("La persona con el documento proporcionado no existe"));
        }
    
        if ("nombres".equals(request.getTipoBusqueda())) {
            // Búsqueda por nombre (pueden ser varias personas)
            List<Persona> personas = personaRepository.findByNombres(request.getValor());
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


    private void validarCampo(Persona personaCampo){
           // Validar campos obligatorios
        if (personaCampo.getNroDocumento() == null || personaCampo.getNroDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El numero de documento es obligatorio");
        }
        if (personaCampo.getNombres() == null || personaCampo.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("Los nombres son obligatorios");
        }
        if (personaCampo.getApellidos() == null || personaCampo.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }
    }

    @Override
    public Persona actualizarPersona(PersonaDTO personaDTO) {
        // Validar que el ID de la persona no sea nulo
        if (personaDTO.getCodPersona() == null) {
            throw new IllegalArgumentException("El ID de la persona no puede ser nulo");
        }

        // Buscar persona en la base de datos
        Persona personaDatosActuales = personaRepository.findById(personaDTO.getCodPersona())
                .orElseThrow(() -> new NoSuchElementException("La persona no fue hallada"));

        // Actualizar solo si los valores no son nulos
        Optional.ofNullable(personaDTO.getNroDocumento()).ifPresent(personaDatosActuales::setNroDocumento);
        Optional.ofNullable(personaDTO.getNroRuc()).ifPresent(personaDatosActuales::setNroRuc);
        Optional.ofNullable(personaDTO.getNombres()).ifPresent(personaDatosActuales::setNombres);
        Optional.ofNullable(personaDTO.getApellidos()).ifPresent(personaDatosActuales::setApellidos);
        Optional.ofNullable(personaDTO.getCodNivelEstudio()).ifPresent(personaDatosActuales::setCodNivelEstudio);
        Optional.ofNullable(personaDTO.getCodPaisNacimiento()).ifPresent(personaDatosActuales::setCodPaisNacimiento);
        Optional.ofNullable(personaDTO.getFecNacimiento()).ifPresent(personaDatosActuales::setFecNacimiento);
        Optional.ofNullable(personaDTO.getLugarNacimiento()).ifPresent(personaDatosActuales::setLugarNacimiento);
        Optional.ofNullable(personaDTO.getPoseeDiscapacidad()).ifPresent(personaDatosActuales::setPoseeDiscapacidad);
        Optional.ofNullable(personaDTO.getDescripcionDiscapacidad()).ifPresent(personaDatosActuales::setDescripcionDiscapacidad);
        Optional.ofNullable(personaDTO.getRutaFoto()).ifPresent(personaDatosActuales::setRutaFoto);
        Optional.ofNullable(personaDTO.getEstadoCivil()).ifPresent(personaDatosActuales::setEstadoCivil);


        // Guardar y devolver persona actualizada
        return personaRepository.save(personaDatosActuales);
    }


    
}

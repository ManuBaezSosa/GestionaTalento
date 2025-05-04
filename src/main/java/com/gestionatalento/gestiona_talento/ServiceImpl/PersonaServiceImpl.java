package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Mapper.PersonaMapper;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{
    private static final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public GenericResponse crearPersona(PersonaDto personaDto) {
        /* Buscamos a la persona */
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En PersonaDto, en el Request: {}", personaDto);
            Optional<Persona> personaResponse = personaRepository.findByNroDocumento(personaDto.getNroDocumento());
            if (!personaResponse.isPresent()) {
                Persona persona = PersonaMapper.setPersona(personaDto);
                logger.info("En PersonaMapper, en el Request: {}", persona);
                /* Guardamos la persona */
                persona = personaRepository.save(persona);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Persona creada exitosamente");
                genericResponse.setObjeto(persona);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("409");
                genericResponse.setMensaje("Ya existe una persona con el numero de documento ingresado.");
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
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
    public GenericResponse actualizarPersona(PersonaDto personaDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En PersonaDto, en el Request: {}", personaDto);
            Optional<Persona> personaResponse = personaRepository.findById(personaDto.getCodPersona());
            if (personaResponse.isPresent()) {
                Persona personaOriginal = personaResponse.get();
                logger.info("En personaOriginal, en el Request: {}", personaOriginal);

                /* Cargamos los datos del DTO al Empleado */
                Persona personaActualizada = new Persona();
                personaActualizada = PersonaMapper.setActualizarPersona(personaOriginal, personaDto);
                logger.info("En PersonaMapper, en el Request: {}", personaActualizada);
                if (!personaActualizada.getNroDocumento().equals(personaOriginal.getNroDocumento())) {
                    personaResponse = personaRepository.findByNroDocumento(personaActualizada.getNroDocumento());
                    if (personaResponse.isPresent()) {
                        genericResponse.setCodigoMensaje("409");
                        genericResponse.setMensaje("Ya existe una persona con el numero de documento ingresado");
                        genericResponse.setObjeto(null);
                        return genericResponse;
                    }
                }
                /* Guardar cambios */
                personaActualizada = personaRepository.save(personaActualizada);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Persona actualizada exitosamente");
                genericResponse.setObjeto(personaActualizada);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra la persona con el valor proporcionado. ID: " + personaDto.getCodPersona());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }   
    }
    /*
    public Persona actualizarPersona(PersonaDto personaDTO) {
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
    }*/


    
}

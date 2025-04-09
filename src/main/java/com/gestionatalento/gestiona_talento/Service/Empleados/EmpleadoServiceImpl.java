package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Mapper.EmpleadoMapper;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Response.FindEmpleadoResponse;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import jakarta.transaction.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    PersonaRepository personaRepository;

    /* Para que exista el empleado primero debe de existir la persona */
    @Override
    public GenericResponse crearEmpleado(EmpleadoDto empleadoDto) {
        /* Buscamos a la persona */
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En EmpleadoDto, en el Request: {}", empleadoDto);
            Optional<Persona> personaResponse = personaRepository.findById(empleadoDto.getPersona().getCodPersona());
            if (personaResponse.isPresent()) {
                Persona persona = personaResponse.get();
                Empleado empleadoExistente = empleadoRepository.findByCodPersonaEmpleadoActivo(persona.getCodPersona());
                if (empleadoExistente == null) {
                    /* Cargamos los datos del DTO al Empleado */
                    Empleado empleado = EmpleadoMapper.setEmpleado(empleadoDto, persona);
                    logger.info("En EmpleadoMapper, en el Request: {}", empleado);
                    /* Guardamos el empleado */
                    empleado = empleadoRepository.save(empleado);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Empleado dado de alta exitosamente");
                    genericResponse.setObjeto(empleado);
                    return genericResponse;
                }else{
                    genericResponse.setCodigoMensaje("409");
                    genericResponse.setMensaje("Ya existe un empleado activo con la persona ingresada");
                    genericResponse.setObjeto(null);
                    return genericResponse;
                }
                
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra una persona con el valor proporcionado. ID: " + empleadoDto.getPersona().getCodPersona());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarEmpleado(EmpleadoDto empleadoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En EmpleadoDto, en el Request: {}", empleadoDto);
            Empleado empleadoExistente = empleadoRepository.findByIdEmpleadoActivo(empleadoDto.getCodEmpleado());
            if (empleadoExistente != null) {
                /* Cargamos los datos del DTO al Empleado */
                Empleado empleado = EmpleadoMapper.setActualizarEmpleado(empleadoExistente, empleadoDto);
                logger.info("En EmpleadoMapper, en el Request: {}", empleado);
                /* Guardar cambios */
                empleado = empleadoRepository.save(empleado);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado actualizado exitosamente");
                genericResponse.setObjeto(empleado);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un empleado activo con el valor proporcionado. ID: " + empleadoDto.getCodEmpleado());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor " + e.getMessage());
            return genericResponse;
        }   
    }

    @Override
    public GenericResponse bajarEmpleado(EmpleadoDto request) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(request.getCodEmpleado());
            if (empleado != null) {
                /* Asignamos los valores por baja de empleado */
                empleado.setFecEgreso(request.getFecEgreso());
                empleado.setEstado("I");
                /* Enviamos el request */
                empleadoRepository.save(empleado);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado dado de baja correctamente con valor proporcionado. ID: " + request.getCodEmpleado());
                return genericResponse;
            } else {
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un empleado activo con el valor proporcionado. ID: " + request.getCodEmpleado());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor " + e.getMessage());
            return genericResponse;
        }
    }

    @Override
    public Object buscarEmpleado(PersonaRequest request) {
        // Validación del tipo de búsqueda
        if (request.getTipoBusqueda() == null || request.getValor() == null) {
            throw new IllegalArgumentException("Debes proporcionar el tipo de búsqueda y un valor");
        }

        switch (request.getTipoBusqueda()) {
            case "nroDocumento":
                return mapToFindEmpleadoResponse(
                    empleadoRepository.findByNroDocumento(request.getValor())
                    .orElseThrow(() -> new RuntimeException("La persona con el documento proporcionado no existe"))
                );

            case "nombres":
                List<Empleado> empleadoPersona = empleadoRepository.findByNombre(request.getValor());
                if (empleadoPersona.isEmpty()) {
                    throw new RuntimeException("La persona con el nombre proporcionado no existe");
                }
                return empleadoPersona.stream()
                    .map(this::mapToFindEmpleadoResponse)
                    .collect(Collectors.toList());

            default:
                throw new IllegalArgumentException("Tipo de búsqueda no válido. Use 'nroDocumento' o 'nombre'");
        }
    }

    private FindEmpleadoResponse mapToFindEmpleadoResponse(Empleado empleado) {
        FindEmpleadoResponse response = new FindEmpleadoResponse();
        
        // Mapeo de datos básicos
        response.setNombreCompleto(empleado.getPersona().getNombres() + " " + empleado.getPersona().getApellidos());
        response.setArea(empleado.getCargo() != null ? empleado.getCargo().getDescripcion() : "Sin área asignada");
        response.setFechaIngreso(empleado.getFecIngreso());
        response.setFechaEgreso(empleado.getFecEgreso());
        response.setAntiguedad(calcularAntiguedad(empleado.getFecIngreso()));
        response.setDescripcion(empleado.getObservacion());
    

        return response;
    }

    private String calcularAntiguedad(LocalDate fechaInicio){
         // Cálculo de antigüedad
        if (fechaInicio != null) {
            LocalDate fechaActual = LocalDate.now();
            
            // Calcular los años completos de antigüedad
            int años = Period.between(fechaInicio, fechaActual).getYears();
            
            // Calcular los meses completos de antigüedad
            int meses = Period.between(fechaInicio, fechaActual).getMonths();
            
            // Si la fecha de ingreso es posterior al mes actual, restamos un mes a la antigüedad
            if (fechaInicio.plusYears(años).plusMonths(meses).isAfter(fechaActual)) {
                meses--;
            }
            
            // Definir el formato de antigüedad
            String antiguedad = "";
            if (años > 0) {
                if(años == 1){
                    antiguedad += años + " año";
                }else{
                    antiguedad += años + " años";
                }
                
            }
            if (meses > 0) {
                if (!antiguedad.isEmpty()) antiguedad += " y ";
                if(meses == 1){
                    antiguedad += meses + " mes";
                }else{
                    antiguedad += meses + " meses";
                }
            }
            
            return antiguedad;
        }else {
            return "No posee antiguedad";
        }
    }

}

package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Request.EmpleadoRequest;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Response.FindEmpleadoResponse;

import jakarta.transaction.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    PersonaRepository personaRepository;

    //Para que exista el empleado primero debe de existir la persona 
    @Override
    public Empleado crearEmpleado(EmpleadoRequest request) {
         // Primero buscamos a la persona
         logger.info("Empleado: {}", request);
        Optional<Persona> personaOpt = personaRepository.findById(request.getPersona().getCodPersona());
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            

            // Configuramos el empleado
            Empleado empleadoPersona = new Empleado();

            empleadoPersona.setPersona(persona);
            empleadoPersona.setEstado(request.getEstado());
            empleadoPersona.setFecActoAdministrativo(request.getFecActoAdministrativo());
            empleadoPersona.setFecIngreso(request.getFecIngreso());
            empleadoPersona.setFecEgreso(request.getFecEgreso());
            empleadoPersona.setObservacion(request.getObservacion());
            empleadoPersona.setAsignacion(request.getAsignacion());
            empleadoPersona.setNroResolucion(request.getNroResolucion());
            empleadoPersona.setHoraEntrada(request.getHoraEntrada());
            empleadoPersona.setHoraSalida(request.getHoraSalida());
            empleadoPersona.setCargo(request.getCargo());
            empleadoPersona.setSede(request.getSede());
            empleadoPersona.setSituacionLaboral(request.getSituacionLaboral());
            
            return empleadoRepository.save(empleadoPersona);
        }
    
        throw new RuntimeException("No se encontró la persona con el ID proporcionado");

        
    }

    @Override
    public Empleado eliminarEmpleado(Long codPersona) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(codPersona);
        
        if (empleadoOptional.isPresent()) {
            empleadoRepository.deleteById(codPersona);
            Empleado empleadoEliminado = empleadoOptional.get();
            return empleadoEliminado;
        } else {
            throw new RuntimeException("No se encontró el Empleado con ID " + codPersona);
        }
    }

    @Override
    public List<Empleado> obtenerAllEmpleado() {
        List<Empleado> validacionEmpleados = empleadoRepository.findAll();;
        if(validacionEmpleados.isEmpty()){
            throw new RuntimeException("No hay empleados" );
        }
        return validacionEmpleados;
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

    @Override
    public Empleado actualizarEmpleado(EmpleadoDto empleadoDto) {
        if (empleadoDto.getCodEmpleado() == null) {
            throw new RuntimeException("El ID del empleado no puede ser nulo");
        }

        // Buscar empleado existente
        Empleado empleado = empleadoRepository.findById(empleadoDto.getCodEmpleado())
                .orElseThrow(() -> new RuntimeException("El Empleado no fue hallado"));


        // Actualizar datos solo si los valores no son nulos
        Optional.ofNullable(empleadoDto.getCargo()).ifPresent(empleado::setCargo);
        Optional.ofNullable(empleadoDto.getFecIngreso()).ifPresent(empleado::setFecIngreso);
        Optional.ofNullable(empleadoDto.getFecActoAdministrativo()).ifPresent(empleado::setFecActoAdministrativo);
        Optional.ofNullable(empleadoDto.getAsignacion()).ifPresent(empleado::setAsignacion);
        Optional.ofNullable(empleadoDto.getSituacionLaboral()).ifPresent(empleado::setSituacionLaboral);
        Optional.ofNullable(empleadoDto.getSede()).ifPresent(empleado::setSede);
        Optional.ofNullable(empleadoDto.getNroResolucion()).ifPresent(empleado::setNroResolucion);
        Optional.ofNullable(empleadoDto.getHoraEntrada()).ifPresent(empleado::setHoraEntrada);
        Optional.ofNullable(empleadoDto.getHoraSalida()).ifPresent(empleado::setHoraSalida);
        Optional.ofNullable(empleadoDto.getEstado()).ifPresent(empleado::setEstado);
    
        // Guardar cambios
        return empleadoRepository.save(empleado);
    }

    
}

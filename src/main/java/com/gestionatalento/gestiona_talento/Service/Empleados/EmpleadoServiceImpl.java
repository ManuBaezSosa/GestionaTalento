package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Dto.PasantesDto;
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

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    PersonaRepository personaRepository;

    //Para que exista el empleado primero debe de existir la persona 
    @Override
    public Empleado crearEmpleado(EmpleadoRequest request) {
         // Primero buscamos a la persona
        Optional<Persona> personaOpt = personaRepository.findById(request.getId_persona());
        
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            
            // Configuramos el empleado
            Empleado empleadoPersona = request.getEmpleado();
            
            // Con @MapsId, solo deberías necesitar establecer la persona
            // No establecer manualmente el codPersona
            empleadoPersona.setPersona(persona);
            
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

            case "nombre":
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
        response.setNombreCompleto(empleado.getPersona().getNombre() + " " + empleado.getPersona().getApellido());
        response.setArea(empleado.getArea() != null ? empleado.getArea().getDescripcion() : "Sin área asignada");
        response.setFechaIngreso(empleado.getFecInicio());
        response.setFechaEgreso(empleado.getFecEgreso());
        response.setAntiguedad(calcularAntiguedad(empleado.getFecInicio()));
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
        if (empleadoDto.getCodPersona() == null) {
            throw new RuntimeException("El ID del empleado no puede ser nulo");
        }

        // Buscar empleado existente
        Empleado empleado = empleadoRepository.findById(empleadoDto.getCodPersona())
                .orElseThrow(() -> new RuntimeException("El Empleado no fue hallado"));


        // Actualizar datos solo si los valores no son nulos
        Optional.ofNullable(empleadoDto.getCargo()).ifPresent(empleado::setCargo);
        Optional.ofNullable(empleadoDto.getFechaInicio()).ifPresent(empleado::setFecInicio);
        Optional.ofNullable(empleadoDto.getFechaActo()).ifPresent(empleado::setFecActo);
        Optional.ofNullable(empleadoDto.getAsignacion()).ifPresent(empleado::setAsignacion);
        Optional.ofNullable(empleadoDto.getSituacionLaboral()).ifPresent(empleado::setDescSitLaboral);
        Optional.ofNullable(empleadoDto.getSede()).ifPresent(empleado::setDescSede);
        Optional.ofNullable(empleadoDto.getNroResolucion()).ifPresent(empleado::setNroResolucion);
        Optional.ofNullable(empleadoDto.getHoraEntrada()).ifPresent(empleado::setHoraEntrada);
        Optional.ofNullable(empleadoDto.getHoraSalida()).ifPresent(empleado::setHoraSalida);
        Optional.ofNullable(empleadoDto.getCargo()).ifPresent(empleado::setCargo);
    

        // Guardar cambios
        return empleadoRepository.save(empleado);


    }

    @Override
    public Map<String, Object> findByAllPasante(Boolean pasante) {
        List<Empleado> empleados = empleadoRepository.findByPasante(pasante);
    
        if (empleados.isEmpty()) {
            throw new RuntimeException("No se encontraron empleados pasantes");
        }

        Empleado empleado = empleados.get(0); // Tomamos el primer resultado
        Map<String, Object> response = new HashMap<>();

        response.put("codPersona", empleado.getCodPersona());

        Map<String, Object> datos = new HashMap<>();
        datos.put("asignacion", empleado.getAsignacion());
        datos.put("nombre", empleado.getPersona().getNombre());
        datos.put("apellido", empleado.getPersona().getApellido());
        datos.put("nroDocumento", empleado.getPersona().getNroDocumento());
        datos.put("fechaInicio", empleado.getFecInicio());
        datos.put("fechaEgreso", empleado.getFecEgreso());

        response.put("datos", datos);
        
        return response;
    }

    
}

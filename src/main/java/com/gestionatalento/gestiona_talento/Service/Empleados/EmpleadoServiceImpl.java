package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Empleado;
import com.gestionatalento.gestiona_talento.Entities.Persona;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Request.EmpleadoRequest;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    PersonaRepository personaRepository;

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

    
    
}

package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface EmpleadoService {
    GenericResponse crearEmpleado(EmpleadoDto request);
    Object buscarEmpleado(PersonaRequest request);
    GenericResponse actualizarEmpleado(EmpleadoDto empleadoDto);
    GenericResponse bajarEmpleado(EmpleadoDto empleadoDto);
    
}

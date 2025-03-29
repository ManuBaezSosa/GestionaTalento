package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Dto.PasantesDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Request.EmpleadoRequest;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;

@Service
public interface EmpleadoService {
    Empleado crearEmpleado(EmpleadoRequest request);
    Empleado eliminarEmpleado(Long id);
    List<Empleado> obtenerAllEmpleado();
    Object buscarEmpleado(PersonaRequest request);
    Empleado actualizarEmpleado(EmpleadoDto empleadoDto);
    //me busca todos los pasantes
    Map<String, Object> findByAllPasante(Boolean pasante);
    
}

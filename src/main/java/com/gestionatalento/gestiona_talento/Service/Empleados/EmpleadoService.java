package com.gestionatalento.gestiona_talento.Service.Empleados;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Entities.Empleado;
import com.gestionatalento.gestiona_talento.Request.EmpleadoRequest;

@Service
public interface EmpleadoService {
    Empleado crearEmpleado(EmpleadoRequest request);
    Empleado eliminarEmpleado(Long id);
    List<Empleado> obtenerAllEmpleado();
    
}

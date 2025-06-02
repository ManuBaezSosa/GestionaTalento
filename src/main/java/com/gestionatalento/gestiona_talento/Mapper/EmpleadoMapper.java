package com.gestionatalento.gestiona_talento.Mapper;

import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Persona;

@Mapper
public interface EmpleadoMapper {

    public static Empleado setEmpleado(EmpleadoDto empleadoDto, Persona persona){
        Empleado empleado = new Empleado();
        empleado.setPersona(persona);
        empleado.setEstado(empleadoDto.getEstado());
        empleado.setFecActoAdministrativo(empleadoDto.getFecActoAdministrativo());
        empleado.setFecIngreso(empleadoDto.getFecIngreso());
        empleado.setFecEgreso(empleadoDto.getFecEgreso());
        empleado.setObservacion(empleadoDto.getObservacion());
        empleado.setAsignacion(empleadoDto.getAsignacion());
        empleado.setNroResolucion(empleadoDto.getNroResolucion());
        empleado.setHoraEntrada(empleadoDto.getHoraEntrada());
        empleado.setHoraSalida(empleadoDto.getHoraSalida());
        empleado.setCargo(empleadoDto.getCargo());
        empleado.setSede(empleadoDto.getSede());
        empleado.setSituacionLaboral(empleadoDto.getSituacionLaboral());
        return empleado;
    }
    public static Empleado setActualizarEmpleado(Empleado empleado, EmpleadoDto empleadoDto) {
        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setCodEmpleado(empleado.getCodEmpleado());
        empleadoActualizado.setPersona(empleado.getPersona());
        empleadoActualizado.setEstado(empleado.getEstado());
        empleadoActualizado.setSituacionLaboral(empleado.getSituacionLaboral());
        empleadoActualizado.setAsignacion(empleado.getAsignacion());

        empleadoActualizado.setFecActoAdministrativo(empleadoDto.getFecActoAdministrativo());
        empleadoActualizado.setFecIngreso(empleadoDto.getFecIngreso());
        empleadoActualizado.setObservacion(empleadoDto.getObservacion());
        empleadoActualizado.setNroResolucion(empleadoDto.getNroResolucion());
        empleadoActualizado.setHoraEntrada(empleadoDto.getHoraEntrada());
        empleadoActualizado.setHoraSalida(empleadoDto.getHoraEntrada());
        empleadoActualizado.setCargo(empleadoDto.getCargo());
        empleadoActualizado.setSede(empleadoDto.getSede());

        return empleadoActualizado;
    }
    
}

package com.gestionatalento.gestiona_talento.Mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
        Optional.ofNullable(empleado.getFecActoAdministrativo()).ifPresent(empleadoActualizado::setFecActoAdministrativo);
        Optional.ofNullable(empleado.getFecIngreso()).ifPresent(empleadoActualizado::setFecIngreso);
        Optional.ofNullable(empleado.getFecEgreso()).ifPresent(empleadoActualizado::setFecEgreso);
        Optional.ofNullable(empleado.getObservacion()).ifPresent(empleadoActualizado::setObservacion);
        Optional.ofNullable(empleado.getAsignacion()).ifPresent(empleadoActualizado::setAsignacion);
        Optional.ofNullable(empleado.getNroResolucion()).ifPresent(empleadoActualizado::setNroResolucion);
        Optional.ofNullable(empleado.getHoraEntrada()).ifPresent(empleadoActualizado::setHoraEntrada);
        Optional.ofNullable(empleado.getHoraSalida()).ifPresent(empleadoActualizado::setHoraSalida);
        Optional.ofNullable(empleado.getCargo()).ifPresent(empleadoActualizado::setCargo);
        Optional.ofNullable(empleado.getSede()).ifPresent(empleadoActualizado::setSede);

        return empleadoActualizado;
    }
    
}

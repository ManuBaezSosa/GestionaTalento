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
        empleadoActualizado.setFecActoAdministrativo(empleado.getFecActoAdministrativo());
        empleadoActualizado.setFecIngreso(empleado.getFecIngreso());
        empleadoActualizado.setFecEgreso(empleado.getFecEgreso());
        empleadoActualizado.setObservacion(empleado.getObservacion());
        empleadoActualizado.setAsignacion(empleado.getAsignacion());
        empleadoActualizado.setNroResolucion(empleado.getNroResolucion());
        empleadoActualizado.setHoraEntrada(empleado.getHoraEntrada());
        empleadoActualizado.setHoraSalida(empleado.getHoraSalida());
        empleadoActualizado.setCargo(empleado.getCargo());
        empleadoActualizado.setSede(empleado.getSede());
        empleadoActualizado.setSituacionLaboral(empleado.getSituacionLaboral());

        Optional.ofNullable(empleadoDto.getFecActoAdministrativo()).ifPresent(empleadoActualizado::setFecActoAdministrativo);
        Optional.ofNullable(empleadoDto.getFecIngreso()).ifPresent(empleadoActualizado::setFecIngreso);
        Optional.ofNullable(empleadoDto.getFecEgreso()).ifPresent(empleadoActualizado::setFecEgreso);
        Optional.ofNullable(empleadoDto.getObservacion()).ifPresent(empleadoActualizado::setObservacion);
        Optional.ofNullable(empleadoDto.getAsignacion()).ifPresent(empleadoActualizado::setAsignacion);
        Optional.ofNullable(empleadoDto.getNroResolucion()).ifPresent(empleadoActualizado::setNroResolucion);
        Optional.ofNullable(empleadoDto.getHoraEntrada()).ifPresent(empleadoActualizado::setHoraEntrada);
        Optional.ofNullable(empleadoDto.getHoraSalida()).ifPresent(empleadoActualizado::setHoraSalida);
        Optional.ofNullable(empleadoDto.getCargo()).ifPresent(empleadoActualizado::setCargo);
        Optional.ofNullable(empleadoDto.getSede()).ifPresent(empleadoActualizado::setSede);
        return empleadoActualizado;
    }
    
}

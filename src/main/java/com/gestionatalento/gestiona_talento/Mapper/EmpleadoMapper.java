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
        Optional.ofNullable(empleadoDto.getFecActoAdministrativo()).ifPresent(empleado::setFecActoAdministrativo);
        Optional.ofNullable(empleadoDto.getFecIngreso()).ifPresent(empleado::setFecIngreso);
        Optional.ofNullable(empleadoDto.getFecEgreso()).ifPresent(empleado::setFecEgreso);
        Optional.ofNullable(empleadoDto.getObservacion()).ifPresent(empleado::setObservacion);
        Optional.ofNullable(empleadoDto.getAsignacion()).ifPresent(empleado::setAsignacion);
        Optional.ofNullable(empleadoDto.getNroResolucion()).ifPresent(empleado::setNroResolucion);
        Optional.ofNullable(empleadoDto.getHoraEntrada()).ifPresent(empleado::setHoraEntrada);
        Optional.ofNullable(empleadoDto.getHoraSalida()).ifPresent(empleado::setHoraSalida);
        Optional.ofNullable(empleadoDto.getCargo()).ifPresent(empleado::setCargo);
        Optional.ofNullable(empleadoDto.getSede()).ifPresent(empleado::setSede);
        return empleado;
    }
    
}

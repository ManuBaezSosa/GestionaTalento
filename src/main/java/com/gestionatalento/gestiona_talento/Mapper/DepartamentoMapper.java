package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.DepartamentoDto;
import com.gestionatalento.gestiona_talento.Entity.Departamento;

@Mapper
public interface DepartamentoMapper {

    public static Departamento setDepartamento(DepartamentoDto departamentoDto){
        Departamento departamento = new Departamento();
        departamento.setDescripcion(departamentoDto.getDescripcion());
        departamento.setEstado("A");
        departamento.setDireccion(departamentoDto.getDireccion());

        return departamento;
    }
    public static Departamento setActualizarDepartamento(DepartamentoDto departamentoDto) {
        Departamento departamento = new Departamento();
        departamento.setCodDepartamento(departamentoDto.getCodDepartamento());
        departamento.setDescripcion(departamentoDto.getDescripcion());
        departamento.setEstado(departamentoDto.getEstado());
        departamento.setDireccion(departamentoDto.getDireccion());

        return departamento;
    }
    
}

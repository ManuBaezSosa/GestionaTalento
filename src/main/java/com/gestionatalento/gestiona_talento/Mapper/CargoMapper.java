package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.CargoDto;
import com.gestionatalento.gestiona_talento.Entity.Cargo;

@Mapper
public interface CargoMapper {

    public static Cargo setCargo(CargoDto cargoDto){
        Cargo cargo = new Cargo();
        cargo.setDescripcion(cargoDto.getDescripcion());
        cargo.setEstado("A");
        cargo.setDepartamento(cargoDto.getDepartamento());

        return cargo;
    }
    public static Cargo setActualizarCargo(CargoDto cargoDto) {
        Cargo cargo = new Cargo();
        cargo.setCodCargo(cargoDto.getCodCargo());
        cargo.setDescripcion(cargoDto.getDescripcion());
        cargo.setEstado(cargoDto.getEstado());
        cargo.setDepartamento(cargoDto.getDepartamento());

        return cargo;
    }
    
}

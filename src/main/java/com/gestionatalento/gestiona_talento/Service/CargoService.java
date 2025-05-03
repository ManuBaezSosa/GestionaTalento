package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.CargoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface CargoService {
    GenericResponse crearCargo(CargoDto cargoDto);
    GenericResponse actualizarCargo(CargoDto cargoDto);
}

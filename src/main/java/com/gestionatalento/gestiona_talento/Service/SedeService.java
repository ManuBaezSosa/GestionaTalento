package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.SedeDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface SedeService {
    GenericResponse crearSede(SedeDto sedeDto);
    GenericResponse actualizarSede(SedeDto sedeDto);
}

package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DireccionDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface DireccionService {
    GenericResponse crearDireccion(DireccionDto direccionDto);
    GenericResponse actualizarDireccion(DireccionDto direccionDto);
}

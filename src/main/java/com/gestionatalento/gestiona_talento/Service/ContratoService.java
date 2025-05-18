package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.ContratoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface ContratoService {
    GenericResponse crearContrato(ContratoDto contratoDto);
    GenericResponse actualizarContrato(ContratoDto contratoDto);
}

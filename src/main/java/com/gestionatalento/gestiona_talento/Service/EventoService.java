package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EventoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface EventoService {
    GenericResponse crearEvento(EventoDto eventoDto);
    GenericResponse actualizarEvento(EventoDto eventoDto);
}

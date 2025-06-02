package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface TipoEventoService {
    GenericResponse obtenerListaActiva();
}

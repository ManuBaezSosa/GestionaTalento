package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.CorreoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface CorreoService {
    GenericResponse crearCorreo(CorreoDto correoDto);
    GenericResponse actualizarCorreo(CorreoDto correoDto);
}

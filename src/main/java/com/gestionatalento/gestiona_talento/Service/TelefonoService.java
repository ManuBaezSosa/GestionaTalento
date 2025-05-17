package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.TelefonoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface TelefonoService {
    GenericResponse crearTelefono(TelefonoDto telefonoDto);
    GenericResponse actualizarTelefono(TelefonoDto telefonoDto);
}

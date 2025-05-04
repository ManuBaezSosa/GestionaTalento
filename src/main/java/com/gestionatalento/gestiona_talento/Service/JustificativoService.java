package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.JustificativoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface JustificativoService {
    GenericResponse crearJustificativo(JustificativoDto justificativoDto);
    GenericResponse actualizarJustificativo(JustificativoDto justificativoDto);
}

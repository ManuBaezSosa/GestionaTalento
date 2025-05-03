package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.MarcacionManualDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface MarcacionManualService {
    GenericResponse crearMarcacionManual(MarcacionManualDto marcacionManualDto);
    GenericResponse actualizarMarcacionManual(MarcacionManualDto marcacionManualDto);
}

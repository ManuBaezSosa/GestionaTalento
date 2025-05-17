package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface DocumentoService {
    GenericResponse crearDocumento(DocumentoDto documentoDto);
    GenericResponse actualizarDocumento(DocumentoDto documentoDto);
}

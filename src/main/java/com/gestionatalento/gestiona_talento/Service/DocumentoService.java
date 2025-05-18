package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface DocumentoService {
    GenericResponse crearDocumento(DocumentoDto documentoDto, MultipartFile archivo);
    GenericResponse actualizarDocumento(DocumentoDto documentoDto);
}

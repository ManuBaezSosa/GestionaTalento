package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.InventarioDocumentoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface InventarioDocumentoService {
    GenericResponse crearInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto);
    GenericResponse actualizarInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto);
    GenericResponse eliminarInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto);
}

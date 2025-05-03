package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface UsuarioExportadoTmpService {
    GenericResponse cargarUsuario(MultipartFile file);
}

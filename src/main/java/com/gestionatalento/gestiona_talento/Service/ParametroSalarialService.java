package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.ParametroSalarialDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface ParametroSalarialService {
    GenericResponse obtenerParametroSalarial(ParametroSalarialDto parametroSalarialDto);
}

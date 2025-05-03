package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface DescuentoSalarialService {
    GenericResponse crearDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto);
    GenericResponse actualizarDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto);
    GenericResponse calcularDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto);
}

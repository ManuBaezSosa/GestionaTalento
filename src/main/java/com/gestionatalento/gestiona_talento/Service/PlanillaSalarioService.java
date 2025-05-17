package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarioDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface PlanillaSalarioService {
    GenericResponse crearPlanillaSalario(PlanillaSalarioDto planillaSalarioDto);
    GenericResponse eliminarPlanillaSalario(PlanillaSalarioDto descuentoSalarialDto);
}

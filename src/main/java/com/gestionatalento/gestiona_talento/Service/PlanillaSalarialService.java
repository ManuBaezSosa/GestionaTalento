package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface PlanillaSalarialService {
    GenericResponse crearPlanillaSalarial(PlanillaSalarialDto planillaSalarialDto);
    GenericResponse actualizarPlanillaSalarial(PlanillaSalarialDto planillaSalarialDto);
    GenericResponse eliminarPlanillaSalarial(PlanillaSalarialDto planillaSalarialDto);
}

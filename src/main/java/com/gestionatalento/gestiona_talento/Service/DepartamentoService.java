package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DepartamentoDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface DepartamentoService {
    GenericResponse crearDepartamento(DepartamentoDto departamentoDto);
    GenericResponse actualizarDepartamento(DepartamentoDto departamentoDto);
}

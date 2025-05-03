package com.gestionatalento.gestiona_talento.Service;


import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.HoraExtraDto;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@Service
public interface HoraExtraService {
    GenericResponse crearHoraExtra(HoraExtraDto horaExtraDto);
    GenericResponse actualizarHoraExtra(HoraExtraDto horaExtraDto);
    GenericResponse eliminarHoraExtra(HoraExtraDto horaExtraDto);
    GenericResponse calcularHoraExtra(HoraExtraDto horaExtraDto);
}

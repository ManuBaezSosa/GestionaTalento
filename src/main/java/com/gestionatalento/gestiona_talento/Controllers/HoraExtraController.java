package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.HoraExtraDto;
import com.gestionatalento.gestiona_talento.Entity.HoraExtra;
import com.gestionatalento.gestiona_talento.Repository.HoraExtraRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.HoraExtraServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/horas-extras")
public class HoraExtraController {

    @Autowired
    HoraExtraRepository horaExtraRepository;
    @Autowired
    HoraExtraServiceImpl horaExtraServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearHoraExtra(@Valid @RequestBody HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = horaExtraServiceImpl.crearHoraExtra(horaExtraDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarHoraExtra(@Valid @RequestBody HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = horaExtraServiceImpl.actualizarHoraExtra(horaExtraDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @DeleteMapping("/eliminar")
    public GenericResponse eliminarHoraExtra(@Valid @RequestBody HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = horaExtraServiceImpl.eliminarHoraExtra(horaExtraDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarHorasExtras() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<HoraExtra> horasExtras = horaExtraRepository.findAll();

            // Verificamos si la lista está vacía
            if (horasExtras.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen horas extras registradas");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (HoraExtra horaExtra : horasExtras) {
                Map<String, Object> response = new HashMap<>();
                response.put("horaExtra", horaExtra); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas las horas extras correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/calcular")
    public GenericResponse calcularHoraExtra(@Valid @RequestBody HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = horaExtraServiceImpl.calcularHoraExtra(horaExtraDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

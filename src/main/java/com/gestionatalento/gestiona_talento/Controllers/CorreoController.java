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

import com.gestionatalento.gestiona_talento.Dto.CorreoDto;
import com.gestionatalento.gestiona_talento.Entity.Correo;
import com.gestionatalento.gestiona_talento.Repository.CorreoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.CorreoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/personas/correos")
public class CorreoController {

    @Autowired
    CorreoRepository correoRepository;
    @Autowired
    CorreoServiceImpl correoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearCorreo(@Valid @RequestBody CorreoDto correoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = correoServiceImpl.crearCorreo(correoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarCorreo(@Valid @RequestBody CorreoDto correoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = correoServiceImpl.actualizarCorreo(correoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarCorreos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Correo> correos = correoRepository.findAll();

            // Verificamos si la lista está vacía
            if (correos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen correos registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Correo correo : correos) {
                Map<String, Object> response = new HashMap<>();
                response.put("correo", correo); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los correos correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

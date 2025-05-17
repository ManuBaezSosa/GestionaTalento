package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.EstadoCivil;
import com.gestionatalento.gestiona_talento.Repository.EstadoCivilRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/estados-civiles")
public class EstadoCivilController {

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @GetMapping("/obtenerLista")
    public GenericResponse listarEstadosCiviles() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<EstadoCivil> estadosCiviles = estadoCivilRepository.findAll();

            // Verificamois si la lista está vacía
            if (estadosCiviles.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen estados civiles registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (EstadoCivil estadoCivil : estadosCiviles) {
                Map<String, Object> response = new HashMap<>();
                response.put("estadoCivil", estadoCivil); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los estados civiles correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.Pais;
import com.gestionatalento.gestiona_talento.Repository.PaisRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/paises")
public class PaisController {

    @Autowired
    PaisRepository paisRepository;

    @GetMapping("/obtenerLista")
    public GenericResponse listarGradosSalariales() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Pais> paises = paisRepository.findAll();

            // Verificamois si la lista está vacía
            if (paises.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen paises registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Pais pais : paises) {
                Map<String, Object> response = new HashMap<>();
                response.put("pais", pais); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los paises correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

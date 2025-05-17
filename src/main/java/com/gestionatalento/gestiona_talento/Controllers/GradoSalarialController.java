package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.GradoSalarial;
import com.gestionatalento.gestiona_talento.Repository.GradoSalarialRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/grados-salariales")
public class GradoSalarialController {

    @Autowired
    GradoSalarialRepository gradoSalarialRepository;

    @GetMapping("/obtenerLista")
    public GenericResponse listarGradosSalariales() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<GradoSalarial> gradosSalariales = gradoSalarialRepository.findAll();

            // Verificamois si la lista está vacía
            if (gradosSalariales.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen grados salariales registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (GradoSalarial gradoSalarial : gradosSalariales) {
                Map<String, Object> response = new HashMap<>();
                response.put("gradoSalarial", gradoSalarial); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los grados salariales correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

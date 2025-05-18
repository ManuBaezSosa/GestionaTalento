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

import com.gestionatalento.gestiona_talento.Dto.ParametroSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.ParametroSalarial;
import com.gestionatalento.gestiona_talento.Repository.ParametroSalarialRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.ParametroSalarialServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/salarios/informes")
public class ParametroSalarialController {

    @Autowired
    ParametroSalarialRepository parametroSalarialRepository;
    @Autowired
    ParametroSalarialServiceImpl parametroSalarialServiceImpl;

    @GetMapping("/obtenerParametros")
    public GenericResponse listarParametroSalariales() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<ParametroSalarial> parametrosSalariales = parametroSalarialRepository.findAll();

            // Verificamos si la lista está vacía
            if (parametrosSalariales.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen parametros salariales registradas");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (ParametroSalarial parametroSalarial : parametrosSalariales) {
                Map<String, Object> response = new HashMap<>();
                response.put("parametroSalarial", parametroSalarial); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los Parametros Salariales correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

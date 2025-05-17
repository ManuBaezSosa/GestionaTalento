package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.ObjetoGasto;
import com.gestionatalento.gestiona_talento.Repository.ObjetoGastoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/objetos-gasto")
public class ObjetoGastoController {

    @Autowired
    ObjetoGastoRepository objetoGastoRepository;

    @GetMapping("/obtenerLista")
    public GenericResponse listarObjetosGasto() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<ObjetoGasto> objetosGasto = objetoGastoRepository.findAll();

            // Verificamois si la lista está vacía
            if (objetosGasto.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen objetos de gasto registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (ObjetoGasto objetoGasto : objetosGasto) {
                Map<String, Object> response = new HashMap<>();
                response.put("objetoGasto", objetoGasto); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los objetos de gasto correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

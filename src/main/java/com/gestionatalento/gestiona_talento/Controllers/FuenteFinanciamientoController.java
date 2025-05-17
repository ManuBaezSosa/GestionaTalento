package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.FuenteFinanciamiento;
import com.gestionatalento.gestiona_talento.Repository.FuenteFinanciamientoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/fuentes-financiamiento")
public class FuenteFinanciamientoController {

    @Autowired
    FuenteFinanciamientoRepository fuenteFinanciamientoRepository;

    @GetMapping("/obtenerLista")
    public GenericResponse listarFuentesFinanciamiento() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<FuenteFinanciamiento> fuentesFinanciamiento = fuenteFinanciamientoRepository.findAll();

            // Verificamois si la lista está vacía
            if (fuentesFinanciamiento.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen fuentes de financiamiento registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (FuenteFinanciamiento fuenteFinanciamiento : fuentesFinanciamiento) {
                Map<String, Object> response = new HashMap<>();
                response.put("fuenteFinanciamiento", fuenteFinanciamiento); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos las fuentes de financiamiento correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

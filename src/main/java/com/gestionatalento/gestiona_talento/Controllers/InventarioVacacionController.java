package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.InventarioVacacion;
import com.gestionatalento.gestiona_talento.Repository.InventarioVacacionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/vacaciones")
public class InventarioVacacionController {

    @Autowired
    InventarioVacacionRepository inventarioRepository;

    @GetMapping("/obtenerInventario")
    public GenericResponse listarInventarioVacacions() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<InventarioVacacion> inventarios = inventarioRepository.findAll();

            // Verificamos si la lista está vacía
            if (inventarios.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen inventarios registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (InventarioVacacion inventarioVacacion : inventarios) {
                Map<String, Object> response = new HashMap<>();
                response.put("inventarioVacacion", inventarioVacacion); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Ha sido obtenido el inventario exitosamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

}

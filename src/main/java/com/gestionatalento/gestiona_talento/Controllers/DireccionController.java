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

import com.gestionatalento.gestiona_talento.Dto.DireccionDto;
import com.gestionatalento.gestiona_talento.Entity.Direccion;
import com.gestionatalento.gestiona_talento.Repository.DireccionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.DireccionServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/direcciones")
public class DireccionController {

    @Autowired
    DireccionRepository direccionRepository;
    @Autowired
    DireccionServiceImpl direccionServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearDireccion(@Valid @RequestBody DireccionDto direccionDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = direccionServiceImpl.crearDireccion(direccionDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarDireccion(@Valid @RequestBody DireccionDto direccionDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = direccionServiceImpl.actualizarDireccion(direccionDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarDirecciones() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Direccion> direcciones = direccionRepository.findAll();

            // Verificamos si la lista está vacía
            if (direcciones.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen direcciones registradas");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Direccion direccion : direcciones) {
                Map<String, Object> response = new HashMap<>();
                response.put("direccion", direccion); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas las direcciones correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

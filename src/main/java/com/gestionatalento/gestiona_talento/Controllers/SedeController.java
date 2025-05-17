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

import com.gestionatalento.gestiona_talento.Dto.SedeDto;
import com.gestionatalento.gestiona_talento.Entity.Sede;
import com.gestionatalento.gestiona_talento.Repository.SedeRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.SedeServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/sedes")
public class SedeController {

    @Autowired
    SedeRepository sedeRepository;
    @Autowired
    SedeServiceImpl sedeServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearSede(@Valid @RequestBody SedeDto sedeDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = sedeServiceImpl.crearSede(sedeDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarSede(@Valid @RequestBody SedeDto sedeDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = sedeServiceImpl.actualizarSede(sedeDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarSedees() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Sede> sedes = sedeRepository.findAll();

            // Verificamos si la lista está vacía
            if (sedes.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen sedes registradas");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Sede sede : sedes) {
                Map<String, Object> response = new HashMap<>();
                response.put("sede", sede); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas las sedes correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

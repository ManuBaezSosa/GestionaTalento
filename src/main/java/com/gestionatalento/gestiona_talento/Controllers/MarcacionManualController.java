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

import com.gestionatalento.gestiona_talento.Dto.MarcacionManualDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionManual;
import com.gestionatalento.gestiona_talento.Repository.MarcacionManualRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.MarcacionManualServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/marcaciones/manuales")
public class MarcacionManualController {

    @Autowired
    MarcacionManualRepository marcacionManualRepository;
    @Autowired
    MarcacionManualServiceImpl marcacionManualServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearMarcacionManual(@Valid @RequestBody MarcacionManualDto marcacionManualDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = marcacionManualServiceImpl.crearMarcacionManual(marcacionManualDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarMarcacionManual(@Valid @RequestBody MarcacionManualDto marcacionManualDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = marcacionManualServiceImpl.actualizarMarcacionManual(marcacionManualDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarMarcaciones() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<MarcacionManual> marcacionesManuales = marcacionManualRepository.findAll();

            // Verificamos si la lista está vacía
            if (marcacionesManuales.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen marcaciones manuales registradas");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (MarcacionManual marcacionManual : marcacionesManuales) {
                Map<String, Object> response = new HashMap<>();
                response.put("marcacionManual", marcacionManual); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas todas las marcaciones manuales correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

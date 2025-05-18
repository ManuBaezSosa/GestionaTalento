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

import com.gestionatalento.gestiona_talento.Dto.ContratoDto;
import com.gestionatalento.gestiona_talento.Entity.Contrato;
import com.gestionatalento.gestiona_talento.Repository.ContratoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.ContratoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    ContratoRepository contratoRepository;
    @Autowired
    ContratoServiceImpl contratoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearContrato(@Valid @RequestBody ContratoDto contratoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = contratoServiceImpl.crearContrato(contratoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarContrato(@Valid @RequestBody ContratoDto contratoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = contratoServiceImpl.actualizarContrato(contratoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarContratoes() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Contrato> contratos = contratoRepository.findAll();

            // Verificamos si la lista está vacía
            if (contratos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen contratos registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Contrato contrato : contratos) {
                Map<String, Object> response = new HashMap<>();
                response.put("contrato", contrato); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas las contratos correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

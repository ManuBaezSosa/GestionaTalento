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

import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;
import com.gestionatalento.gestiona_talento.Repository.DescuentoSalarialRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.DescuentoSalarialServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/descuentos-salariales")
public class DescuentoSalarialController {

    @Autowired
    DescuentoSalarialRepository descuentoSalarialRepository;
    @Autowired
    DescuentoSalarialServiceImpl descuentoSalarialServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.crearDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.actualizarDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @DeleteMapping("/eliminar")
    public GenericResponse eliminarDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.eliminarDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarDescuentos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<DescuentoSalarial> descuentos = descuentoSalarialRepository.findAll();

            // Verificamos si la lista está vacía
            if (descuentos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen descuentos salariales registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (DescuentoSalarial descuentoSalarial : descuentos) {
                Map<String, Object> response = new HashMap<>();
                response.put("descuentoSalarial", descuentoSalarial); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los descuentos salariales correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/calcular")
    public GenericResponse calcularDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.calcularDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

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

import com.gestionatalento.gestiona_talento.Dto.DepartamentoDto;
import com.gestionatalento.gestiona_talento.Entity.Departamento;
import com.gestionatalento.gestiona_talento.Repository.DepartamentoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.DepartamentoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoRepository departamentoRepository;
    @Autowired
    DepartamentoServiceImpl departamentoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearDepartamento(@Valid @RequestBody DepartamentoDto departamentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = departamentoServiceImpl.crearDepartamento(departamentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarDepartamento(@Valid @RequestBody DepartamentoDto departamentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = departamentoServiceImpl.actualizarDepartamento(departamentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarDepartamentos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Departamento> departamentos = departamentoRepository.findAll();

            // Verificamos si la lista está vacía
            if (departamentos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen departamentos registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Departamento departamento : departamentos) {
                Map<String, Object> response = new HashMap<>();
                response.put("departamento", departamento); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los departamentos correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

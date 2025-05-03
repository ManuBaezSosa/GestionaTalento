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

import com.gestionatalento.gestiona_talento.Dto.CargoDto;
import com.gestionatalento.gestiona_talento.Entity.Cargo;
import com.gestionatalento.gestiona_talento.Repository.CargoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.CargoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/configuraciones/cargos")
public class CargoController {

    @Autowired
    CargoRepository cargoRepository;
    @Autowired
    CargoServiceImpl cargoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearCargo(@Valid @RequestBody CargoDto cargoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = cargoServiceImpl.crearCargo(cargoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarCargo(@Valid @RequestBody CargoDto cargoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = cargoServiceImpl.actualizarCargo(cargoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarCargos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Cargo> cargos = cargoRepository.findAll();

            // Verificamos si la lista está vacía
            if (cargos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen cargos registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Cargo cargo : cargos) {
                Map<String, Object> response = new HashMap<>();
                response.put("cargo", cargo); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los cargos correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

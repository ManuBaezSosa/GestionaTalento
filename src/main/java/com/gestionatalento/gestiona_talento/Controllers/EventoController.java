package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import com.gestionatalento.gestiona_talento.Dto.EventoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Evento;
import com.gestionatalento.gestiona_talento.Repository.EventoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.EventoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/configuraciones/eventos")
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    EventoServiceImpl eventoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearEvento(@Valid @RequestBody EventoDto eventoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = eventoServiceImpl.crearEvento(eventoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarEvento(@Valid @RequestBody EventoDto eventoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = eventoServiceImpl.actualizarEvento(eventoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtener/id/{nroEvento}")
    public GenericResponse buscarEvento(@Valid @PathVariable Long nroEvento) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Optional<Evento> evento = eventoRepository.findById(nroEvento);
            if (evento.isPresent()) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Evento obtenido exitosamente");
                genericResponse.setObjeto(evento.get());
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe evento registrado con el valor proporcionado");
                genericResponse.setDatoAdicional("ID: " + nroEvento);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarEventos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Evento> eventos = eventoRepository.findAll();

            // Verificamos si la lista está vacía
            if (eventos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen eventos registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Evento evento : eventos) {
                Map<String, Object> response = new HashMap<>();
                response.put("evento", evento); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los eventos correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

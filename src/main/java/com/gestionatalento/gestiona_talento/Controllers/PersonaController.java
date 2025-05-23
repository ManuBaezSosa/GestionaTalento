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

import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.PersonaServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/personas") // Define la ruta base para todas las solicitudes
public class PersonaController {


    @Autowired
    PersonaRepository  personaRepository;
    @Autowired
    PersonaServiceImpl personaServiceImpl;



    @PostMapping("/crear")
    public GenericResponse crearPersona(@Valid @RequestBody PersonaDto personaDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            // Intentamos crear el empleado
            genericResponse = personaServiceImpl.crearPersona(personaDto);
            return genericResponse;
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarPersona(@Valid @RequestBody PersonaDto personaDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = personaServiceImpl.actualizarPersona(personaDto);
            return genericResponse;
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarPersonas() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Persona> personas = personaRepository.findAll();

            // Verificamos si la lista está vacía
            if (personas.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen personas registradas");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            // Para cada persona en la lista, envolvemos el objeto en un mapa con la clave "persona"
            for (Persona persona : personas) {
                Map<String, Object> response = new HashMap<>();
                response.put("persona", persona);  // Aquí agregamos el objeto Persona bajo la clave "persona"
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas las personas correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtener/documento/{nroDocumento}")
    public GenericResponse buscarPersona(@PathVariable String nroDocumento) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Optional<Persona> persona = personaRepository.findByNroDocumento(nroDocumento);

            if (persona.isPresent()) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Persona obtenida exitosamente");
                genericResponse.setObjeto(persona.get());
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe persona registrada con el valor proporcionado. NroDocumento: " + nroDocumento);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }


}

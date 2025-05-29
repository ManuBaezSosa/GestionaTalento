package com.gestionatalento.gestiona_talento.Controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Value("${storage.fotos.path}")
    private String storagePath;


    @PostMapping("/crear")
    public GenericResponse crearPersona(@Valid
                                        @RequestParam("data") String jsonPersonaDto,
                                        @RequestParam("foto") MultipartFile foto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            // Intentamos crear el empleado 
            PersonaDto personaDto = new ObjectMapper().readValue(jsonPersonaDto, PersonaDto.class);
            genericResponse = personaServiceImpl.crearPersona(personaDto, foto);
            return genericResponse;
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarPersona(@Valid
                                            @RequestParam("data") String jsonPersonaDto,
                                            @RequestParam("foto") MultipartFile foto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            PersonaDto personaDto = new ObjectMapper().readValue(jsonPersonaDto, PersonaDto.class);
            genericResponse = personaServiceImpl.actualizarPersona(personaDto, foto);
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

    @GetMapping("/obtener/foto/{codPersona}")
    public ResponseEntity<Resource> obtenerFoto(@PathVariable Long codPersona) throws IOException {
        Optional<Persona> persona = personaRepository.findById(codPersona);;

        Path filepath = Paths.get(storagePath, persona.get().getRutaFoto());
        Resource resource = new UrlResource(filepath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }


}

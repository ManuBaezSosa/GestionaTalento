package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Dto.PersonaDto;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Jwt.ApplicationConfig;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.Persona.PersonaServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/personas") // Define la ruta base para todas las solicitudes
public class PersonaController {


    @Autowired
    PersonaRepository  personaRepository;
    @Autowired
    PersonaServiceImpl personaServiceImpl;



    @PostMapping("/crear")
    public ResponseEntity<?> crearPersona(@Valid @RequestBody PersonaDto personaDto) {
        try {
            // Intentamos crear el empleado
            GenericResponse genericResponse = personaServiceImpl.crearPersona(personaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "codigoMensaje", "500",
                        "mensaje", "Ha ocurrido un error interno en el servidor al crear la persona: " + e.getMessage()
                    ));
        }
    }

    // Handler de validación de errores
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Crear un objeto GenericResponse
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCodigoMensaje("400");
        genericResponse.setMensaje("Existen campos con valores incorrectos");
        
        // Mapa para almacenar los errores de validación
        Map<String, String> errors = new HashMap<>();
        
        // Llenar el mapa de errores con los mensajes de validación
        BindingResult result = ex.getBindingResult();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        // Establecer los errores directamente en el campo 'objeto' del GenericResponse
        genericResponse.setObjeto(errors);

        // Usar LinkedHashMap para mantener el orden de inserción
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("codigoMensaje", genericResponse.getCodigoMensaje());
        response.put("mensaje", genericResponse.getMensaje());
        response.put("objeto", genericResponse.getObjeto());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPersona(@Valid @RequestBody PersonaDto personaDto) {
        try {
            GenericResponse genericResponse = personaServiceImpl.actualizarPersona(personaDto);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
        } catch (Exception e) {
            // Si hay un error en la creación de la persona, retornamos un error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "codigoMensaje", "500",
                        "mensaje", "Ha ocurrido un error interno en el servidor al crear la persona: " + e.getMessage()
                    ));
        }
    }

    @GetMapping("/obtenerLista")
    public ResponseEntity<?> listarPersonas() {
        try {
            List<Persona> personas = personaRepository.findAll();

            // Verificamos si la lista está vacía
            if (personas.isEmpty()) {
                GenericResponse genericResponse = new GenericResponse();
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen personas registradas");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genericResponse);
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            // Para cada persona en la lista, envolvemos el objeto en un mapa con la clave "persona"
            for (Persona persona : personas) {
                Map<String, Object> response = new HashMap<>();
                response.put("persona", persona);  // Aquí agregamos el objeto Persona bajo la clave "persona"
                responseList.add(response);
            }

            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


}

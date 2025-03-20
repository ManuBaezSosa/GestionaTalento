package com.gestionatalento.gestiona_talento.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entities.Persona;
import com.gestionatalento.gestiona_talento.Jwt.ApplicationConfig;
import com.gestionatalento.gestiona_talento.Repository.PersonaRepository;
import com.gestionatalento.gestiona_talento.Service.Persona.PersonaServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/Persona") // Define la ruta base para todas las solicitudes
public class PersonaController {


    @Autowired
    PersonaRepository  personaRepository;
    @Autowired
    PersonaServiceImpl personaServiceImpl;



    @PostMapping("/crearPersona")
    public ResponseEntity<?> crerarPersona(@RequestBody Persona persona){
        //Primero buscamos si la persona ya existe
        Optional<Persona> personaNueva = personaRepository.findByNroDocumento(persona.getNroDocumento());

        if (personaNueva.isPresent()) {
            return ResponseEntity.badRequest().body("{error: El número de documento ya está registrado.}" + persona.getNroDocumento());
        }

        personaRepository.save(persona);

        return ResponseEntity.ok(persona);
    }

    @GetMapping("/consultaPersona")
    public ResponseEntity<?> listarEmpleados() {
        try {
            List<Persona> todasPersonas = personaRepository.findAll();

            //consutltamos si la lista esta vacia
            if(todasPersonas.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO HAY PERSONAS");
            }

            return ResponseEntity.ok(todasPersonas);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Ocurrió un error inesperado: " + e.getMessage());
        }

        
    }
    


}

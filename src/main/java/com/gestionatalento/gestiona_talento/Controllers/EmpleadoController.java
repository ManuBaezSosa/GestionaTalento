package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Persona;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.Empleados.EmpleadoServiceImpl;

import io.jsonwebtoken.lang.Collections;
import jakarta.validation.Valid;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    // ----------- EMPLEADOS -------- //
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    EmpleadoServiceImpl empleadoServiceImpl;

    /* Este metodo crea los empleados, se debe de ver el metodo de crearEmpleado en la clase de EmpleadoServiceImpl */
    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto) {
        try {
            // Intentamos crear el empleado
            GenericResponse genericResponse = empleadoServiceImpl.crearEmpleado(empleadoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "codigoMensaje", "500",
                        "mensaje", "Ha ocurrido un error interno en el servidor al dar de alta el empleado: " + e.getMessage()
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
    public ResponseEntity<?> actualizarEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto) {
        try {
            GenericResponse genericResponse = empleadoServiceImpl.actualizarEmpleado(empleadoDto);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
        } catch (Exception e) {
            // Si hay un error en la creación del empleado, retornamos un error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "codigoMensaje", "500",
                        "mensaje", "Ha ocurrido un error interno en el servidor al actualizar el empleado: " + e.getMessage()
                    ));
        }
    }

    @GetMapping("/obtenerLista")
    public ResponseEntity<?> listarPersonas() {
        try {
            List<Empleado> empleados = empleadoRepository.findAll();

            // Verificamos si la lista está vacía
            if (empleados.isEmpty()) {
                GenericResponse genericResponse = new GenericResponse();
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen empleados registrados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genericResponse);
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            // Para cada persona en la lista, envolvemos el objeto en un mapa con la clave "persona"
            for (Empleado empleado : empleados) {
                Map<String, Object> response = new HashMap<>();
                response.put("empleado", empleado);  // Aquí agregamos el objeto Persona bajo la clave "persona"
                responseList.add(response);
            }

            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/obtener/id/{idEmpleado}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long idEmpleado) {
        Optional<Empleado> empleado = empleadoRepository.findById(idEmpleado);

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get()); // Si existe, devolver el objeto
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Empleado no encontrado con ID: " + idEmpleado);
        }
    }

    @GetMapping("/obtener/documento/{nroDocumento}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable String nroDocumento) {
        Optional<Empleado> empleado = empleadoRepository.findByNroDocumento(nroDocumento);

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get()); // Si existe, devolver el objeto
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Empleado no encontrado con ID: " + nroDocumento);
        }
    }

    @PutMapping("/bajar")
    public ResponseEntity<?> bajarEmpleado(@RequestBody EmpleadoDto request){
        try {
            GenericResponse genericResponse = empleadoServiceImpl.bajarEmpleado(request);
            return ResponseEntity.ok()
                .body(genericResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of(
                    "codigoMensaje", "500",
                    "mensaje", "Ha ocurrido un error interno en el servidor al dar de Baja Empleado: " + e.getMessage()
                ));
        }
    }

}

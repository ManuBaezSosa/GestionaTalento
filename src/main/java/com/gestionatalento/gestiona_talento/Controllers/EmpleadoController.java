package com.gestionatalento.gestiona_talento.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Dto.PasantesDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Request.EmpleadoRequest;
import com.gestionatalento.gestiona_talento.Request.PersonaRequest;
import com.gestionatalento.gestiona_talento.Service.Empleados.EmpleadoServiceImpl;

import io.jsonwebtoken.lang.Collections;

import org.springframework.web.bind.annotation.DeleteMapping;
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

    /*
     * Este metodo crea los empleados, se debe de ver el metodo de crearEmpleado en la clase de EmpleadoServiceImpl
     */
    @PostMapping("/crearEmpleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoRequest request) {
        try {
            empleadoServiceImpl.crearEmpleado(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Empleado creado exitosamente con ID: " + request.getPersona().getCodPersona());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear usuario: " + e.getMessage());
        }
    }

    @GetMapping("/obtenerEmpleados")
    public ResponseEntity<?> obtenerEmpleado() {
        try {
            List<Empleado> empleados = empleadoServiceImpl.obtenerAllEmpleado();
            return ResponseEntity.ok(empleados);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/Empleado/{idPersona}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long idPersona) {
        Optional<Empleado> empleado = empleadoRepository.findById(idPersona);

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get()); // Si existe, devolver el objeto
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Empleado no encontrado con ID: " + idPersona);
        }
    }
/* 
    @GetMapping("/buscar/Empleado/{idPersona}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long idPersona) {
        try {
            Object empleado = empleadoRepository.findById(idPersona);
            return ResponseEntity.ok(empleado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

*/
    @DeleteMapping("{codPersona}/eliminarEmpleado")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long codPersona){
        try {
            Optional<Empleado> empleadoElimiando = empleadoRepository.findById(codPersona);
            return ResponseEntity.ok(empleadoElimiando.get());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    /*@GetMapping("/buscar/Empleado")
    public ResponseEntity<?> obtenerFuncionario(@RequestBody PersonaRequest request) {
        try {
            return ResponseEntity.ok(empleadoServiceImpl.buscarEmpleado(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }*/
    
    // En tu Controller
    @PostMapping("/empleado/actualizarDatos")
    public ResponseEntity<?> updateEmpleado(@RequestBody EmpleadoDto request) {
        try {
            Empleado empleadoActualizado = empleadoServiceImpl.actualizarEmpleado(request);
            return ResponseEntity.ok()
                .body(Map.of(
                    "mensaje", "Empleado actualizado exitosamente",
                    "id", empleadoActualizado.getPersona().getCodPersona(),
                    "empleado", empleadoActualizado
                ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of(
                    "error", "Error al actualizar empleado",
                    "detalle", e.getMessage()
                ));
        }
    }

}

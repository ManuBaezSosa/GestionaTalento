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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.EmpleadoServiceImpl;

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
    public GenericResponse crearEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = empleadoServiceImpl.crearEmpleado(empleadoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = empleadoServiceImpl.actualizarEmpleado(empleadoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarPersonas() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Empleado> empleados = empleadoRepository.findAll();

            // Verificamos si la lista está vacía
            if (empleados.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen empleados registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            // Para cada persona en la lista, envolvemos el objeto en un mapa con la clave "persona"
            for (Empleado empleado : empleados) {
                Map<String, Object> response = new HashMap<>();
                response.put("empleado", empleado);  // Aquí agregamos el objeto Persona bajo la clave "persona"
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los empleados correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtener/id/{codEmpleado}")
    public GenericResponse buscarEmpleado(@Valid @PathVariable Long codEmpleado) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Optional<Empleado> empleado = empleadoRepository.findById(codEmpleado);
            if (empleado.isPresent()) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado obtenido exitosamente");
                genericResponse.setObjeto(empleado.get());
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado registrado con el valor proporcionado. ID: " + codEmpleado);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }

    }

    @GetMapping("/obtener/documento/{nroDocumento}")
    public GenericResponse buscarEmpleado(@PathVariable String nroDocumento) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Optional<Empleado> empleado = empleadoRepository.findByNroDocumento(nroDocumento);

            if (empleado.isPresent()) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado obtenido exitosamente");
                genericResponse.setObjeto(empleado.get());
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado registrado con el valor proporcionado. NroDocumento: " + nroDocumento);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/bajar")
    public GenericResponse bajarEmpleado(@RequestBody EmpleadoDto request){
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = empleadoServiceImpl.bajarEmpleado(request);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/altas")
    public ResponseEntity<GenericResponse> obtenerInformeAltas(@RequestParam("periodo") String periodo) {
        GenericResponse response = empleadoServiceImpl.obtenerInformeAltas(periodo);
        return new ResponseEntity<>(response, getStatusFromCodigo(response.getCodigoMensaje()));
    }

    @GetMapping("/bajas")
    public ResponseEntity<GenericResponse> obtenerInformeBajas(@RequestParam("periodo") String periodo) {
        GenericResponse response = empleadoServiceImpl.obtenerInformeBajas(periodo);
        return new ResponseEntity<>(response, getStatusFromCodigo(response.getCodigoMensaje()));
    }

    @GetMapping("/modificaionSalario")
    public ResponseEntity<GenericResponse> obtenerInformeHistoricoAsignacion(@RequestParam("periodo") String periodo) {
        GenericResponse response = empleadoServiceImpl.obtenerInformeHistoricoAsignacion(periodo);
        return new ResponseEntity<>(response, getStatusFromCodigo(response.getCodigoMensaje()));
    }

    private HttpStatus getStatusFromCodigo(String codigo) {
        switch (codigo) {
            case "200": return HttpStatus.OK;
            case "404": return HttpStatus.NOT_FOUND;
            case "409": return HttpStatus.CONFLICT;
            default: return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}

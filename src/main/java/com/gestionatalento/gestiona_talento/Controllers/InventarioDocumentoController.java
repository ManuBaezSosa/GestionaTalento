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

import com.gestionatalento.gestiona_talento.Dto.InventarioDocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.InventarioDocumento;
import com.gestionatalento.gestiona_talento.Repository.InventarioDocumentoRepository;
import com.gestionatalento.gestiona_talento.Repository.DireccionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.InventarioDocumentoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/personas/documentos/inventarios")
public class InventarioDocumentoController {

    @Autowired
    InventarioDocumentoRepository inventarioDocumentoRepository;
    @Autowired
    InventarioDocumentoServiceImpl inventarioDocumentoServiceImpl;
    @Autowired
    DireccionRepository direccionRepository;

    @PostMapping("/crear")
    public GenericResponse crearInventarioDocumento(@Valid @RequestBody InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = inventarioDocumentoServiceImpl.crearInventarioDocumento(inventarioDocumentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarInventarioDocumento(@Valid @RequestBody InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = inventarioDocumentoServiceImpl.actualizarInventarioDocumento(inventarioDocumentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @DeleteMapping("/eliminar")
    public GenericResponse eliminarInventarioDocumento(@Valid @RequestBody InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = inventarioDocumentoServiceImpl.eliminarInventarioDocumento(inventarioDocumentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PostMapping("/obtenerLista")
    public GenericResponse listarInventarioDocumento(@Valid @RequestBody InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<InventarioDocumento> inventarios = inventarioDocumentoRepository.findByInventarioDocumentoExistentes(inventarioDocumentoDto.getPeriodo().getNroPeriodo());
            List<Empleado> empleados = inventarioDocumentoRepository.findByInventarioDocumentoFaltantes(inventarioDocumentoDto.getPeriodo().getNroPeriodo());

            // Verificamos si la lista está vacía
            if (inventarios.isEmpty() && empleados.isEmpty()) {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen datos para inventario de salarios");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (InventarioDocumento inventarioDocumento : inventarios) {
                Map<String, Object> response = new HashMap<>();
                response.put("inventarioDocumento", inventarioDocumento); 
                responseList.add(response);
            }

            for (Empleado empleado : empleados) {
                Map<String, Object> response = new HashMap<>();

                InventarioDocumento inventarioDocumento = new InventarioDocumento();
                inventarioDocumento.setEmpleado(empleado);

                response.put("inventarioDocumento", inventarioDocumento); 
                responseList.add(response);
            }

            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los documentos de inventario salarial");
            genericResponse.setObjeto(responseList);
    
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

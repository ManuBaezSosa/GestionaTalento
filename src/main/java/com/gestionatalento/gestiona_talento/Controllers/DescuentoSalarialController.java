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

import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialCabDto;
import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialDto;
import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialInfDto;
import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;
import com.gestionatalento.gestiona_talento.Entity.Direccion;
import com.gestionatalento.gestiona_talento.Repository.DescuentoSalarialRepository;
import com.gestionatalento.gestiona_talento.Repository.DireccionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.DescuentoSalarialServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/descuentos-salariales")
public class DescuentoSalarialController {

    @Autowired
    DescuentoSalarialRepository descuentoSalarialRepository;
    @Autowired
    DescuentoSalarialServiceImpl descuentoSalarialServiceImpl;
    @Autowired
    DireccionRepository direccionRepository;

    @PostMapping("/crear")
    public GenericResponse crearDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.crearDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.actualizarDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @DeleteMapping("/eliminar")
    public GenericResponse eliminarDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.eliminarDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarDescuentosSalariales() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<DescuentoSalarial> descuentos = descuentoSalarialRepository.findAll();
    
            // Verificamos si la lista está vacía
            if (descuentos.isEmpty()) {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen descuentos salariales registrados");
                return genericResponse;
            }
    
            List<DescuentoSalarial> responseList = new ArrayList<>();
    
            responseList.addAll(descuentos);
    
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos todos los descuentos salariales correctamente");
            genericResponse.setObjeto(responseList); 
    
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PostMapping("/calcular")
    public GenericResponse calcularDescuentoSalarial(@Valid @RequestBody DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = descuentoSalarialServiceImpl.calcularDescuentoSalarial(descuentoSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerMontoSuperiores")
    public GenericResponse listarInventarioVacacions() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<DescuentoSalarial> descuentos = descuentoSalarialRepository.findByMonto();

            // Verificamos si la lista está vacía
            if (descuentos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen descuentos superiores al 25% del salario");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (DescuentoSalarial descuentoSalarial : descuentos) {
                Map<String, Object> response = new HashMap<>();
                response.put("descuentoSalarial", descuentoSalarial); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los descuentos salariales superiores al 25% del salario exitosamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/informes/obtenerPorDireccion")
    public GenericResponse obtenerDescuentoPorDireccion() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Direccion> direcciones = direccionRepository.findAll();
            List<DescuentoSalarialInfDto> descuentosSalariales = new ArrayList<>();

            // Verificamos si la lista está vacía
            if (direcciones.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen direcciones registradas");
                return genericResponse;
            }

            DescuentoSalarialInfDto descuentoSalarialInforme;
            DescuentoSalarialCabDto descuentoSalarialCabecera;
            List<DescuentoSalarial> descuentoSalarialDetalleList;

            for (Direccion direccion : direcciones) {
                descuentoSalarialInforme = new DescuentoSalarialInfDto();
                descuentoSalarialCabecera = new DescuentoSalarialCabDto();
                descuentoSalarialDetalleList = new ArrayList<>();

                descuentoSalarialCabecera.setDireccion(direccion);
                
                List<DescuentoSalarial> detallesDescuento = descuentoSalarialRepository.findByDireccion(direccion.getCodDireccion());

                for (DescuentoSalarial descuentoSalarial : detallesDescuento) {
                    descuentoSalarialDetalleList.add(descuentoSalarial);
                }

                descuentoSalarialInforme.setCabecera(descuentoSalarialCabecera);
                descuentoSalarialInforme.setDetalle(descuentoSalarialDetalleList);
                descuentosSalariales.add(descuentoSalarialInforme);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los Descuentos Salariales por Direccion correctamente");
            genericResponse.setObjeto(descuentosSalariales);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.JustificativoDto;
import com.gestionatalento.gestiona_talento.Entity.Justificativo;
import com.gestionatalento.gestiona_talento.Repository.JustificativoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.JustificativoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/justificativos")
public class JustificativoController {

    @Autowired
    JustificativoRepository justificativoRepository;
    @Autowired
    JustificativoServiceImpl justificativoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearJustificativo(@Valid @RequestBody JustificativoDto justificativoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = justificativoServiceImpl.crearJustificativo(justificativoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarJustificativo(@Valid @RequestBody JustificativoDto justificativoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = justificativoServiceImpl.actualizarJustificativo(justificativoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerListaJustificativos")
    public GenericResponse listarJustificativos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Justificativo> justificativos = justificativoRepository.listarJustificativos();
    
            // Verificamos si la lista está vacía
            if (justificativos.isEmpty()) {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen justificativos registrados");
                return genericResponse;
            }
    
            List<Justificativo> responseList = new ArrayList<>();
    
            responseList.addAll(justificativos);
    
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los justificativos correctamente");
            genericResponse.setObjeto(responseList); 
    
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerListaVacaciones")
    public GenericResponse listarVacaciones() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Justificativo> justificativos = justificativoRepository.listarVacaciones();
    
            // Verificamos si la lista está vacía
            if (justificativos.isEmpty()) {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen vacaciones registradas");
                return genericResponse;
            }
    
            List<Justificativo> responseList = new ArrayList<>();
    
            responseList.addAll(justificativos);
    
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidas las vacaciones correctamente");
            genericResponse.setObjeto(responseList); 
    
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

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

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.Documento;
import com.gestionatalento.gestiona_talento.Repository.DocumentoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.DocumentoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/personas/documentos")
public class DocumentoController {

    @Autowired
    DocumentoRepository documentoRepository;
    @Autowired
    DocumentoServiceImpl documentoServiceImpl;

    @PostMapping("/crear")
    public GenericResponse crearDocumento(@Valid @RequestBody DocumentoDto documentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = documentoServiceImpl.crearDocumento(documentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarDocumento(@Valid @RequestBody DocumentoDto documentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = documentoServiceImpl.actualizarDocumento(documentoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarDocumentos() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Documento> documentos = documentoRepository.findAll();

            // Verificamos si la lista está vacía
            if (documentos.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen documentos registrados");
                return genericResponse;
            }
            
            // Creamos un contenedor para la respuesta
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Documento documento : documentos) {
                Map<String, Object> response = new HashMap<>();
                response.put("documento", documento); 
                responseList.add(response);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los documentos correctamente");
            genericResponse.setObjeto(responseList);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

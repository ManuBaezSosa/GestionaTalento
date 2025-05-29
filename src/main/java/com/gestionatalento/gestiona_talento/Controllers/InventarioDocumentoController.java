package com.gestionatalento.gestiona_talento.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.InventarioDocumentoDto;
import com.gestionatalento.gestiona_talento.Repository.InventarioDocumentoRepository;
import com.gestionatalento.gestiona_talento.Repository.DireccionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.InventarioDocumentoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/documentos/inventario")
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
}

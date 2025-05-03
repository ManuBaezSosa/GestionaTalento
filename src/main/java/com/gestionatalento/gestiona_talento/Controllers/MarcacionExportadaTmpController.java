package com.gestionatalento.gestiona_talento.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Repository.MarcacionExportadaTmpRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.MarcacionExportadaTmpServiceImpl;


@RestController
@RequestMapping("/marcaciones/exportacion")
public class MarcacionExportadaTmpController {
    @Autowired
    MarcacionExportadaTmpRepository marcacionExportadaRepository;

    @Autowired
    MarcacionExportadaTmpServiceImpl marcacionExportadaTmpServiceImpl;

    @PostMapping("/cargar-marcacion")
    public GenericResponse cargarMarcacion(@RequestParam("file") MultipartFile file) {
        GenericResponse genericResponse = new GenericResponse();
        if (file.isEmpty()) {
            genericResponse.setCodigoMensaje("404");
            genericResponse.setMensaje("El archivo se encuentra vacio");
            return genericResponse;
        }

        try {
            genericResponse = marcacionExportadaTmpServiceImpl.cargarMarcacion(file);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

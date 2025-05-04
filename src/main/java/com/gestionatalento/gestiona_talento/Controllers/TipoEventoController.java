package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Entity.TipoEvento;
import com.gestionatalento.gestiona_talento.Repository.TipoEventoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@RestController
@RequestMapping("/configuraciones/tipos-eventos")
public class TipoEventoController {

    @Autowired
    TipoEventoRepository tipoEventoRepository;

    @GetMapping("/obtenerListaActiva")
    public GenericResponse obtenerListaActiva() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<TipoEvento> tiposEventos = new ArrayList<>();
            tiposEventos = tipoEventoRepository.obtenerListaActiva();
            
            if (tiposEventos.size() > 0) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Han sido obtenidos los tipos de eventos");
                genericResponse.setObjeto(tiposEventos);
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen tipos de eventos parametrizados");
                genericResponse.setObjeto(null);
            }
            genericResponse.setCodigoMensaje("200");
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }
}

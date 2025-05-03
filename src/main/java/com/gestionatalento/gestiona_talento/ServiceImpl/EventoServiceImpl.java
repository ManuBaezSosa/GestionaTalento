package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.EventoDto;
import com.gestionatalento.gestiona_talento.Entity.Evento;
import com.gestionatalento.gestiona_talento.Mapper.EventoMapper;
import com.gestionatalento.gestiona_talento.Repository.EventoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.EventoService;


@Service
public class EventoServiceImpl implements EventoService {
    private static final Logger logger = LoggerFactory.getLogger(EventoServiceImpl.class);

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public GenericResponse crearEvento(EventoDto eventoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En EventoDto, en el Request: {}", eventoDto);

            /* Cargamos los datos del DTO al Evento */
            Evento evento = EventoMapper.setEvento(eventoDto);
            logger.info("En EventoMapper, en el Request: {}", evento);
            /* Guardamos el Evento */
            evento = eventoRepository.save(evento);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Evento creado exitosamente");
            genericResponse.setObjeto(evento);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarEvento(EventoDto eventoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En EventoDto, en el Request: {}", eventoDto);
            Optional<Evento> eventoFind = eventoRepository.findById(eventoDto.getNroEvento());
            if (eventoFind.isPresent()) {
                /* Cargamos los datos del DTO al Evento */
                Evento evento = new Evento();
                evento = EventoMapper.setActualizarEvento(eventoDto);
                logger.info("En EventoMapper, en el Request: {}", eventoDto);
                /* Guardar cambios */
                evento = eventoRepository.save(evento);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Evento actualizado exitosamente");
                genericResponse.setObjeto(evento);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un evento con el valor proporcionado. ID: " + eventoDto.getNroEvento());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }   
    }
}

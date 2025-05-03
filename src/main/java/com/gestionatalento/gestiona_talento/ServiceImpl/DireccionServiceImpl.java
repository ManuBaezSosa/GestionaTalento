package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DireccionDto;
import com.gestionatalento.gestiona_talento.Entity.Direccion;
import com.gestionatalento.gestiona_talento.Mapper.DireccionMapper;
import com.gestionatalento.gestiona_talento.Repository.DireccionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.DireccionService;


@Service
public class DireccionServiceImpl implements DireccionService {
    private static final Logger logger = LoggerFactory.getLogger(DireccionServiceImpl.class);

    @Autowired
    DireccionRepository direccionRepository;

    @Override
    public GenericResponse crearDireccion(DireccionDto direccionDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DireccionDto, en el Request: {}", direccionDto);

            /* Cargamos los datos del DTO a la Direccion */
            Direccion direccion = DireccionMapper.setDireccion(direccionDto);
            logger.info("En DireccionMapper, en el Request: {}", direccion);
            /* Guardamos la Direccion */
            direccion = direccionRepository.save(direccion);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Direccion creada exitosamente");
            genericResponse.setObjeto(direccion);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarDireccion(DireccionDto direccionDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DireccionDto, en el Request: {}", direccionDto);
            Optional<Direccion> direccionFind = direccionRepository.findById(direccionDto.getCodDireccion());
            if (direccionFind.isPresent()) {
                /* Cargamos los datos del DTO a la Direccion */
                Direccion direccion = new Direccion();
                direccion = DireccionMapper.setActualizarDireccion(direccionDto);
                logger.info("En DireccionMapper, en el Request: {}", direccionDto);
                /* Guardar cambios */
                direccion = direccionRepository.save(direccion);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Direccion actualizada exitosamente");
                genericResponse.setObjeto(direccion);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra una direccion con el valor proporcionado. ID: " + direccionDto.getCodDireccion());
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

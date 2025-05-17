package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.CorreoDto;
import com.gestionatalento.gestiona_talento.Entity.Correo;
import com.gestionatalento.gestiona_talento.Mapper.CorreoMapper;
import com.gestionatalento.gestiona_talento.Repository.CorreoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.CorreoService;


@Service
public class CorreoServiceImpl implements CorreoService {
    private static final Logger logger = LoggerFactory.getLogger(CorreoServiceImpl.class);

    @Autowired
    CorreoRepository correoRepository;

    @Override
    public GenericResponse crearCorreo(CorreoDto correoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En CorreoDto, en el Request: {}", correoDto);

            /* Cargamos los datos del DTO al Correo */
            Correo correo = CorreoMapper.setCorreo(correoDto);
            logger.info("En CorreoMapper, en el Request: {}", correo);
            /* Guardamos la Correo */
            correo = correoRepository.save(correo);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Correo creado exitosamente");
            genericResponse.setObjeto(correo);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarCorreo(CorreoDto correoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En CorreoDto, en el Request: {}", correoDto);
            Optional<Correo> correoFind = correoRepository.findById(correoDto.getCodCorreo());
            if (correoFind.isPresent()) {
                /* Cargamos los datos del DTO al Correo */
                Correo correo = new Correo();
                correo = CorreoMapper.setActualizarCorreo(correoDto);
                logger.info("En CorreoMapper, en el Request: {}", correoDto);
                /* Guardar cambios */
                correo = correoRepository.save(correo);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Correo actualizado exitosamente");
                genericResponse.setObjeto(correo);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un correo con el valor proporcionado. ID: " + correoDto.getCodCorreo());
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

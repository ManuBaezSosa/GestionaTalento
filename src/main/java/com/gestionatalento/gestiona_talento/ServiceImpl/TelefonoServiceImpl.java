package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.TelefonoDto;
import com.gestionatalento.gestiona_talento.Entity.Telefono;
import com.gestionatalento.gestiona_talento.Mapper.TelefonoMapper;
import com.gestionatalento.gestiona_talento.Repository.TelefonoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.TelefonoService;


@Service
public class TelefonoServiceImpl implements TelefonoService {
    private static final Logger logger = LoggerFactory.getLogger(TelefonoServiceImpl.class);

    @Autowired
    TelefonoRepository telefonoRepository;

    @Override
    public GenericResponse crearTelefono(TelefonoDto telefonoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En TelefonoDto, en el Request: {}", telefonoDto);

            /* Cargamos los datos del DTO al Telefono */
            Telefono telefono = TelefonoMapper.setTelefono(telefonoDto);
            logger.info("En TelefonoMapper, en el Request: {}", telefono);
            /* Guardamos la Telefono */
            telefono = telefonoRepository.save(telefono);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Telefono creado exitosamente");
            genericResponse.setObjeto(telefono);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarTelefono(TelefonoDto telefonoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En TelefonoDto, en el Request: {}", telefonoDto);
            Optional<Telefono> telefonoFind = telefonoRepository.findById(telefonoDto.getCodTelefono());
            if (telefonoFind.isPresent()) {
                /* Cargamos los datos del DTO al Telefono */
                Telefono telefono = new Telefono();
                telefono = TelefonoMapper.setActualizarTelefono(telefonoDto);
                logger.info("En TelefonoMapper, en el Request: {}", telefonoDto);
                /* Guardar cambios */
                telefono = telefonoRepository.save(telefono);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Telefono actualizado exitosamente");
                genericResponse.setObjeto(telefono);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un telefono con el valor proporcionado. ID: " + telefonoDto.getCodTelefono());
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

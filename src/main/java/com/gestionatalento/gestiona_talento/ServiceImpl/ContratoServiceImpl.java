package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.ContratoDto;
import com.gestionatalento.gestiona_talento.Entity.Contrato;
import com.gestionatalento.gestiona_talento.Mapper.ContratoMapper;
import com.gestionatalento.gestiona_talento.Repository.ContratoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.ContratoService;


@Service
public class ContratoServiceImpl implements ContratoService {
    private static final Logger logger = LoggerFactory.getLogger(ContratoServiceImpl.class);

    @Autowired
    ContratoRepository contratoRepository;

    @Override
    public GenericResponse crearContrato(ContratoDto contratoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En ContratoDto, en el Request: {}", contratoDto);

            /* Cargamos los datos del DTO al Contrato */
            Contrato contrato = ContratoMapper.setContrato(contratoDto);
            logger.info("En ContratoMapper, en el Request: {}", contrato);
            /* Guardamos la Contrato */
            contrato = contratoRepository.save(contrato);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Contrato creado exitosamente");
            genericResponse.setObjeto(contrato);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarContrato(ContratoDto contratoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En ContratoDto, en el Request: {}", contratoDto);
            Optional<Contrato> contratoFind = contratoRepository.findById(contratoDto.getNroContrato());
            if (contratoFind.isPresent()) {
                /* Cargamos los datos del DTO al Contrato */
                Contrato contrato = new Contrato();
                contrato = ContratoMapper.setActualizarContrato(contratoDto);
                logger.info("En ContratoMapper, en el Request: {}", contratoDto);
                /* Guardar cambios */
                contrato = contratoRepository.save(contrato);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Contrato actualizado exitosamente");
                genericResponse.setObjeto(contrato);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un contrato con el valor proporcionado. ID: " + contratoDto.getNroContrato());
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

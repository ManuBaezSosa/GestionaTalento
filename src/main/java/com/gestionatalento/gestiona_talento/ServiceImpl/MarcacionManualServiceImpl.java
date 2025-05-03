package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.MarcacionManualDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionManual;
import com.gestionatalento.gestiona_talento.Mapper.MarcacionManualMapper;
import com.gestionatalento.gestiona_talento.Repository.MarcacionManualRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.MarcacionManualService;


@Service
public class MarcacionManualServiceImpl implements MarcacionManualService {
    private static final Logger logger = LoggerFactory.getLogger(MarcacionManualServiceImpl.class);

    @Autowired
    MarcacionManualRepository marcacionManualRepository;

    @Override
    public GenericResponse crearMarcacionManual(MarcacionManualDto marcacionManualDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En MarcacionManualDto, en el Request: {}", marcacionManualDto);

            List<MarcacionManual> marcaciones = new ArrayList<>();
            for (LocalDateTime fecMarcacion : marcacionManualDto.getMarcacion()) {
                /* Cargamos los datos del DTO a la marcacion */
                MarcacionManual marcacionManual = MarcacionManualMapper.setmMarcacionManual(marcacionManualDto, fecMarcacion);
                logger.info("En MarcacionManualMapper, en el Request: {}", marcacionManual);
                /* Guardamos la Marcacion Manual */
                marcacionManual = marcacionManualRepository.save(marcacionManual);
                marcaciones.add(marcacionManual);
            }
            
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Marcaciones creadas exitosamente");
            genericResponse.setObjeto(marcaciones);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarMarcacionManual(MarcacionManualDto marcacionManualDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En MarcacionManualDto, en el Request: {}", marcacionManualDto);
            Optional<MarcacionManual> marcacionManualFind = marcacionManualRepository.findById(marcacionManualDto.getNroMarcacion());
            if (marcacionManualFind.isPresent()) {
                /* Cargamos los datos del DTO a la Marcacion Manual */
                MarcacionManual marcacionManual = new MarcacionManual();
                marcacionManual = MarcacionManualMapper.setActualizaMarcacionManual(marcacionManualFind.get(), marcacionManualDto);
                logger.info("En MarcacionManualMapper, en el Request: {}", marcacionManualDto);
                /* Guardar cambios */
                marcacionManual = marcacionManualRepository.save(marcacionManual);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Marcacion actualizada exitosamente");
                genericResponse.setObjeto(marcacionManual);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra una marcacion manual con el valor proporcionado. ID: " + marcacionManualDto.getNroMarcacion());
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

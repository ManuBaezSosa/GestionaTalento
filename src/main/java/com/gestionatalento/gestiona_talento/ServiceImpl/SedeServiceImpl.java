package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.SedeDto;
import com.gestionatalento.gestiona_talento.Entity.Sede;
import com.gestionatalento.gestiona_talento.Mapper.SedeMapper;
import com.gestionatalento.gestiona_talento.Repository.SedeRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.SedeService;


@Service
public class SedeServiceImpl implements SedeService {
    private static final Logger logger = LoggerFactory.getLogger(SedeServiceImpl.class);

    @Autowired
    SedeRepository sedeRepository;

    @Override
    public GenericResponse crearSede(SedeDto sedeDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En SedeDto, en el Request: {}", sedeDto);

            /* Cargamos los datos del DTO a la Sede */
            Sede sede = SedeMapper.setSede(sedeDto);
            logger.info("En SedeMapper, en el Request: {}", sede);
            /* Guardamos la Sede */
            sede = sedeRepository.save(sede);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Sede creada exitosamente");
            genericResponse.setObjeto(sede);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarSede(SedeDto sedeDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En SedeDto, en el Request: {}", sedeDto);
            Optional<Sede> sedeFind = sedeRepository.findById(sedeDto.getCodSede());
            if (sedeFind.isPresent()) {
                /* Cargamos los datos del DTO a la Sede */
                Sede sede = new Sede();
                sede = SedeMapper.setActualizarSede(sedeDto);
                logger.info("En SedeMapper, en el Request: {}", sedeDto);
                /* Guardar cambios */
                sede = sedeRepository.save(sede);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Sede actualizada exitosamente");
                genericResponse.setObjeto(sede);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra una sede con el valor proporcionado. ID: " + sedeDto.getCodSede());
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

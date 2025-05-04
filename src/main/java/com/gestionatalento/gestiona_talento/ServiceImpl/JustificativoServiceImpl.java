package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.JustificativoDto;
import com.gestionatalento.gestiona_talento.Entity.Justificativo;
import com.gestionatalento.gestiona_talento.Mapper.JustificativoMapper;
import com.gestionatalento.gestiona_talento.Repository.JustificativoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.JustificativoService;


@Service
public class JustificativoServiceImpl implements JustificativoService {
    private static final Logger logger = LoggerFactory.getLogger(JustificativoServiceImpl.class);

    @Autowired
    JustificativoRepository justificativoRepository;

    @Override
    public GenericResponse crearJustificativo(JustificativoDto justificativoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En JustificativoDto, en el Request: {}", justificativoDto);

            List<Justificativo> justificativos = new ArrayList<>();
            for (Date fechaJustificativo : justificativoDto.getFechaJustificativo()) {
                /* Cargamos los datos del DTO al Justificativo */
                Justificativo justificativo = JustificativoMapper.setJustificativo(justificativoDto, fechaJustificativo);
                logger.info("En JustificativoMapper, en el Request: {}", justificativo);
                /* Guardamos la Marcacion Manual */
                justificativo = justificativoRepository.save(justificativo);
                justificativos.add(justificativo);
            }
            
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Justificativos cargados exitosamente");
            genericResponse.setObjeto(justificativos);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarJustificativo(JustificativoDto justificativoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En JustificativoDto, en el Request: {}", justificativoDto);
            Optional<Justificativo> justificativoFind = justificativoRepository.findById(justificativoDto.getNroJustificativo());
            if (justificativoFind.isPresent()) {
                /* Cargamos los datos del DTO al Justificativo */
                Justificativo justificativo = new Justificativo();
                justificativo = JustificativoMapper.setActualizarJustificativo(justificativoFind.get(), justificativoDto);
                logger.info("En JustificativoMapper, en el Request: {}", justificativoDto);
                /* Guardar cambios */
                justificativo = justificativoRepository.save(justificativo);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Justificativo actualizado exitosamente");
                genericResponse.setObjeto(justificativo);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un Justificativo con el valor proporcionado. ID: " + justificativoDto.getNroJustificativo());
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

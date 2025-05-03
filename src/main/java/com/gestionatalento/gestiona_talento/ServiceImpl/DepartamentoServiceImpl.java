package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DepartamentoDto;
import com.gestionatalento.gestiona_talento.Entity.Departamento;
import com.gestionatalento.gestiona_talento.Entity.Direccion;
import com.gestionatalento.gestiona_talento.Mapper.DepartamentoMapper;
import com.gestionatalento.gestiona_talento.Repository.DepartamentoRepository;
import com.gestionatalento.gestiona_talento.Repository.DireccionRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.DepartamentoService;


@Service
public class DepartamentoServiceImpl implements DepartamentoService {
    private static final Logger logger = LoggerFactory.getLogger(DepartamentoServiceImpl.class);

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    DireccionRepository direccionRepository;

    @Override
    public GenericResponse crearDepartamento(DepartamentoDto departamentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DepartamentoDto, en el Request: {}", departamentoDto);
            /* Validamos que exista la direccion */
            Optional<Direccion> direccionFind = direccionRepository.findById(departamentoDto.getDireccion().getCodDireccion());
            if (direccionFind.isPresent()) {
                /* Cargamos los datos del DTO al Departamento */
                Departamento departamento = DepartamentoMapper.setDepartamento(departamentoDto);
                logger.info("En DepartamentoMapper, en el Request: {}", departamento);
                /* Guardamos el Departamento */
                departamento = departamentoRepository.save(departamento);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Departamento creado exitosamente");
                genericResponse.setObjeto(departamento);
                return genericResponse;
            } else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un direccion con el valor proporcionado. ID: " + departamentoDto.getDireccion().getCodDireccion());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarDepartamento(DepartamentoDto departamentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DepartamentoDto, en el Request: {}", departamentoDto);
            Optional<Departamento> departamentoFind = departamentoRepository.findById(departamentoDto.getCodDepartamento());
            if (departamentoFind.isPresent()) {
                /* Validamos que exista la direccion */
                Optional<Direccion> direccionFind = direccionRepository.findById(departamentoDto.getDireccion().getCodDireccion());
                if (direccionFind.isPresent()) {
                    /* Cargamos los datos del DTO al Departamento */
                    Departamento departamento = new Departamento();
                    departamento = DepartamentoMapper.setActualizarDepartamento(departamentoDto);
                    logger.info("En DepartamentoMapper, en el Request: {}", departamentoDto);
                    /* Guardar cambios */
                    departamento = departamentoRepository.save(departamento);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Departamento actualizado exitosamente");
                    genericResponse.setObjeto(departamento);
                    return genericResponse;
                } else{
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("404");
                    genericResponse.setMensaje("No se encuentra un direccion con el valor proporcionado. ID: " + departamentoDto.getDireccion().getCodDireccion());
                    return genericResponse;
                }
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un departamento con el valor proporcionado. ID: " + departamentoDto.getCodDepartamento());
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

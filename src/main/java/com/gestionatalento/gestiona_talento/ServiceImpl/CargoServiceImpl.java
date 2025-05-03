package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.CargoDto;
import com.gestionatalento.gestiona_talento.Entity.Cargo;
import com.gestionatalento.gestiona_talento.Entity.Departamento;
import com.gestionatalento.gestiona_talento.Mapper.CargoMapper;
import com.gestionatalento.gestiona_talento.Repository.CargoRepository;
import com.gestionatalento.gestiona_talento.Repository.DepartamentoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.CargoService;


@Service
public class CargoServiceImpl implements CargoService {
    private static final Logger logger = LoggerFactory.getLogger(CargoServiceImpl.class);

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Override
    public GenericResponse crearCargo(CargoDto cargoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En CargoDto, en el Request: {}", cargoDto);
            /* Validamos que exista el departamento */
            Optional<Departamento> departamentoFind = departamentoRepository.findById(cargoDto.getDepartamento().getCodDepartamento());
            if (departamentoFind.isPresent()) {
                /* Cargamos los datos del DTO al Cargo */
                Cargo cargo = CargoMapper.setCargo(cargoDto);
                logger.info("En CargoMapper, en el Request: {}", cargo);
                /* Guardamos el Cargo */
                cargo = cargoRepository.save(cargo);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Cargo creado exitosamente");
                genericResponse.setObjeto(cargo);
                return genericResponse;
            } else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un departamento con el valor proporcionado. ID: " + cargoDto.getDepartamento().getCodDepartamento());
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
    public GenericResponse actualizarCargo(CargoDto cargoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En CargoDto, en el Request: {}", cargoDto);
            Optional<Cargo> cargoFind = cargoRepository.findById(cargoDto.getCodCargo());
            if (cargoFind.isPresent()) {
                /* Validamos que exista el departamento */
                Optional<Departamento> departamentoFind = departamentoRepository.findById(cargoDto.getDepartamento().getCodDepartamento());
                if (departamentoFind.isPresent()) {
                    /* Cargamos los datos del DTO al Cargo */
                    Cargo cargo = new Cargo();
                    cargo = CargoMapper.setActualizarCargo(cargoDto);
                    logger.info("En CargoMapper, en el Request: {}", cargoDto);
                    /* Guardar cambios */
                    cargo = cargoRepository.save(cargo);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Cargo actualizado exitosamente");
                    genericResponse.setObjeto(cargo);
                    return genericResponse;
                } else{
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("404");
                    genericResponse.setMensaje("No se encuentra un departamento con el valor proporcionado. ID: " + cargoDto.getDepartamento().getCodDepartamento());
                    return genericResponse;
                }
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un cargo con el valor proporcionado. ID: " + cargoDto.getCodCargo());
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

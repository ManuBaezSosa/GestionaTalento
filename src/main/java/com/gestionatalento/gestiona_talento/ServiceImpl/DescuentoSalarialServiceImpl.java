package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Mapper.DescuentoSalarialMapper;
import com.gestionatalento.gestiona_talento.Repository.DescuentoSalarialRepository;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.DescuentoSalarialService;


@Service
public class DescuentoSalarialServiceImpl implements DescuentoSalarialService {
    private static final Logger logger = LoggerFactory.getLogger(DescuentoSalarialServiceImpl.class);

    @Autowired
    DescuentoSalarialRepository descuentoSalarialRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public GenericResponse crearDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DescuentoSalarialDto, en el Request: {}", descuentoSalarialDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(descuentoSalarialDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                DescuentoSalarial descuentoSalarialFind = descuentoSalarialRepository.findByDescuentoSalarial(descuentoSalarialDto.getPeriodo().getNroPeriodo(), descuentoSalarialDto.getEmpleado().getCodEmpleado());
                if (descuentoSalarialFind == null){
                    /* Cargamos los datos del DTO al Descuento Salarial */
                    DescuentoSalarial descuentoSalarial = DescuentoSalarialMapper.setDescuentoSalarial(descuentoSalarialDto, empleado);
                    logger.info("En DescuentoSalarialMapper, en el Request: {}", descuentoSalarial);
                    /* Guardamos el Descuento Salarial */
                    descuentoSalarial = descuentoSalarialRepository.save(descuentoSalarial);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Descuento Salarial creado exitosamente");
                    genericResponse.setObjeto(descuentoSalarial);
                    return genericResponse;
                } else{
                    genericResponse.setCodigoMensaje("409");
                    genericResponse.setMensaje("Ya existe un Descuento Salarial para el empleado y periodo proporcionado. ID: " + descuentoSalarialDto.getEmpleado().getCodEmpleado() + ", " + descuentoSalarialDto.getPeriodo().getNroPeriodo());
                    genericResponse.setObjeto(null);
                    return genericResponse;
                }
            } else{
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + descuentoSalarialDto.getEmpleado().getCodEmpleado());
                genericResponse.setObjeto(null);
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
    public GenericResponse actualizarDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DescuentoSalarialDto, en el Request: {}", descuentoSalarialDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(descuentoSalarialDto.getEmpleado().getCodEmpleado());
            DescuentoSalarial descuentoSalarialFind = descuentoSalarialRepository.findByDescuentoSalarial(descuentoSalarialDto.getPeriodo().getNroPeriodo(), descuentoSalarialDto.getEmpleado().getCodEmpleado());
            if (descuentoSalarialFind != null) {
                /* Cargamos los datos del DTO al Descuento Salarial */
                DescuentoSalarial descuentoSalarial = new DescuentoSalarial();
                descuentoSalarial = DescuentoSalarialMapper.setActualizarDescuentoSalarial(descuentoSalarialDto, empleado);
                logger.info("En DescuentoSalarialMapper, en el Request: {}", descuentoSalarialDto);
                /* Guardar cambios */
                descuentoSalarial = descuentoSalarialRepository.save(descuentoSalarial);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Descuento Salarial actualizado exitosamente");
                genericResponse.setObjeto(descuentoSalarial);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un Descuento Salarial con el valor proporcionado. Periodo: " + descuentoSalarialDto.getPeriodo().getNroPeriodo() + ", ID: " + descuentoSalarialDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse eliminarDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DescuentoSalarialDto, en el Request: {}", descuentoSalarialDto);
            DescuentoSalarial descuentoSalarialFind = descuentoSalarialRepository.findByDescuentoSalarial(descuentoSalarialDto.getPeriodo().getNroPeriodo(), descuentoSalarialDto.getEmpleado().getCodEmpleado());
            if (descuentoSalarialFind != null) {
                descuentoSalarialRepository.deleteByDescuentoSalarial(descuentoSalarialDto.getPeriodo().getNroPeriodo(), descuentoSalarialDto.getEmpleado().getCodEmpleado());
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Descuento Salarial eliminado exitosamente");
                genericResponse.setObjeto(descuentoSalarialDto);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un Descuento Salarial con el valor proporcionado. Periodo: " + descuentoSalarialDto.getPeriodo().getNroPeriodo() + ", ID: " + descuentoSalarialDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse calcularDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DescuentoSalarialDto, en el Request: {}", descuentoSalarialDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(descuentoSalarialDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                /* Cargamos los datos del DTO al Descuento Salarial */
                DescuentoSalarial descuentoSalarial = new DescuentoSalarial();
                descuentoSalarial = DescuentoSalarialMapper.setDescuentoSalarial(descuentoSalarialDto, empleado);
                logger.info("En DescuentoSalarialMapper, en el Request: {}", descuentoSalarialDto);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Descuento Salarial calculado exitosamente");
                genericResponse.setObjeto(descuentoSalarial);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + descuentoSalarialDto.getEmpleado().getCodEmpleado());
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

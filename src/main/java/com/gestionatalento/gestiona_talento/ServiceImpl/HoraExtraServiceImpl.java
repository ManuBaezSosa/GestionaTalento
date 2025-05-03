package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.HoraExtraDto;
import com.gestionatalento.gestiona_talento.Entity.HoraExtra;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Mapper.HoraExtraMapper;
import com.gestionatalento.gestiona_talento.Repository.HoraExtraRepository;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.HoraExtraService;


@Service
public class HoraExtraServiceImpl implements HoraExtraService {
    private static final Logger logger = LoggerFactory.getLogger(HoraExtraServiceImpl.class);

    @Autowired
    HoraExtraRepository horaExtraRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public GenericResponse crearHoraExtra(HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En HoraExtraDto, en el Request: {}", horaExtraDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(horaExtraDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                HoraExtra horaExtraFind = horaExtraRepository.findByHoraExtra(horaExtraDto.getCodPeriodo(), horaExtraDto.getEmpleado().getCodEmpleado());
                if (horaExtraFind == null){
                    /* Cargamos los datos del DTO a la Hora Extra */
                    HoraExtra horaExtra = HoraExtraMapper.setHoraExtra(horaExtraDto, empleado);
                    logger.info("En HoraExtraMapper, en el Request: {}", horaExtra);
                    /* Guardamos la Hora Extra */
                    horaExtra = horaExtraRepository.save(horaExtra);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Hora Extra cargada exitosamente");
                    genericResponse.setObjeto(horaExtra);
                    return genericResponse;
                } else{
                    genericResponse.setCodigoMensaje("409");
                    genericResponse.setMensaje("Ya existe la Hora Extra para el empleado y periodo proporcionado. ID: " + horaExtraDto.getEmpleado().getCodEmpleado() + ", " + horaExtraDto.getCodPeriodo());
                    genericResponse.setObjeto(null);
                    return genericResponse;
                }
            } else{
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + horaExtraDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse actualizarHoraExtra(HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En HoraExtraDto, en el Request: {}", horaExtraDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(horaExtraDto.getEmpleado().getCodEmpleado());
            HoraExtra horaExtraFind = horaExtraRepository.findByHoraExtra(horaExtraDto.getCodPeriodo(), horaExtraDto.getEmpleado().getCodEmpleado());
            if (horaExtraFind != null) {
                /* Cargamos los datos del DTO de la Hora Extra */
                HoraExtra horaExtra = new HoraExtra();
                horaExtra = HoraExtraMapper.setActualizarHoraExtra(horaExtraDto, empleado);
                logger.info("En HoraExtraMapper, en el Request: {}", horaExtraDto);
                /* Guardar cambios */
                horaExtra = horaExtraRepository.save(horaExtra);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Hora Extra actualizada exitosamente");
                genericResponse.setObjeto(horaExtra);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un registro de Hora Extra con el valor proporcionado. Periodo: " + horaExtraDto.getCodPeriodo() + ", ID: " + horaExtraDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse eliminarHoraExtra(HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En HoraExtraDto, en el Request: {}", horaExtraDto);
            HoraExtra horaExtraFind = horaExtraRepository.findByHoraExtra(horaExtraDto.getCodPeriodo(), horaExtraDto.getEmpleado().getCodEmpleado());
            if (horaExtraFind != null) {
                horaExtraRepository.deleteByHoraExtra(horaExtraDto.getCodPeriodo(), horaExtraDto.getEmpleado().getCodEmpleado());
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Hora Extra eliminada exitosamente");
                genericResponse.setObjeto(horaExtraDto);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un registro de Hora Extra con el valor proporcionado. Periodo: " + horaExtraDto.getCodPeriodo() + ", ID: " + horaExtraDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse calcularHoraExtra(HoraExtraDto horaExtraDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En HoraExtraDto, en el Request: {}", horaExtraDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(horaExtraDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                /* Cargamos los datos del DTO a la Hora Extra */
                HoraExtra horaExtra = new HoraExtra();
                horaExtra = HoraExtraMapper.setHoraExtra(horaExtraDto, empleado);
                logger.info("En HoraExtraMapper, en el Request: {}", horaExtraDto);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Hora Extra calculada exitosamente");
                genericResponse.setObjeto(horaExtra);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + horaExtraDto.getEmpleado().getCodEmpleado());
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

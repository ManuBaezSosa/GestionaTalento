package com.gestionatalento.gestiona_talento.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.InventarioDocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.InventarioDocumento;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Mapper.InventarioDocumentoMapper;
import com.gestionatalento.gestiona_talento.Repository.InventarioDocumentoRepository;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.InventarioDocumentoService;


@Service
public class InventarioDocumentoServiceImpl implements InventarioDocumentoService {
    private static final Logger logger = LoggerFactory.getLogger(InventarioDocumentoServiceImpl.class);

    @Autowired
    InventarioDocumentoRepository inventarioDocumentoRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public GenericResponse crearInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En InventarioDocumentoDto, en el Request: {}", inventarioDocumentoDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(inventarioDocumentoDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                InventarioDocumento inventarioDocumentoFind = inventarioDocumentoRepository.findByInventarioDocumento(inventarioDocumentoDto.getPeriodo().getNroPeriodo(), inventarioDocumentoDto.getEmpleado().getCodEmpleado());
                if (inventarioDocumentoFind == null){
                    /* Cargamos los datos del DTO al Inventario de Documento */
                    InventarioDocumento inventarioDocumento = InventarioDocumentoMapper.setInventarioDocumento(inventarioDocumentoDto, empleado);
                    logger.info("En InventarioDocumentoMapper, en el Request: {}", inventarioDocumento);
                    /* Guardamos el Inventario de Documento */
                    inventarioDocumento = inventarioDocumentoRepository.save(inventarioDocumento);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Documento cargado en el inventario exitosamente");
                    genericResponse.setObjeto(inventarioDocumento);
                    return genericResponse;
                } else{
                    genericResponse.setCodigoMensaje("409");
                    genericResponse.setMensaje("Ya existe un Documento en el Inventario para el empleado y periodo proporcionado. ID: " + inventarioDocumentoDto.getEmpleado().getCodEmpleado() + ", " + inventarioDocumentoDto.getPeriodo().getNroPeriodo());
                    genericResponse.setObjeto(null);
                    return genericResponse;
                }
            } else{
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + inventarioDocumentoDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse actualizarInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En InventarioDocumentoDto, en el Request: {}", inventarioDocumentoDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(inventarioDocumentoDto.getEmpleado().getCodEmpleado());
            InventarioDocumento inventarioDocumentoFind = inventarioDocumentoRepository.findByInventarioDocumento(inventarioDocumentoDto.getPeriodo().getNroPeriodo(), inventarioDocumentoDto.getEmpleado().getCodEmpleado());
            if (inventarioDocumentoFind != null) {
                /* Cargamos los datos del DTO al Inventario de Documentos */
                InventarioDocumento inventarioDocumento = new InventarioDocumento();
                inventarioDocumento = InventarioDocumentoMapper.setActualizarInventarioDocumento(inventarioDocumentoDto, empleado);
                logger.info("En InventarioDocumentoMapper, en el Request: {}", inventarioDocumentoDto);
                /* Guardar cambios */
                inventarioDocumento = inventarioDocumentoRepository.save(inventarioDocumento);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Documento del Inventario Actualizado Exitosamente");
                genericResponse.setObjeto(inventarioDocumento);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra el Documento en el Inventario con el valor proporcionado. Periodo: " + inventarioDocumentoDto.getPeriodo().getNroPeriodo() + ", ID: " + inventarioDocumentoDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse eliminarInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En InventarioDocumentoDto, en el Request: {}", inventarioDocumentoDto);
            InventarioDocumento inventarioDocumentoFind = inventarioDocumentoRepository.findByInventarioDocumento(inventarioDocumentoDto.getPeriodo().getNroPeriodo(), inventarioDocumentoDto.getEmpleado().getCodEmpleado());
            if (inventarioDocumentoFind != null) {
                inventarioDocumentoRepository.deleteByInventarioDocumento(inventarioDocumentoDto.getPeriodo().getNroPeriodo(), inventarioDocumentoDto.getEmpleado().getCodEmpleado());
                
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Documento del Inventario eliminado exitosamente");
                genericResponse.setObjeto(inventarioDocumentoDto);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra el Documento en el Inventario con el valor proporcionado. Periodo: " + inventarioDocumentoDto.getPeriodo().getNroPeriodo() + ", ID: " + inventarioDocumentoDto.getEmpleado().getCodEmpleado());
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

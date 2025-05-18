package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.ParametroSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.ParametroSalarial;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Mapper.ParametroSalarialMapper;
import com.gestionatalento.gestiona_talento.Repository.ParametroSalarialRepository;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.ParametroSalarialService;


@Service
public class ParametroSalarialServiceImpl implements ParametroSalarialService {
    private static final Logger logger = LoggerFactory.getLogger(ParametroSalarialServiceImpl.class);

    @Autowired
    ParametroSalarialRepository parametroSalarialRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public GenericResponse obtenerParametroSalarial(ParametroSalarialDto parametroSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En ParametroSalarialDto, en el Request: {}", parametroSalarialDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(parametroSalarialDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                /* Cargamos los datos del DTO al Descuento Salarial */
                ParametroSalarial parametroSalarial = new ParametroSalarial();
            // parametroSalarial = ParametroSalarialMapper.setParametroSalarial(parametroSalarialDto, empleado);
                logger.info("En ParametroSalarialMapper, en el Request: {}", parametroSalarialDto);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Descuento Salarial calculado exitosamente");
                genericResponse.setObjeto(parametroSalarial);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + parametroSalarialDto.getEmpleado().getCodEmpleado());
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

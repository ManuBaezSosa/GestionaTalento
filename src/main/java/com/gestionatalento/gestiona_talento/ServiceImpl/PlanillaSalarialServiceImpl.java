package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.PlanillaSalarial;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Mapper.PlanillaSalarialMapper;
import com.gestionatalento.gestiona_talento.Repository.PlanillaSalarialRepository;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.PlanillaSalarialService;


@Service
public class PlanillaSalarialServiceImpl implements PlanillaSalarialService {
    private static final Logger logger = LoggerFactory.getLogger(PlanillaSalarialServiceImpl.class);

    @Autowired
    PlanillaSalarialRepository planillaSalarialRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public GenericResponse crearPlanillaSalarial(PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En PlanillaSalarialDto, en el Request: {}", planillaSalarialDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(planillaSalarialDto.getEmpleado().getCodEmpleado());
            if (empleado != null) {
                Optional<PlanillaSalarial> planillaSalarialFind = planillaSalarialRepository.findById(planillaSalarialDto.getNroPlanilla());
                if (!planillaSalarialFind.isPresent()){
                    /* Cargamos los datos del DTO a la Planilla Salarial */
                    PlanillaSalarial planillaSalarial = PlanillaSalarialMapper.setPlanillaSalarial(planillaSalarialDto);
                    logger.info("En PlanillaSalarialMapper, en el Request: {}", planillaSalarial);
                    /* Guardamos la Planilla Salarial */
                    planillaSalarial = planillaSalarialRepository.save(planillaSalarial);
                    /* Completamos los mensajes de retorno */
                    genericResponse.setCodigoMensaje("200");
                    genericResponse.setMensaje("Planilla Salarial creada exitosamente");
                    genericResponse.setObjeto(planillaSalarial);
                    return genericResponse;
                } else{
                    genericResponse.setCodigoMensaje("409");
                    genericResponse.setMensaje("Ya existe una Planilla Salarial con las parametrizaciones asignadas al empleado proporcionado. ID: " + planillaSalarialDto.getEmpleado().getCodEmpleado());
                    genericResponse.setObjeto(null);
                    return genericResponse;
                }
            } else{
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo con el valor proporcionado. ID: " + planillaSalarialDto.getEmpleado().getCodEmpleado());
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
    public GenericResponse actualizarPlanillaSalarial(PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En PlanillaSalarialDto, en el Request: {}", planillaSalarialDto);
            Empleado empleado = empleadoRepository.findByIdEmpleadoActivo(planillaSalarialDto.getEmpleado().getCodEmpleado());
            Optional<PlanillaSalarial> planillaSalarialFind = planillaSalarialRepository.findById(planillaSalarialDto.getNroPlanilla());
            if (!planillaSalarialFind.isPresent()) {
                /* Cargamos los datos del DTO a la Planilla Salarial */
                PlanillaSalarial planillaSalarial = new PlanillaSalarial();
                planillaSalarial = PlanillaSalarialMapper.setActualizarPlanillaSalarial(planillaSalarialDto);
                logger.info("En PlanillaSalarialMapper, en el Request: {}", planillaSalarialDto);
                /* Guardar cambios */
                planillaSalarial = planillaSalarialRepository.save(planillaSalarial);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Planilla Salarial actualizada exitosamente");
                genericResponse.setObjeto(planillaSalarial);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra una Planilla Salarial con el valor proporcionado. " + planillaSalarialDto.getNroPlanilla());
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
    public GenericResponse eliminarPlanillaSalarial(PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En PlanillaSalarialDto, en el Request: {}", planillaSalarialDto);
            Optional<PlanillaSalarial> planillaSalarialFind = planillaSalarialRepository.findById(planillaSalarialDto.getNroPlanilla());
            if (!planillaSalarialFind.isPresent()) {
                planillaSalarialRepository.deleteById(planillaSalarialDto.getNroPlanilla());
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Planilla Salarial eliminada exitosamente");
                genericResponse.setObjeto(planillaSalarialDto);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra una Planilla Salarial con el valor proporcionado. ID: " + planillaSalarialDto.getNroPlanilla());
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

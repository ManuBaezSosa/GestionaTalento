package com.gestionatalento.gestiona_talento.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialCabDto;
import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialDetDto;
import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialDto;
import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialInfDto;
import com.gestionatalento.gestiona_talento.Entity.PlanillaSalarial;
import com.gestionatalento.gestiona_talento.Entity.Justificativo;
import com.gestionatalento.gestiona_talento.Entity.ParametroSalarial;
import com.gestionatalento.gestiona_talento.Repository.ParametroSalarialRepository;
import com.gestionatalento.gestiona_talento.Repository.PlanillaSalarialRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.PlanillaSalarialServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/salarios")
public class PlanillaSalarialController {

    @Autowired
    PlanillaSalarialRepository planillaSalarialRepository;

    @Autowired
    PlanillaSalarialServiceImpl planillaSalarialServiceImpl;

    @Autowired
    ParametroSalarialRepository parametroSalarialRepository;

    @PostMapping("/crear")
    public GenericResponse crearPlanillaSalarial(@Valid @RequestBody PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = planillaSalarialServiceImpl.crearPlanillaSalarial(planillaSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarPlanillaSalarial(@Valid @RequestBody PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = planillaSalarialServiceImpl.actualizarPlanillaSalarial(planillaSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @DeleteMapping("/eliminar")
    public GenericResponse eliminarPlanillaSalarial(@Valid @RequestBody PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = planillaSalarialServiceImpl.eliminarPlanillaSalarial(planillaSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarDescuentosSalariales() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<PlanillaSalarial> salarios = planillaSalarialRepository.findAll();
    
            // Verificamos si la lista está vacía
            if (salarios.isEmpty()) {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen planillas salariales registradas");
                return genericResponse;
            }
    
            List<PlanillaSalarial> responseList = new ArrayList<>();
    
            responseList.addAll(salarios);
    
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos todos las planillas de salariales correctamente");
            genericResponse.setObjeto(responseList); 
    
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PostMapping("/parametros/obtenerLista")
    public GenericResponse obtenerParametroSalarial(@Valid @RequestBody PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = planillaSalarialServiceImpl.obtenerParametroSalarial(planillaSalarialDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PostMapping("/informes/obtenerPlanillaSalarial")
    public GenericResponse obtenerPlanillaSalarial(@Valid @RequestBody PlanillaSalarialDto planillaSalarialDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<ParametroSalarial> parametrosSalariales = parametroSalarialRepository.findAll();
            List<PlanillaSalarialInfDto> planillasSalariales = new ArrayList<>();

            // Verificamos si la lista está vacía
            if (parametrosSalariales.isEmpty()) {
                 /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen parametros salariales registradas");
                return genericResponse;
            }

            PlanillaSalarialInfDto planillaSalarialInforme;
            PlanillaSalarialCabDto planillaSalarialCabecera;
            List<PlanillaSalarialDetDto> planillaSalarialDetalleList;

            for (ParametroSalarial parametroSalarial : parametrosSalariales) {
                planillaSalarialInforme = new PlanillaSalarialInfDto();
                planillaSalarialCabecera = new PlanillaSalarialCabDto();
                planillaSalarialDetalleList = new ArrayList<>();

                planillaSalarialCabecera.setPeriodo(planillaSalarialDto.getPeriodo());
                planillaSalarialCabecera.setPresupuesto(parametroSalarial.getPresupuesto());
                planillaSalarialCabecera.setPrograma(parametroSalarial.getPrograma());
                planillaSalarialCabecera.setSituacionLaboral(parametroSalarial.getSituacionLaboral());
                planillaSalarialCabecera.setFuenteFinanciamiento(parametroSalarial.getFuenteFinanciamiento());
                planillaSalarialCabecera.setObjetoGasto(parametroSalarial.getObjetoGasto());
                planillaSalarialCabecera.setSubprograma(parametroSalarial.getSubPrograma());
                
                planillaSalarialInforme.setCabecera(planillaSalarialCabecera);

                List<PlanillaSalarial> detallesSalariales = planillaSalarialRepository.findByCabecera(parametroSalarial.getSituacionLaboral().getCodSituacionLaboral(),
                                                                                                       parametroSalarial.getPresupuesto().getCodPresupuesto(),
                                                                                                       parametroSalarial.getPrograma().getCodPrograma(),
                                                                                                       parametroSalarial.getFuenteFinanciamiento().getCodFuenteFinanciamiento(),
                                                                                                       parametroSalarial.getObjetoGasto().getCodObjetoGasto(),
                                                                                                       parametroSalarial.getSubPrograma().getCodSubprograma());

                PlanillaSalarialDetDto planillaSalarialDetDto;
                for (PlanillaSalarial planillaSalarial : detallesSalariales) {
                    planillaSalarialDetDto = new PlanillaSalarialDetDto();
                    planillaSalarialDetDto.setEmpleado(planillaSalarial.getEmpleado());
                    planillaSalarialDetDto.setAsignacion(planillaSalarial.getAsignacion());
                    planillaSalarialDetDto.setGradoSalarial(planillaSalarial.getGradoSalarial());
                    planillaSalarialDetDto.setNroPlanilla(planillaSalarial.getNroPlanilla());
                    planillaSalarialDetalleList.add(planillaSalarialDetDto);
                }

                planillaSalarialInforme.setCabecera(planillaSalarialCabecera);
                planillaSalarialInforme.setDetalle(planillaSalarialDetalleList);
                planillasSalariales.add(planillaSalarialInforme);
            }
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los Parametros Salariales correctamente");
            genericResponse.setObjeto(planillasSalariales);

            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

}

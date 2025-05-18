package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.PlanillaSalarial;
import com.gestionatalento.gestiona_talento.Entity.Presupuesto;
import com.gestionatalento.gestiona_talento.Entity.Programa;
import com.gestionatalento.gestiona_talento.Entity.SituacionLaboral;
import com.gestionatalento.gestiona_talento.Entity.Subprograma;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.FuenteFinanciamiento;
import com.gestionatalento.gestiona_talento.Entity.GradoSalarial;
import com.gestionatalento.gestiona_talento.Entity.ObjetoGasto;
import com.gestionatalento.gestiona_talento.Entity.Periodo;

@Mapper
public interface PlanillaSalarialMapper {

    public static PlanillaSalarial setPlanillaSalarial(PlanillaSalarialDto planillaSalarioDto){
        PlanillaSalarial planillaSalario = new PlanillaSalarial();

        Empleado empleado = new Empleado();
        empleado.setCodEmpleado(planillaSalarioDto.getEmpleado().getCodEmpleado());
        planillaSalario.setEmpleado(empleado);

        Periodo periodo = new Periodo();
        periodo.setNroPeriodo(planillaSalarioDto.getPeriodo().getNroPeriodo());
        planillaSalario.setPeriodo(periodo);
        
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setCodPresupuesto(planillaSalarioDto.getPresupuesto().getCodPresupuesto());
        planillaSalario.setPresupuesto(presupuesto);

        Programa programa = new Programa();
        programa.setCodPrograma(planillaSalarioDto.getPrograma().getCodPrograma());
        planillaSalario.setPrograma(programa);

        SituacionLaboral situacionLaboral = new SituacionLaboral();
        situacionLaboral.setCodSituacionLaboral(planillaSalarioDto.getSituacionLaboral().getCodSituacionLaboral());
        planillaSalario.setSituacionLaboral(situacionLaboral);

        FuenteFinanciamiento fuenteFinanciamiento = new FuenteFinanciamiento();
        fuenteFinanciamiento.setCodFuenteFinanciamiento(planillaSalarioDto.getFuenteFinanciamiento().getCodFuenteFinanciamiento());
        planillaSalario.setFuenteFinanciamiento(fuenteFinanciamiento);

        ObjetoGasto objetoGasto = new ObjetoGasto();
        objetoGasto.setCodObjetoGasto(planillaSalarioDto.getObjetoGasto().getCodObjetoGasto());
        planillaSalario.setObjetoGasto(objetoGasto);

        Subprograma subprograma = new Subprograma();
        subprograma.setCodSubprograma(planillaSalarioDto.getSubprograma().getCodSubprograma());
        planillaSalario.setSubprograma(subprograma);

        GradoSalarial gradoSalarial = new GradoSalarial();
        gradoSalarial.setNroGrado(planillaSalarioDto.getGradoSalarial().getNroGrado());
        planillaSalario.setGradoSalarial(gradoSalarial);

        planillaSalario.setAsignacion(planillaSalarioDto.getAsignacion());

        return planillaSalario;
    }

    public static PlanillaSalarial setActualizarPlanillaSalarial(PlanillaSalarialDto planillaSalarioDto){
        PlanillaSalarial planillaSalario = new PlanillaSalarial();

        planillaSalario.setNroPlanilla(planillaSalarioDto.getNroPlanilla());
        
        Empleado empleado = new Empleado();
        empleado.setCodEmpleado(planillaSalarioDto.getEmpleado().getCodEmpleado());
        planillaSalario.setEmpleado(empleado);

        Periodo periodo = new Periodo();
        periodo.setNroPeriodo(planillaSalarioDto.getPeriodo().getNroPeriodo());
        planillaSalario.setPeriodo(periodo);
        
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setCodPresupuesto(planillaSalarioDto.getPresupuesto().getCodPresupuesto());
        planillaSalario.setPresupuesto(presupuesto);

        Programa programa = new Programa();
        programa.setCodPrograma(planillaSalarioDto.getPrograma().getCodPrograma());
        planillaSalario.setPrograma(programa);

        SituacionLaboral situacionLaboral = new SituacionLaboral();
        situacionLaboral.setCodSituacionLaboral(planillaSalarioDto.getSituacionLaboral().getCodSituacionLaboral());
        planillaSalario.setSituacionLaboral(situacionLaboral);

        FuenteFinanciamiento fuenteFinanciamiento = new FuenteFinanciamiento();
        fuenteFinanciamiento.setCodFuenteFinanciamiento(planillaSalarioDto.getFuenteFinanciamiento().getCodFuenteFinanciamiento());
        planillaSalario.setFuenteFinanciamiento(fuenteFinanciamiento);

        ObjetoGasto objetoGasto = new ObjetoGasto();
        objetoGasto.setCodObjetoGasto(planillaSalarioDto.getObjetoGasto().getCodObjetoGasto());
        planillaSalario.setObjetoGasto(objetoGasto);

        Subprograma subprograma = new Subprograma();
        subprograma.setCodSubprograma(planillaSalarioDto.getSubprograma().getCodSubprograma());
        planillaSalario.setSubprograma(subprograma);

        GradoSalarial gradoSalarial = new GradoSalarial();
        gradoSalarial.setNroGrado(planillaSalarioDto.getGradoSalarial().getNroGrado());
        planillaSalario.setGradoSalarial(gradoSalarial);

        planillaSalario.setAsignacion(planillaSalarioDto.getAsignacion());

        return planillaSalario;
    }
    
}

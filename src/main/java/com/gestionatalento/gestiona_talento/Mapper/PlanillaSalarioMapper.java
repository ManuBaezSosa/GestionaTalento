package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.PlanillaSalarioDto;
import com.gestionatalento.gestiona_talento.Entity.PlanillaSalario;
import com.gestionatalento.gestiona_talento.Entity.PlanillaSalarioPK;
import com.gestionatalento.gestiona_talento.Entity.GradoSalarial;

@Mapper
public interface PlanillaSalarioMapper {

    public static PlanillaSalario setPlanillaSalario(PlanillaSalarioDto planillaSalarioDto){
        PlanillaSalario planillaSalario = new PlanillaSalario();
        PlanillaSalarioPK planillaSalarioPK = new PlanillaSalarioPK();

        planillaSalarioPK.setCodEmpleado(planillaSalarioDto.getEmpleado().getCodEmpleado());
        planillaSalarioPK.setCodPeriodo(planillaSalarioDto.getPeriodo().getCodPeriodo());
        planillaSalarioPK.setCodPresupuesto(planillaSalarioDto.getPresupuesto().getCodPresupuesto());
        planillaSalarioPK.setCodPrograma(planillaSalarioDto.getPrograma().getCodPrograma());
        planillaSalarioPK.setCodSituacionLaboral(planillaSalarioDto.getSituacionLaboral().getCodSituacionLaboral());
        planillaSalarioPK.setCodFuenteFinanciamiento(planillaSalarioDto.getFuenteFinanciamiento().getCodFuenteFinanciamiento());
        planillaSalarioPK.setCodObjetoGasto(planillaSalarioDto.getObjetoGasto().getCodObjetoGasto());
        planillaSalarioPK.setCodSubprograma(planillaSalarioDto.getSubprograma().getCodSubPrograma());

        planillaSalario.setId(planillaSalarioPK);

        GradoSalarial gradoSalarial = new GradoSalarial();
        gradoSalarial.setNroGrado(planillaSalarioDto.getGradoSalarial().getNroGrado());

        planillaSalario.setGradoSalarial(gradoSalarial);
        planillaSalario.setAsignacion(planillaSalarioDto.getAsignacion());

        return planillaSalario;
    }
    
}

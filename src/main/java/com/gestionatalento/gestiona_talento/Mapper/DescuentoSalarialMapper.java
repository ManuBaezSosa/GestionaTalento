package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.DescuentoSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarial;
import com.gestionatalento.gestiona_talento.Entity.DescuentoSalarialPK;
import com.gestionatalento.gestiona_talento.Entity.Empleado;

@Mapper
public interface DescuentoSalarialMapper {

    public static DescuentoSalarial setDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto, Empleado empleado){
        DescuentoSalarial descuentoSalarial = new DescuentoSalarial();
        DescuentoSalarialPK descuentoSalarialPK = new DescuentoSalarialPK();

        descuentoSalarialPK.setCodEmpleado(empleado.getCodEmpleado());
        descuentoSalarialPK.setCodPeriodo(descuentoSalarialDto.getCodPeriodo());

        descuentoSalarial.setId(descuentoSalarialPK);
        descuentoSalarial.setEmpleado(empleado);
        descuentoSalarial.setAusencia(descuentoSalarialDto.getAusencia());
        descuentoSalarial.setEntradaTardia(descuentoSalarialDto.getEntradaTardia());
        descuentoSalarial.setSalidaAnticipada(descuentoSalarialDto.getSalidaAnticipada());
        descuentoSalarial.setObservacion(descuentoSalarialDto.getObservacion());
        descuentoSalarial.setMonto(calcularDescuentoSalarial(descuentoSalarialDto, empleado));

        return descuentoSalarial;
    }
    public static DescuentoSalarial setActualizarDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto, Empleado empleado) {
        DescuentoSalarial descuentoSalarial = new DescuentoSalarial();
        DescuentoSalarialPK descuentoSalarialPK = new DescuentoSalarialPK();

        descuentoSalarialPK.setCodEmpleado(descuentoSalarialDto.getEmpleado().getCodEmpleado());
        descuentoSalarialPK.setCodPeriodo(descuentoSalarialDto.getCodPeriodo());

        descuentoSalarial.setId(descuentoSalarialPK);
        descuentoSalarial.setEmpleado(empleado);
        descuentoSalarial.setAusencia(descuentoSalarialDto.getAusencia());
        descuentoSalarial.setEntradaTardia(descuentoSalarialDto.getEntradaTardia());
        descuentoSalarial.setSalidaAnticipada(descuentoSalarialDto.getSalidaAnticipada());
        descuentoSalarial.setMonto(calcularDescuentoSalarial(descuentoSalarialDto, empleado));
        descuentoSalarial.setObservacion(descuentoSalarialDto.getObservacion());

        return descuentoSalarial;
    }
    
    private static Double calcularDescuentoSalarial(DescuentoSalarialDto descuentoSalarialDto, Empleado empleado){
        Double jornal, mtoEntradasTardias, mtoSalidasAnticipadas, mtoAusencias, mtoDescuento;

        jornal = Math.round((empleado.getAsignacion() / 30) * 100.0) / 100.0;
        if (descuentoSalarialDto.getEntradaTardia() > 2) {
            mtoEntradasTardias = (jornal * (descuentoSalarialDto.getEntradaTardia() - 2));
        }else{
            mtoEntradasTardias = 0.00;
        }
        mtoSalidasAnticipadas = (jornal * descuentoSalarialDto.getSalidaAnticipada());
        mtoAusencias = (jornal * descuentoSalarialDto.getAusencia());
        mtoDescuento = mtoEntradasTardias + mtoSalidasAnticipadas + mtoAusencias;

        return mtoDescuento;
    }
}

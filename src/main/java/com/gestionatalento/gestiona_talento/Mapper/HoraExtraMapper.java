package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.HoraExtraDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.HoraExtra;
import com.gestionatalento.gestiona_talento.Entity.HoraExtraPK;

@Mapper
public interface HoraExtraMapper {

    public static HoraExtra setHoraExtra(HoraExtraDto horaExtraDto, Empleado empleado){
        HoraExtra horaExtra = new HoraExtra();
        HoraExtraPK horaExtraPK = new HoraExtraPK();

        horaExtraPK.setCodEmpleado(empleado.getCodEmpleado());
        horaExtraPK.setNroPeriodo(horaExtraDto.getPeriodo().getNroPeriodo());

        horaExtra.setId(horaExtraPK);
        horaExtra.setEmpleado(empleado);
        horaExtra.setHoraExtra(horaExtraDto.getHoraExtra());
        horaExtra.setMonto(calcularHoraExtra(horaExtraDto, empleado));
        horaExtra.setObservacion(horaExtraDto.getObservacion());
        horaExtra.setExoneraEntrada(horaExtraDto.getExoneraEntrada());

        return horaExtra;
    }
    public static HoraExtra setActualizarHoraExtra(HoraExtraDto horaExtraDto, Empleado empleado) {
        HoraExtra horaExtra = new HoraExtra();
        HoraExtraPK horaExtraPK = new HoraExtraPK();

        horaExtraPK.setCodEmpleado(empleado.getCodEmpleado());
        horaExtraPK.setNroPeriodo(horaExtraDto.getPeriodo().getNroPeriodo());

        horaExtra.setId(horaExtraPK);
        horaExtra.setEmpleado(empleado);
        horaExtra.setHoraExtra(horaExtraDto.getHoraExtra());
        horaExtra.setMonto(calcularHoraExtra(horaExtraDto, empleado));
        horaExtra.setObservacion(horaExtraDto.getObservacion());
        horaExtra.setExoneraEntrada(horaExtraDto.getExoneraEntrada());

        return horaExtra;
    }
    
    private static Double calcularHoraExtra(HoraExtraDto horaExtraDto, Empleado empleado){
        Double mtoHoraExtraDia, mtoHoraExtra;

        mtoHoraExtraDia = (double) Math.round((((empleado.getAsignacion() / 26) / 6) * 1.5));
        mtoHoraExtra = mtoHoraExtraDia * horaExtraDto.getHoraExtra();

        return mtoHoraExtra;
    }
}

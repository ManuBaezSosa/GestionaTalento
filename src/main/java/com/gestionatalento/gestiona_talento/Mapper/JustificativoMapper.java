package com.gestionatalento.gestiona_talento.Mapper;


import java.sql.Date;

import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.JustificativoDto;
import com.gestionatalento.gestiona_talento.Entity.Justificativo;
import com.gestionatalento.gestiona_talento.Entity.TipoJustificativo;

@Mapper
public interface JustificativoMapper {

    public static Justificativo setJustificativo(JustificativoDto justificativoDto, Date fecJustificativo){
        Justificativo justificativo = new Justificativo();
        TipoJustificativo tipoJustificativo = new TipoJustificativo();

        justificativo.setFecha(fecJustificativo);
        justificativo.setDescripcion(justificativoDto.getDescripcion());
        justificativo.setEstado(justificativoDto.getEstado());
        justificativo.setPersona(justificativoDto.getPersona());

        tipoJustificativo.setCodTipJustificativo(justificativoDto.getTipoJustificativo().getCodTipJustificativo());
        justificativo.setTipoJustificativo(tipoJustificativo);

        return justificativo;
    }
    public static Justificativo setActualizarJustificativo(Justificativo justificativo, JustificativoDto justificativoDto) {
        Justificativo justificativoActualizado = new Justificativo();

        justificativoActualizado.setNroJustificativo(justificativo.getNroJustificativo());
        justificativoActualizado.setFecha(justificativo.getFecha());
        justificativoActualizado.setDescripcion(justificativo.getDescripcion());
        justificativoActualizado.setEstado(justificativoDto.getEstado());
        justificativoActualizado.setPersona(justificativo.getPersona());

        justificativo.setTipoJustificativo(justificativo.getTipoJustificativo());

        return justificativoActualizado;
    }
    
}

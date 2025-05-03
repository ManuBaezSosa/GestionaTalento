package com.gestionatalento.gestiona_talento.Mapper;


import java.time.LocalDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.MarcacionManualDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionManual;

@Mapper
public interface MarcacionManualMapper {

    public static MarcacionManual setmMarcacionManual(MarcacionManualDto marcacionManualDto, LocalDateTime fecMarcacion){
        MarcacionManual marcacionManual = new MarcacionManual();
        marcacionManual.setFecMarcacion(fecMarcacion);
        marcacionManual.setPersona(marcacionManualDto.getPersona());

        return marcacionManual;
    }
    public static MarcacionManual setActualizaMarcacionManual(MarcacionManual marcacionManual, MarcacionManualDto marcacionManualDto) {
        MarcacionManual marcacionManualActualizada = new MarcacionManual();
        marcacionManualActualizada.setNroMarcacion(marcacionManual.getNroMarcacion());
        marcacionManualActualizada.setFecMarcacion(marcacionManualDto.getMarcacion().get(0));
        marcacionManualActualizada.setPersona(marcacionManual.getPersona());

        return marcacionManualActualizada;
    }
    
}

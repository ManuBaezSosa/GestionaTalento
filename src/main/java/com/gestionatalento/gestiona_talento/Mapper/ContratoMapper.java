package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.ContratoDto;
import com.gestionatalento.gestiona_talento.Entity.Contrato;

@Mapper
public interface ContratoMapper {

    public static Contrato setContrato(ContratoDto contratoDto){
        Contrato contrato = new Contrato();

        contrato.setPeriodo(contratoDto.getPeriodo());
        contrato.setPersona(contratoDto.getPersona());
        contrato.setNroDocumento(contratoDto.getNroDocumento());
        contrato.setNombres(contratoDto.getNombres());
        contrato.setApellidos(contratoDto.getApellidos());
        contrato.setAsignacion(contratoDto.getAsignacion());
        contrato.setMontoLetras(contratoDto.getMontoLetras());
        contrato.setEstado(contratoDto.getEstado());
        contrato.setFecDesde(contratoDto.getFecDesde());
        contrato.setFecHasta(contratoDto.getFecHasta());
        contrato.setSituacionLaboral(contratoDto.getSituacionLaboral());
        contrato.setNomFirmante1(contratoDto.getNomFirmante1());
        contrato.setNomFirmante2(contratoDto.getNomFirmante2());
        contrato.setObservacion(contratoDto.getObservacion());

        return contrato;
    }
    public static Contrato setActualizarContrato(ContratoDto contratoDto) {
        Contrato contrato = new Contrato();
        
        contrato.setNroContrato(contratoDto.getNroContrato());
        contrato.setPeriodo(contratoDto.getPeriodo());
        contrato.setPersona(contratoDto.getPersona());
        contrato.setNroDocumento(contratoDto.getNroDocumento());
        contrato.setNombres(contratoDto.getNombres());
        contrato.setApellidos(contratoDto.getApellidos());
        contrato.setAsignacion(contratoDto.getAsignacion());
        contrato.setMontoLetras(contratoDto.getMontoLetras());
        contrato.setEstado(contratoDto.getEstado());
        contrato.setFecDesde(contratoDto.getFecDesde());
        contrato.setFecHasta(contratoDto.getFecHasta());
        contrato.setSituacionLaboral(contratoDto.getSituacionLaboral());
        contrato.setNomFirmante1(contratoDto.getNomFirmante1());
        contrato.setNomFirmante2(contratoDto.getNomFirmante2());
        contrato.setObservacion(contratoDto.getObservacion());

        return contrato;
    }
    
}

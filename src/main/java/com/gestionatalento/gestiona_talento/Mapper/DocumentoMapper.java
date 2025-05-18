package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.Documento;

@Mapper
public interface DocumentoMapper {

    public static Documento setDocumento(DocumentoDto documentoDto){
        Documento documento = new Documento();
        documento.setPersona(documentoDto.getPersona());
        documento.setNomArchivo(documentoDto.getNomArchivo());
        documento.setEstado(documentoDto.getEstado());
        documento.setFecDocumento(documentoDto.getFecDocumento());
        documento.setObservacion(documentoDto.getObservacion());

        return documento;
    }
    public static Documento setActualizarDocumento(DocumentoDto documentoDto) {
        Documento documento = new Documento();
        documento.setNroDocumento(documentoDto.getNroDocumento());
        documento.setPersona(documentoDto.getPersona());
        documento.setNomArchivo(documentoDto.getNomArchivo());
        documento.setEstado(documentoDto.getEstado());
        documento.setFecDocumento(documentoDto.getFecDocumento());
        documento.setObservacion(documentoDto.getObservacion());

        return documento;
    }
}

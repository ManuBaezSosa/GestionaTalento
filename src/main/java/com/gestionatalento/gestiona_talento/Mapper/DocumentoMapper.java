package com.gestionatalento.gestiona_talento.Mapper;


import java.io.IOException;

import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.Documento;

@Mapper
public interface DocumentoMapper {

    public static Documento setDocumento(DocumentoDto documentoDto, MultipartFile archivo) throws IOException{
        Documento documento = new Documento();
        documento.setPersona(documentoDto.getPersona());
        documento.setTipoDocumento(documentoDto.getTipoDocumento());
        documento.setEstado(documentoDto.getEstado());
        documento.setFecDocumento(documentoDto.getFecDocumento());
        documento.setFecVencimiento(documentoDto.getFecVencimiento());
        documento.setObservacion(documentoDto.getObservacion());
        documento.setNomArchivo(archivo.getOriginalFilename());
        documento.setTipArchivo(archivo.getContentType());
        documento.setTamArchivo(archivo.getSize());
        documento.setArchivo(archivo.getBytes());

        return documento;
    }
    public static Documento setActualizarDocumento(DocumentoDto documentoDto) {
        Documento documento = new Documento();
        documento.setNroDocumento(documentoDto.getNroDocumento());
        documento.setPersona(documentoDto.getPersona());
        documento.setNomArchivo(documentoDto.getNomArchivo());
        documento.setEstado(documentoDto.getEstado());
        documento.setFecDocumento(documentoDto.getFecDocumento());
        documento.setFecVencimiento(documentoDto.getFecVencimiento());
        documento.setObservacion(documentoDto.getObservacion());

        return documento;
    }
}

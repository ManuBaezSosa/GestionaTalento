package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.InventarioDocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.InventarioDocumento;
import com.gestionatalento.gestiona_talento.Entity.InventarioDocumentoPK;
import com.gestionatalento.gestiona_talento.Entity.Empleado;

@Mapper
public interface InventarioDocumentoMapper {

    public static InventarioDocumento setInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto, Empleado empleado){
        InventarioDocumento inventarioDocumento = new InventarioDocumento();
        InventarioDocumentoPK inventarioDocumentoPK = new InventarioDocumentoPK();

        inventarioDocumentoPK.setCodEmpleado(empleado.getCodEmpleado());
        inventarioDocumentoPK.setNroPeriodo(inventarioDocumentoDto.getPeriodo().getNroPeriodo());

        inventarioDocumento.setId(inventarioDocumentoPK);
        inventarioDocumento.setEmpleado(empleado);
        inventarioDocumento.setEstado(inventarioDocumentoDto.getEstado());
        inventarioDocumento.setComentario(inventarioDocumentoDto.getComentario());

        return inventarioDocumento;
    }
    public static InventarioDocumento setActualizarInventarioDocumento(InventarioDocumentoDto inventarioDocumentoDto, Empleado empleado) {
        InventarioDocumento inventarioDocumento = new InventarioDocumento();
        InventarioDocumentoPK inventarioDocumentoPK = new InventarioDocumentoPK();

        inventarioDocumentoPK.setCodEmpleado(inventarioDocumentoDto.getEmpleado().getCodEmpleado());
        inventarioDocumentoPK.setNroPeriodo(inventarioDocumentoDto.getPeriodo().getNroPeriodo());

        inventarioDocumento.setId(inventarioDocumentoPK);
        inventarioDocumento.setEmpleado(empleado);
        inventarioDocumento.setPeriodo(inventarioDocumentoDto.getPeriodo());
        inventarioDocumento.setEstado(inventarioDocumentoDto.getEstado());
        inventarioDocumento.setComentario(inventarioDocumentoDto.getComentario());

        return inventarioDocumento;
    }

}

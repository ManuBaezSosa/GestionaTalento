package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionatalento.gestiona_talento.Dto.DocumentoDto;
import com.gestionatalento.gestiona_talento.Entity.Documento;
import com.gestionatalento.gestiona_talento.Mapper.DocumentoMapper;
import com.gestionatalento.gestiona_talento.Repository.DocumentoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.DocumentoService;


@Service
public class DocumentoServiceImpl implements DocumentoService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentoServiceImpl.class);

    @Autowired
    DocumentoRepository documentoRepository;

    @Override
    public GenericResponse crearDocumento(DocumentoDto documentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DocumentoDto, en el Request: {}", documentoDto);

            /* Cargamos los datos del DTO al Documento */
            Documento documento = DocumentoMapper.setDocumento(documentoDto);
            logger.info("En DocumentoMapper, en el Request: {}", documento);
            /* Guardamos la Documento */
            documento = documentoRepository.save(documento);
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Documento creado exitosamente");
            genericResponse.setObjeto(documento);
            return genericResponse;
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }        
    }

    @Override
    public GenericResponse actualizarDocumento(DocumentoDto documentoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            logger.info("En DocumentoDto, en el Request: {}", documentoDto);
            Optional<Documento> documentoFind = documentoRepository.findById(documentoDto.getNroDocumento());
            if (documentoFind.isPresent()) {
                /* Cargamos los datos del DTO al Documento */
                Documento documento = new Documento();
                documento = DocumentoMapper.setActualizarDocumento(documentoDto);
                logger.info("En DocumentoMapper, en el Request: {}", documentoDto);
                /* Guardar cambios */
                documento = documentoRepository.save(documento);
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Documento actualizado exitosamente");
                genericResponse.setObjeto(documento);
                return genericResponse;
            }else{
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No se encuentra un documento con el valor proporcionado. ID: " + documentoDto.getNroDocumento());
                return genericResponse;
            }
        }catch (Exception e){
            /* Completamos los mensajes de retorno */
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }   
    }
}

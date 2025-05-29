package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.MarcacionExportadaTmpDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionExportadaTmp;
import com.gestionatalento.gestiona_talento.Entity.MarcacionExportadaTmpPK;
import com.gestionatalento.gestiona_talento.Entity.UsuarioExportadoTmp;
import com.gestionatalento.gestiona_talento.Repository.MarcacionExportadaTmpRepository;
import com.gestionatalento.gestiona_talento.Repository.UsuarioExportadoTmpRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.MarcacionExportadaTmpService;


@Service
public class MarcacionExportadaTmpServiceImpl implements MarcacionExportadaTmpService {

    @Autowired
    UsuarioExportadoTmpRepository usuarioExportadoTmpRepository;

    @Autowired
    MarcacionExportadaTmpRepository marcacionExportadaRepository;

    @Override
    public GenericResponse cargarMarcacion(MultipartFile file) {
        GenericResponse genericResponse = new GenericResponse();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;
            List<MarcacionExportadaTmp> marcacionesExportadas = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Salta encabezado
                    continue;
                }

                String[] datos = line.split(";");
                MarcacionExportadaTmpDto marcacionExportadaTmpDto = new MarcacionExportadaTmpDto();

                UsuarioExportadoTmp usuarioExportadoTmp = new UsuarioExportadoTmp();
                usuarioExportadoTmp.setCodUsuario(Long.parseLong(datos[0].trim()));
                marcacionExportadaTmpDto.setUsuarioExportadoTmp(usuarioExportadoTmp);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime fechaMarcacion = LocalDateTime.parse(datos[1].trim(), formatter);
                marcacionExportadaTmpDto.setFecMarcacion(fechaMarcacion);

                // Convertir DTO a entidad
                MarcacionExportadaTmp marcacionExportadaTmp = new MarcacionExportadaTmp();
                marcacionExportadaTmp.setUsuarioExportadoTmp(marcacionExportadaTmpDto.getUsuarioExportadoTmp());
                
                MarcacionExportadaTmpPK marcacionExportadaTmpPK = new MarcacionExportadaTmpPK();
                marcacionExportadaTmpPK.setCodUsuario(marcacionExportadaTmpDto.getUsuarioExportadoTmp().getCodUsuario());
                marcacionExportadaTmpPK.setFecMarcacion(marcacionExportadaTmpDto.getFecMarcacion());
                marcacionExportadaTmp.setId(marcacionExportadaTmpPK);

                marcacionesExportadas.add(marcacionExportadaTmp);
            }
            try{
                for (MarcacionExportadaTmp marcacion : marcacionesExportadas) {
                    Long codUsuario = marcacion.getUsuarioExportadoTmp().getCodUsuario();
                
                    Optional<UsuarioExportadoTmp>  usuarioExportadoTmp = usuarioExportadoTmpRepository.findById(codUsuario);
                    if (!usuarioExportadoTmp.isPresent()){
                        genericResponse.setCodigoMensaje("404");
                        genericResponse.setMensaje("No se encuentra el usuario ingresado en la planilla. ID: " + codUsuario);
                        return genericResponse;
                    }
                }
                marcacionExportadaRepository.saveAll(marcacionesExportadas);
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Han sido importados los datos de marcaciones correctamente");
                genericResponse.setObjeto(null);
                return genericResponse;    
            }catch (Exception e){
                /* Completamos los mensajes de retorno */
                genericResponse.setCodigoMensaje("500");
                genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
                return genericResponse;
            } 

        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            genericResponse.setObjeto(null);
            return genericResponse;
        }
    }
}

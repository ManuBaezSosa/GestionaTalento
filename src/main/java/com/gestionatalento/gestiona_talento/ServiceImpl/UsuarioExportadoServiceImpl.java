package com.gestionatalento.gestiona_talento.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.MarcacionManualDto;
import com.gestionatalento.gestiona_talento.Dto.UsuarioExportadoDto;
import com.gestionatalento.gestiona_talento.Dto.UsuarioExportadoTmpDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionManual;
import com.gestionatalento.gestiona_talento.Entity.UsuarioExportadoTmp;
import com.gestionatalento.gestiona_talento.Mapper.MarcacionManualMapper;
import com.gestionatalento.gestiona_talento.Repository.MarcacionManualRepository;
import com.gestionatalento.gestiona_talento.Repository.UsuarioExportadoTmpRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.Service.MarcacionManualService;
import com.gestionatalento.gestiona_talento.Service.UsuarioExportadoService;


@Service
public class UsuarioExportadoServiceImpl implements UsuarioExportadoService {
    private static final Logger logger = LoggerFactory.getLogger(MarcacionManualServiceImpl.class);

    @Autowired
    UsuarioExportadoTmpRepository usuarioExportadoTmpRepository;

    @Override
    public GenericResponse cargarUsuario(MultipartFile file) {
        GenericResponse genericResponse = new GenericResponse();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;
            List<UsuarioExportadoTmp> usuariosExportados = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Salta encabezado
                    continue;
                }

                String[] datos = line.split(";");
                UsuarioExportadoTmpDto usuarioExportadoTmpDto = new UsuarioExportadoTmpDto();
                usuarioExportadoTmpDto.setCodUsuario(Long.parseLong(datos[0].trim()));
                usuarioExportadoTmpDto.setNroDocumento(datos[1].trim());

                // Convertir DTO a entidad
                UsuarioExportadoTmp usuarioExportadotTmp = new UsuarioExportadoTmp();
                usuarioExportadotTmp.setCodUsuario(usuarioExportadoTmpDto.getCodUsuario());
                usuarioExportadotTmp.setNroDocumento(usuarioExportadoTmpDto.getNroDocumento());

                usuariosExportados.add(usuarioExportadotTmp);
            }
            try{
                usuarioExportadoTmpRepository.saveAll(usuariosExportados);
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Han sido importados los datos de usuarios correctamente");
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

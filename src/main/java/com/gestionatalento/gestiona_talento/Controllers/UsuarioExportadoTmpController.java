package com.gestionatalento.gestiona_talento.Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.UsuarioExportadoTmpDto;
import com.gestionatalento.gestiona_talento.Entity.UsuarioExportadoTmp;
import com.gestionatalento.gestiona_talento.Repository.UsuarioExportadoTmpRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

@RestController
@RequestMapping("/marcaciones/exportacion")
public class UsuarioExportadoTmpController {

    @Autowired
    UsuarioExportadoTmpRepository usuarioExportadoRepository;

    @PostMapping("/cargar-usuario")
    public GenericResponse cargarUsuario(@RequestParam("file") MultipartFile file) {
        GenericResponse genericResponse = new GenericResponse();
        if (file.isEmpty()) {
            genericResponse.setCodigoMensaje("404");
            genericResponse.setMensaje("El archivo se encuentra vacio");
            return genericResponse;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;
            List<UsuarioExportadoTmp> usuariosExportados = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Salta encabezado
                    continue;
                }

                String[] datos = line.split(",");
                UsuarioExportadoTmpDto usuarioExportadoDto = new UsuarioExportadoTmpDto();
                usuarioExportadoDto.setCodUsuario(Long.parseLong(datos[0].trim()));
                usuarioExportadoDto.setNroDocumento(datos[1].trim());

                // Convertir DTO a entidad
                UsuarioExportadoTmp usuarioExportado = new UsuarioExportadoTmp();
                usuarioExportado.setCodUsuario(usuarioExportadoDto.getCodUsuario());
                usuarioExportado.setNroDocumento(usuarioExportadoDto.getNroDocumento());

                usuariosExportados.add(usuarioExportado);
            }

            usuarioExportadoRepository.saveAll(usuariosExportados);
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido importados los datos de usuarios correctamente");
            genericResponse.setObjeto(null);
            return genericResponse;

        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
        
    }
}

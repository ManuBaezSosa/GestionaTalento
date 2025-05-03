package com.gestionatalento.gestiona_talento.Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.MarcacionExportadaTmpDto;
import com.gestionatalento.gestiona_talento.Entity.MarcacionExportadaTmp;
import com.gestionatalento.gestiona_talento.Entity.MarcacionExportadaTmpPK;
import com.gestionatalento.gestiona_talento.Entity.UsuarioExportadoTmp;
import com.gestionatalento.gestiona_talento.Repository.MarcacionExportadaTmpRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;


@RestController
@RequestMapping("/marcaciones/exportacion")
public class MarcacionExportadaTmpController {
    @Autowired
    MarcacionExportadaTmpRepository marcacionExportadaRepository;

    @PostMapping("/cargar-marcacion")
    public GenericResponse cargarMarcacion(@RequestParam("file") MultipartFile file) {
        GenericResponse genericResponse = new GenericResponse();
        if (file.isEmpty()) {
            genericResponse.setCodigoMensaje("404");
            genericResponse.setMensaje("El archivo se encuentra vacio");
            return genericResponse;
        }

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

            marcacionExportadaRepository.saveAll(marcacionesExportadas);
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido importados los datos de marcaciones correctamente");
            genericResponse.setObjeto(null);
            return genericResponse;

        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
        
    }
}

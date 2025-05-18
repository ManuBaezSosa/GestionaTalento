package com.gestionatalento.gestiona_talento.Mapper;


import java.io.IOException;

import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.gestionatalento.gestiona_talento.Dto.ParametroSalarialDto;
import com.gestionatalento.gestiona_talento.Entity.ParametroSalarial;

@Mapper
public interface ParametroSalarialMapper {

    public static ParametroSalarial setParametroSalarial(ParametroSalarialDto parametrosalarialDto) throws IOException{
        ParametroSalarial parametrosalarial = new ParametroSalarial();
        

        return parametrosalarial;
    }
    public static ParametroSalarial setActualizarParametroSalarial(ParametroSalarialDto parametrosalarialDto) {
        ParametroSalarial parametrosalarial = new ParametroSalarial();
        

        return parametrosalarial;
    }
}
